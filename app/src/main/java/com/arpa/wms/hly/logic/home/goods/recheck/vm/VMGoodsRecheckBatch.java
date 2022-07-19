package com.arpa.wms.hly.logic.home.goods.recheck.vm;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Intent;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Message;
import android.text.TextUtils;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;

import com.arpa.and.arch.base.BaseModel;
import com.arpa.wms.hly.base.viewmodel.WrapDataViewModel;
import com.arpa.wms.hly.bean.SNCodeEntity;
import com.arpa.wms.hly.dao.AppDatabase;
import com.arpa.wms.hly.dao.SNCodeDao;
import com.arpa.wms.hly.ui.listener.ViewListener;
import com.arpa.wms.hly.utils.Const;
import com.arpa.wms.hly.utils.DateUtils;
import com.arpa.wms.hly.utils.RexUtils;
import com.arpa.wms.hly.utils.SoundMode;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

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
    private final HashMap<Integer, Integer> soundID = new HashMap<>();
    public ObservableField<String> goodName = new ObservableField<>();
    public ObservableField<String> goodUnitName = new ObservableField<>();
    public ObservableField<String> radio = new ObservableField<>("0.00%");
    public ArrayList<SNCodeEntity> codeList = new ArrayList<>();
    public ObservableField<Integer> scanCount = new ObservableField<>();
    public int goodsCount; // 商品数，用以计算扫码比例
    private String taskCode;
    private String gmtManufacture; // 生产日期
    private String placeOrigin; // 产地
    private SNCodeEntity entity;
    private SoundPool soundPool = null;

    @Inject
    public VMGoodsRecheckBatch(@NonNull Application application, BaseModel model) {
        super(application, model);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        try {
            initSoundPool();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initSoundPool() throws IOException {
        soundPool = new SoundPool(2, AudioManager.STREAM_SYSTEM, 5);
        // soundID.put(1, soundPool.load(this, R.raw.duang, 1));
        soundID.put(2, soundPool.load(getApplication().getAssets().openFd("biaobiao.mp3"), 1));  //需要捕获IO异常
        playSound(SoundMode.SUCCESS);
    }

    public void playSound(@SoundMode.SOUND_MODE int index) {
        soundPool.play(soundID.get(index), 1, 1, 0, 0, 1);
    }

    public void initData(Intent intent) {
        taskCode = intent.getStringExtra(Const.IntentKey.CODE);
        goodName.set(intent.getStringExtra(Const.IntentKey.GOODS_NAME));
        goodUnitName.set(intent.getStringExtra(Const.IntentKey.GOODS_UNIT_NAME));
        goodsCount = intent.getIntExtra(Const.IntentKey.GOODS_COUNT, 0);
        gmtManufacture = intent.getStringExtra(Const.IntentKey.DATE_MANUFACTURE);
        placeOrigin = intent.getStringExtra(Const.IntentKey.PLACE_ORIGIN);
        fillCodeList(intent.getParcelableArrayListExtra(Const.IntentKey.DATA));

        calcRadio();
    }

    private void fillCodeList(ArrayList<SNCodeEntity> list) {
        if (null == list || list.isEmpty()) {
            asyncLogic(() -> {
                int count = getSNCodeDao().count(taskCode);
                if (count > 0) {
                    sendSingleLiveEvent(Const.Message.MSG_DIALOG, true);
                }
            });
        } else {
            codeList = list;
            scanCount.set(codeList.size());
            sendSingleLiveEvent(Const.Message.MSG_RESTORE);
        }
    }

    /**
     * 获取序列号数据库操作类
     */
    public SNCodeDao getSNCodeDao() {
        return getRoomDatabase(AppDatabase.class).snCodeDao();
    }

    @SuppressLint("CheckResult")
    private void asyncLogic(ViewListener.VoidCallback callback) {
        Observable.just(1).subscribeOn(Schedulers.io()).subscribe(integer -> callback.call());
    }

    public void calcRadio() {
        BigDecimal result = BigDecimal.valueOf(codeList.size())
                .divide(BigDecimal.valueOf(goodsCount), 4, RoundingMode.HALF_UP)
                .multiply(BigDecimal.valueOf(100))
                .setScale(2, BigDecimal.ROUND_HALF_UP);
        radio.set(result + "%");
    }

    /**
     * 操作 tag 数据
     */
    @SuppressLint("CheckResult")
    public void optTagData(String snCode, boolean isAdd) {
        if (isAdd) addData();
        else removeData(snCode);
        calcRadio();
    }

    /**
     * 移除数据
     */
    private void removeData(String snCode) {
        codeList.remove(new SNCodeEntity(taskCode, snCode));
        scanCount.set(codeList.size());
        asyncLogic(() -> {
            if (null != getSNCodeDao().exists(taskCode, snCode)) {
                getSNCodeDao().delete(taskCode, snCode);
            }
        });
    }

    /**
     * 添加数据
     */
    public void addData() {
        playSound(SoundMode.SUCCESS);
        codeList.add(0, entity);
        scanCount.set(codeList.size());
    }

    /**
     * 判断输入非法
     */
    public boolean inputInvalid(String text) {
        if (TextUtils.isEmpty(text)) return true;
        if (!RexUtils.isBatchNo(text)) {
            sendMessage("批次号格式错误");
            return true;
        }
        if (codeList.contains(new SNCodeEntity(taskCode, text))) {
            sendMessage("该批次号已录入");
            return true;
        }
        if (codeList.size() == goodsCount) {
            sendMessage("批次号已录入最大数量");
            return true;
        }
        entity = new SNCodeEntity(taskCode, text);
        entity.verify(gmtManufacture, placeOrigin);
        if (!DateUtils.isDateValid(entity.getBriefDate())) {
            sendMessage("批次号格式错误");
            return true;
        }
        if (entity.isMoreToday()) {
            sendMessage("批次号日期超出当天");
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
            playSound(SoundMode.FAILED);
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
        asyncLogic(() -> {
            codeList = (ArrayList<SNCodeEntity>) getSNCodeDao().getByTask(taskCode);
            scanCount.set(codeList.size());
            sendSingleLiveEvent(Const.Message.MSG_RESTORE, true);
        });
    }

    /**
     * 丢弃记录
     */
    public void discardRecords() {
        asyncLogic(() -> getSNCodeDao().deleteByTask(taskCode));
    }

    /**
     * 暂存
     */
    public void saveAll() {
        asyncLogic(() -> {
            getSNCodeDao().deleteByTask(taskCode);
            getSNCodeDao().saveBatch(codeList);
            sendSingleLiveEvent(Const.Message.MSG_FINISH_RESULT, true);
        });
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
}
