package com.arpa.wms.hly.logic.mine;

import android.app.Application;

import com.arpa.and.wms.arch.base.BaseModel;
import com.arpa.and.wms.arch.base.DataViewModel;
import com.arpa.wms.hly.BuildConfig;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;
import androidx.hilt.lifecycle.ViewModelInject;

/**
 * author: 李一方(<a href="mailto:leergo@dingtalk.com">leergo@dingtalk.com</a>)<br/>
 * version: 1.0.0<br/>
 * since: 2021-04-25 3:13 PM
 *
 * <p>
 * 内容描述区域
 * </p>
 */
public class VMMine extends DataViewModel {
    public ObservableField<String> version = new ObservableField<>("v0.0.0");

    @ViewModelInject
    public VMMine(@NonNull Application application, BaseModel model) {
        super(application, model);
        version.set("v" + BuildConfig.VERSION_NAME);
    }
}
