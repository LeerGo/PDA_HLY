package com.arpa.wms.hly.logic.home.inventory.move;

import android.app.Application;

import com.arpa.and.wms.arch.base.BaseModel;
import com.arpa.wms.hly.base.viewmodel.WrapDataViewModel;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;
import androidx.hilt.lifecycle.ViewModelInject;

/**
 * author: 李一方(<a href="mailto:leergo@dingtalk.com">leergo@dingtalk.com</a>)<br/>
 * version: 1.0.0<br/>
 * since: 2021-04-22 3:30 PM
 *
 * <p>
 * ViewModel: 扫描移除库位、扫描移位商品
 * </p>
 */
public class VMInventoryScan extends WrapDataViewModel {
    private ObservableField<String> title = new ObservableField<>();

    @ViewModelInject
    public VMInventoryScan(@NonNull Application application, BaseModel model) {
        super(application, model);
    }

    public ObservableField<String> getTitle() {
        return title;
    }
}
