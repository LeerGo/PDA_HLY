package com.arpa.wms.hly.base.viewmodel;

import android.app.Application;

import com.arpa.and.wms.arch.base.BaseModel;
import com.arpa.and.wms.arch.base.DataViewModel;
import com.arpa.wms.hly.net.ApiService;
import com.arpa.wms.hly.utils.SPUtils;

import androidx.annotation.NonNull;

/**
 * author: 李一方(<a href="mailto:leergo@dingtalk.com">leergo@dingtalk.com</a>)<br/>
 * version: 1.0.0<br/>
 * since: 2021-04-27 10:24 AM
 *
 * <p>
 * 内容描述区域
 * </p>
 */
public class WrapDataViewModel extends DataViewModel {
    public final ApiService apiService = getRetrofitService(ApiService.class);

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
}
