package com.arpa.wms.hly.base.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;

import com.arpa.and.arch.base.BaseModel;
import com.arpa.and.arch.base.DataViewModel;
import com.arpa.and.arch.base.livedata.MessageEvent;
import com.arpa.and.arch.base.livedata.StatusEvent;
import com.arpa.wms.hly.net.ApiService;
import com.arpa.wms.hly.utils.SPUtils;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

/**
 * author: 李一方(<a href="mailto:leergo@dingtalk.com">leergo@dingtalk.com</a>)<br/>
 * version: 1.0.0<br/>
 * since: 2021-04-27 10:24 AM
 *
 * <p>
 * base：基础数据请求的 ViewModel 封装
 * </p>
 */
@HiltViewModel
public class WrapDataViewModel extends DataViewModel {
    public final ApiService apiService = getRetrofitService(ApiService.class);

    @Inject
    public WrapDataViewModel(@NonNull Application application, BaseModel model) {
        super(application, model);
    }

    public boolean spGetBoolean(String key) {
        return SPUtils.getInstance().getBoolean(key);
    }

    public String spGetString(String key) {
        return SPUtils.getInstance().getString(key);
    }

    public void spPut(String key, boolean value) {
        SPUtils.getInstance().put(key, value);
    }

    public void spPut(String key, String value) {
        SPUtils.getInstance().put(key, value);
    }

    public void register(LifecycleOwner owner, DataViewModel model) {
        getSingleLiveEvent().observe(owner, model::sendSingleLiveEvent);
        getStatusEvent().observe(owner, (StatusEvent.StatusObserver) model::updateStatus);
        getMessageEvent().observe(owner, (MessageEvent.MessageObserver) model::sendMessage);
    }
}
