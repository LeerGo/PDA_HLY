package com.arpa.wms.hly.logic.home.goods;

import android.app.Application;

import com.arpa.and.wms.arch.base.BaseModel;
import com.arpa.and.wms.arch.base.DataViewModel;

import androidx.annotation.NonNull;
import androidx.hilt.lifecycle.ViewModelInject;

/**
 * author: 李一方(<a href="mailto:leergo@dingtalk.com">leergo@dingtalk.com</a>)<br/>
 * version: 1.0.0<br/>
 * since: 2021-04-25 2:03 PM
 *
 * <p>
 * ViewModel: 商品待收货类别
 * </p>
 */
public class VMGoodsTake extends DataViewModel {
    @ViewModelInject
    public VMGoodsTake(@NonNull Application application, BaseModel model) {
        super(application, model);
    }
}
