package com.arpa.wms.hly.base.viewmodel;

import android.app.Application;

import com.arpa.and.wms.arch.base.BaseModel;
import com.arpa.and.wms.arch.base.DataViewModel;
import com.arpa.wms.hly.bean.Result;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableList;
import retrofit2.Call;

/**
 * author: 李一方(<a href="mailto:leergo@dingtalk.com">leergo@dingtalk.com</a>)<br/>
 * version: 1.0.0<br/>
 * since: 2021-04-26 9:35 AM
 *
 * <p>
 * 基础架构-ViewModel：普通列表数据加载
 * </p>
 */
public abstract class VMBaseList <T> extends DataViewModel {
    public final ObservableList<T> data = new ObservableArrayList<>();

    public VMBaseList(@NonNull Application application, BaseModel model) {
        super(application, model);
    }


    public abstract Call<Result<List<T>>> getCall();
}
