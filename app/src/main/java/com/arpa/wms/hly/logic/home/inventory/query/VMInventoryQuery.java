package com.arpa.wms.hly.logic.home.inventory.query;

import android.app.Application;

import com.arpa.and.wms.arch.base.BaseModel;
import com.arpa.wms.hly.base.viewmodel.WrapDataViewModel;

import androidx.annotation.NonNull;
import androidx.hilt.lifecycle.ViewModelInject;

/**
 * author: 李一方(<a href="mailto:leergo@dingtalk.com">leergo@dingtalk.com</a>)<br/>
 * version: 1.0.0<br/>
 * since: 2021-04-22 3:30 PM
 *
 * <p>
 * ViewModel：库存查询
 * </p>
 */
public class VMInventoryQuery extends WrapDataViewModel {

    @ViewModelInject
    public VMInventoryQuery(@NonNull Application application, BaseModel model) {
        super(application, model);
    }
}
