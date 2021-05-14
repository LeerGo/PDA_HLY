package com.arpa.wms.hly.logic.home.goods.pick;

import android.app.Application;

import com.arpa.and.wms.arch.base.BaseModel;
import com.arpa.wms.hly.base.viewmodel.WrapDataViewModel;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import dagger.hilt.android.lifecycle.HiltViewModel;

/**
 * author: 李一方(<a href="mailto:leergo@dingtalk.com">leergo@dingtalk.com</a>)<br/>
 * version: 1.0.0<br/>
 * since: 2021-05-14 16:07
 *
 * <p>
 * ViewModel: 拣货
 * </p>
 */
@HiltViewModel
public class VMGoodsPick extends WrapDataViewModel {
    @Inject
    public VMGoodsPick(@NonNull Application application, BaseModel model) {
        super(application, model);
    }
}
