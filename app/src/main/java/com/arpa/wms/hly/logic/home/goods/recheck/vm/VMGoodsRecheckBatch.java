package com.arpa.wms.hly.logic.home.goods.recheck.vm;

import android.app.Application;

import com.arpa.and.wms.arch.base.BaseModel;
import com.arpa.wms.hly.base.viewmodel.WrapDataViewModel;

import java.util.ArrayList;

import javax.inject.Inject;

import androidx.annotation.NonNull;
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
    public ArrayList<String> codeList = new ArrayList<>();

    @Inject
    public VMGoodsRecheckBatch(@NonNull Application application, BaseModel model) {
        super(application, model);

    }
}
