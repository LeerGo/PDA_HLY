package com.arpa.wms.hly.base.viewmodel;

import android.app.Application;

import com.arpa.and.wms.arch.base.BaseModel;
import com.arpa.wms.hly.bean.base.Result;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableArrayList;
import me.tatarka.bindingcollectionadapter2.BindingRecyclerViewAdapter;
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
public abstract class VMBaseList <T> extends WrapDataViewModel {

    // adapter 相关
    private ObservableArrayList<T> items;
    private BindingRecyclerViewAdapter<T> adapter;

    public VMBaseList(@NonNull Application application, BaseModel model) {
        super(application, model);
    }

    public abstract Call<Result<List<T>>> getCall();

    public BindingRecyclerViewAdapter<T> getAdapter() {
        return adapter;
    }

    public void setAdapter(BindingRecyclerViewAdapter<T> adapter) {
        this.adapter = adapter;
    }

    public ObservableArrayList<T> getItems() {
        if (null == items) {
            items = new ObservableArrayList<>();
        }
        return items;
    }
}
