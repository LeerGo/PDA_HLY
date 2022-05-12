package com.arpa.wms.hly.logic.home.goods.recheck.vm;

import android.app.Application;
import android.content.Intent;

import com.arpa.and.arch.base.BaseModel;
import com.arpa.wms.hly.base.viewmodel.WrapDataViewModel;
import com.arpa.wms.hly.utils.Const;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;
import dagger.hilt.android.lifecycle.HiltViewModel;

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
    public ArrayList<String> codeList = new ArrayList<>();
    public int goodsCount;

    @Inject
    public VMGoodsRecheckBatch(@NonNull Application application, BaseModel model) {
        super(application, model);
    }

    public void initData(Intent intent) {
        goodName.set(intent.getStringExtra(Const.IntentKey.GOODS_NAME));
        codeList = intent.getStringArrayListExtra(Const.IntentKey.DATA);
        goodUnitName.set(intent.getStringExtra(Const.IntentKey.GOODS_UNIT_NAME));
        goodsCount = intent.getIntExtra(Const.IntentKey.GOODS_COUNT, 0);
        calcRadio();
    }

    public void calcRadio() {
        BigDecimal result = BigDecimal.valueOf(codeList.size()).divide(BigDecimal.valueOf(goodsCount), 2, RoundingMode.HALF_UP).multiply(BigDecimal.valueOf(100));
        radio.set(result + "%");
    }
}
