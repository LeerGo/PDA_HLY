package com.arpa.wms.hly.logic.home.inventory.move;

import android.app.Application;

import com.arpa.and.wms.arch.base.BaseModel;
import com.arpa.wms.hly.base.viewmodel.WrapDataViewModel;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;
import dagger.hilt.android.lifecycle.HiltViewModel;

/**
 * author: 李一方(<a href="mailto:leergo@dingtalk.com">leergo@dingtalk.com</a>)<br/>
 * version: 1.0.0<br/>
 * since: 2021-04-22 3:30 PM
 *
 * <p>
 * ViewModel: 扫描移除库位、扫描移位商品
 * </p>
 */
@HiltViewModel
public class VMInventoryScan extends WrapDataViewModel {
    public final ObservableField<String> title = new ObservableField<>();
    public final ObservableField<String> searchHint = new ObservableField<>();

    @Inject
    public VMInventoryScan(@NonNull Application application, BaseModel model) {
        super(application, model);
    }
}
