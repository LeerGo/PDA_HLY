package com.arpa.wms.hly.logic.home.goods.recheck.vm;

import android.app.Application;
import android.content.Intent;
import android.os.Message;
import android.text.TextUtils;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableBoolean;
import androidx.databinding.ObservableField;

import com.arpa.and.arch.base.BaseModel;
import com.arpa.wms.hly.BR;
import com.arpa.wms.hly.R;
import com.arpa.wms.hly.base.viewmodel.WrapDataViewModel;
import com.arpa.wms.hly.bean.entity.SNCodeEntity;
import com.arpa.wms.hly.dao.AppDatabase;
import com.arpa.wms.hly.dao.SNCodeDao;
import com.arpa.wms.hly.ui.listener.ViewListener;
import com.arpa.wms.hly.utils.Const;
import com.arpa.wms.hly.utils.DateUtils;
import com.arpa.wms.hly.utils.RexUtils;
import com.arpa.wms.hly.utils.SoundPlayer;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collections;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.internal.operators.completable.CompletableConcat;
import io.reactivex.rxjava3.schedulers.Schedulers;
import me.tatarka.bindingcollectionadapter2.ItemBinding;

/**
 * author: 李一方(<a href="mailto:leergo@dingtalk.com">leergo@dingtalk.com</a>)<br/>
 * version: 1.0.0<br/>
 * since: 2021-04-25 2:03 PM
 *
 * <p>
 * ViewModel: 商品待复核列表
 * </p>
 */
@HiltViewModel
public class VMGoodsRecheckBatch extends WrapDataViewModel {
    public ObservableField<String> goodName = new ObservableField<>();
    public ObservableField<String> goodUnitName = new ObservableField<>();
    public ObservableField<String> radio = new ObservableField<>("0.00%");
    public ObservableArrayList<SNCodeEntity> codeList = new ObservableArrayList<>();
    public ObservableField<Integer> scanCount = new ObservableField<>();
    public ObservableBoolean isFocus = new ObservableBoolean(false);
    public int goodsCount; // 商品数，用以计算扫码比例
    public SoundPlayer player;
    public ObservableField<String> gmtManufacture = new ObservableField<>(); // 生产日期
    public ObservableField<String> placeOrigin = new ObservableField<>(); // 产地
    public ObservableBoolean isManually = new ObservableBoolean();
    private String taskCode;
    private SNCodeEntity entity;

    @Inject
    public VMGoodsRecheckBatch(@NonNull Application application, BaseModel model) {
        super(application, model);
    }

    public ItemBinding<SNCodeEntity> getItemBinding() {
        ItemBinding<SNCodeEntity> itemBinding = ItemBinding.of(BR.data, R.layout.item_batch_code);
        itemBinding.bindExtra(BR.listener, (ViewListener.DataTransCallback<SNCodeEntity>) data -> removeData(data.getSnCode()));
        return itemBinding;
    }

    /**
     * 移除数据
     */
    private void removeData(String snCode) {
        codeList.remove(new SNCodeEntity(taskCode, snCode));
        scanCount.set(codeList.size());
        calcRadio();
        getSNCodeDao().delete(taskCode, snCode)
                .subscribeOn(Schedulers.io()).subscribe();
    }

    /**
     * 计算扫描百分比
     */
    public void calcRadio() {
        BigDecimal result;
        if (0 == goodsCount) result = BigDecimal.ZERO;
        else result = BigDecimal.valueOf(codeList.size())
                .divide(BigDecimal.valueOf(goodsCount), 4, RoundingMode.HALF_UP)
                .multiply(BigDecimal.valueOf(100))
                .setScale(2, BigDecimal.ROUND_HALF_UP);
        radio.set(result + "%");
        isFocus.set(true);
    }

    /**
     * 获取序列号数据库操作类
     */
    private SNCodeDao getSNCodeDao() {
        return getRoomDatabase(AppDatabase.class).snCodeDao();
    }

    @Override
    public void onCreate() {
        super.onCreate();

        player = new SoundPlayer(getApplication());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        player.release();
    }

    public void initData(Intent intent) {
        taskCode = intent.getStringExtra(Const.IntentKey.CODE);
        goodName.set(intent.getStringExtra(Const.IntentKey.GOODS_NAME));
        goodUnitName.set(intent.getStringExtra(Const.IntentKey.GOODS_UNIT_NAME));
        goodsCount = intent.getIntExtra(Const.IntentKey.GOODS_COUNT, 0);
        gmtManufacture.set(intent.getStringExtra(Const.IntentKey.DATE_MANUFACTURE));
        placeOrigin.set(intent.getStringExtra(Const.IntentKey.PLACE_ORIGIN));
        fillCodeList(intent.getParcelableArrayListExtra(Const.IntentKey.DATA));
        calcRadio();
    }

    /**
     * 填充批次号列表
     */
    private void fillCodeList(ArrayList<SNCodeEntity> list) {
        if (null == list || list.isEmpty()) {
            getSNCodeDao().count(taskCode)
                    .subscribeOn(Schedulers.io())
                    .subscribe(res -> {
                        if (res > 0) sendSingleLiveEvent(Const.Message.MSG_DIALOG, true);
                    }).dispose();
        } else {
            codeList.addAll(list);
            scanCount.set(codeList.size());
        }
    }

    /**
     * 添加 tag
     */
    public void addTag(String snCode) {
        if (inputInvalid(snCode)) {
            isFocus.set(true);
            return;
        }
        // player.play(R.raw.scan_success);
        addData();
    }

    /**
     * 添加数据
     */
    public void addData() {
        codeList.add(0, entity);
        scanCount.set(codeList.size());
        calcRadio();
    }

    /**
     * 判断输入非法
     */
    public boolean inputInvalid(String text) {
        if (TextUtils.isEmpty(text)) return true;
        if (!RexUtils.isBatchNo(text)) {
            sendMessage("批次号格式错误");
            player.play(R.raw.scan_failed);
            return true;
        }
        if (codeList.contains(new SNCodeEntity(taskCode, text))) {
            sendSingleLiveEvent(Const.Message.MSG_BATCH_REPEAT);
            player.play(R.raw.scan_failed);
            return true;
        }
        if (codeList.size() == goodsCount) {
            sendMessage("批次号已录入最大数量");
            player.play(R.raw.scan_failed);
            return true;
        }
        entity = new SNCodeEntity(taskCode, text);
        entity.verify(gmtManufacture.get(), placeOrigin.get());
        if (!DateUtils.isDateValid(entity.getBriefDate())) {
            sendMessage("批次号格式错误");
            player.play(R.raw.scan_failed);
            return true;
        }
        if (entity.isMoreToday()) {
            sendMessage("批次号日期超出当天");
            player.play(R.raw.scan_failed);
            return true;
        }
        StringBuilder dialogTip = new StringBuilder();
        if (!entity.isTimeVerify()) dialogTip.append("时分秒校验错误");
        if (!entity.isDateVerify()) {
            if (!TextUtils.isEmpty(dialogTip)) dialogTip.append("\n");
            dialogTip.append("生产日期校验错误");
        }
        if (!entity.isOriginVerify()) {
            if (!TextUtils.isEmpty(dialogTip)) dialogTip.append("\n");
            dialogTip.append("产地校验错误");
        }
        if (!TextUtils.isEmpty(dialogTip)) {
            player.play(R.raw.scan_error);
            Message msg = new Message();
            msg.what = Const.Message.MSG_BATCH_VERIFY;
            msg.obj = new String[]{dialogTip.toString(), text};
            sendSingleLiveEvent(msg);
            return true;
        }
        return false;
    }

    /**
     * 恢复记录
     */
    public void restoreRecords() {
        getSNCodeDao().getByTask(taskCode)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(data -> {
                    codeList.addAll(data);
                    scanCount.set(codeList.size());
                    calcRadio();
                }).dispose();
    }

    /**
     * 丢弃记录
     */
    public void discardRecords() {
        getSNCodeDao().deleteByTask(taskCode)
                .subscribeOn(Schedulers.io()).subscribe();
    }

    /**
     * 暂存
     */
    public void saveAll() {
        CompletableConcat.concatArray(getSNCodeDao().deleteByTask(taskCode), getSNCodeDao().saveBatch(codeList))
                .subscribeOn(Schedulers.io())
                .subscribe(() -> sendSingleLiveEvent(Const.Message.MSG_FINISH_RESULT, true)).dispose();
    }

    /**
     * 确认
     */
    public void confirm() {
        if (codeList.size() < 2) {
            sendSingleLiveEvent(Const.Message.MSG_FINISH_RESULT, true);
        } else {
            Message msg = new Message();
            msg.what = Const.Message.MSG_BATCH_CONFIRM;

            boolean isDateVerify = true;
            boolean isOriginVerify = true;
            for (SNCodeEntity item : codeList) {
                isDateVerify = isDateVerify && item.isDateVerify();
                isOriginVerify = isOriginVerify && item.isOriginVerify();
                if (!isDateVerify && !isOriginVerify) break;
            }

            SNCodeEntity oldestBatchNo = Collections.max(codeList);
            SNCodeEntity latestBatchNo = Collections.min(codeList);
            String diff = DateUtils.dateDiff(latestBatchNo.getDate(), oldestBatchNo.getDate());
            msg.obj = (isOriginVerify ? "1、产地校验正确" : "1、产地校验不正确") + "\n" +
                    (isDateVerify ? "2、生产日期校验正确" : "2、生产日期校验不正确") + "\n" +
                    "3、上下批次时间为" + diff;
            sendSingleLiveEvent(msg);
        }
    }

    /**
     * 开启手动模式
     */
    public void manuallyMode(boolean isManually) {
        this.isManually.set(isManually);
    }
}
