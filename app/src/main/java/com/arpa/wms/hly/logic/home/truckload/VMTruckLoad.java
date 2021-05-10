package com.arpa.wms.hly.logic.home.truckload;

import android.app.Application;

import com.arpa.and.wms.arch.base.BaseModel;
import com.arpa.wms.hly.base.viewmodel.WrapDataViewModel;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import dagger.hilt.android.lifecycle.HiltViewModel;

/**
 * author: 李一方(<a href="mailto:leergo@dingtalk.com">leergo@dingtalk.com</a>)<br/>
 * version: 1.0.0<br/>
 * since: 2021-04-25 2:01 PM
 *
 * <p>
 * ViewModel：装车出厂（列表）
 * </p>
 */
@HiltViewModel
public class VMTruckLoad extends WrapDataViewModel {

    @Inject
    public VMTruckLoad(@NonNull Application application, BaseModel model) {
        super(application, model);
    }
}
