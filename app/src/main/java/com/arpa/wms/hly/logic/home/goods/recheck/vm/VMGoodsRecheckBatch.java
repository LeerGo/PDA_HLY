package com.arpa.wms.hly.logic.home.goods.recheck.vm;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Intent;
import android.text.TextUtils;

import com.arpa.and.arch.base.BaseModel;
import com.arpa.wms.hly.base.viewmodel.WrapDataViewModel;
import com.arpa.wms.hly.bean.SNCodeEntity;
import com.arpa.wms.hly.dao.AppDatabase;
import com.arpa.wms.hly.dao.SNCodeDao;
import com.arpa.wms.hly.ui.listener.ViewListener;
import com.arpa.wms.hly.utils.Const;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;
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
    public ObservableField<String> goodName = new ObservableField<>();
    public ObservableField<String> goodUnitName = new ObservableField<>();
    public ObservableField<String> radio = new ObservableField<>("0.00%");
    public List<SNCodeEntity> codeList = new ArrayList<>();
    public int goodsCount;
    private String taskCode;

    @Inject
    public VMGoodsRecheckBatch(@NonNull Application application, BaseModel model) {
        super(application, model);
    }

    public void initData(Intent intent) {
        taskCode = intent.getStringExtra(Const.IntentKey.CODE);
        goodName.set(intent.getStringExtra(Const.IntentKey.GOODS_NAME));
        goodUnitName.set(intent.getStringExtra(Const.IntentKey.GOODS_UNIT_NAME));
        goodsCount = intent.getIntExtra(Const.IntentKey.GOODS_COUNT, 0);
        ArrayList<String> list = intent.getStringArrayListExtra(Const.IntentKey.DATA);
        fillCodeList(list);

        calcRadio();
    }

    private void fillCodeList(ArrayList<String> list) {
        if (null == list || list.isEmpty()) {
            asyncLogic(() -> {
                int count = getSNCodeDao().count(taskCode);
                if (count > 0) {
                    sendSingleLiveEvent(Const.Message.MSG_DIALOG, true);
                }
            });
        } else {
            for (String item : list) codeList.add(new SNCodeEntity(taskCode, item));
            sendSingleLiveEvent(Const.Message.MSG_RESTORE);
        }
    }

    public void calcRadio() {
        BigDecimal result = BigDecimal.valueOf(codeList.size())
                .divide(BigDecimal.valueOf(goodsCount), 4, RoundingMode.HALF_UP)
                .multiply(BigDecimal.valueOf(100))
                .setScale(2, BigDecimal.ROUND_HALF_UP);
        radio.set(result + "%");
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

    /**
     * 转换 list
     */
    public ArrayList<String> parseCodeList() {
        ArrayList<String> list = new ArrayList<>();
        for (SNCodeEntity item : codeList) list.add(item.getSnCode());
        return list;
    }

    /**
     * 操作 tag 数据
     */
    @SuppressLint("CheckResult")
    public void optTagData(String snCode, boolean isAdd) {
        SNCodeEntity entity = new SNCodeEntity(taskCode, snCode);
        if (isAdd) {
            codeList.add(0, entity);
            // asyncLogic(() -> {
            //     if (null == getSNCodeDao().exists(taskCode, snCode)) {
            //         getSNCodeDao().insert(entity);
            //     }
            // });
        } else {
            codeList.remove(entity);
            asyncLogic(() -> {
                if (null != getSNCodeDao().exists(taskCode, snCode)) {
                    getSNCodeDao().delete(taskCode, snCode);
                }
            });
        }
        calcRadio();
    }

    /**
     * 判断输入非法
     */
    public boolean inputInvalid(String text) {
        if (TextUtils.isEmpty(text)) return true;
        if (codeList.contains(new SNCodeEntity(taskCode, text))) {
            sendMessage("该批次号已录入");
            return true;
        }
        if (codeList.size() == goodsCount) {
            sendMessage("批次号已录入最大数量");
            return true;
        }
        return false;
    }

    /**
     * 恢复记录
     */
    public void restoreRecords() {
        asyncLogic(() -> {
            codeList = getSNCodeDao().getByTask(taskCode);
            sendSingleLiveEvent(Const.Message.MSG_RESTORE, true);
        });
    }

    /**
     * 丢弃记录
     */
    public void discardRecords() {
        asyncLogic(() -> getSNCodeDao().deleteByTask(taskCode));
    }

    public void saveAll() {
        asyncLogic(() -> {
            getSNCodeDao().deleteByTask(taskCode);
            getSNCodeDao().saveBatch(codeList);
            sendSingleLiveEvent(Const.Message.MSG_FINISH_RESULT, true);
        });
    }
}
