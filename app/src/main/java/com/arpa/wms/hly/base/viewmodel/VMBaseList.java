package com.arpa.wms.hly.base.viewmodel;

import android.app.Application;

import com.arpa.and.wms.arch.base.BaseModel;
import com.arpa.and.wms.arch.base.livedata.StatusEvent;
import com.arpa.wms.hly.R;
import com.arpa.wms.hly.bean.base.ReqBase;
import com.arpa.wms.hly.bean.base.Result;
import com.arpa.wms.hly.net.callback.ResultCallback;
import com.arpa.wms.hly.net.exception.ResultError;

import java.util.List;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableBoolean;
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
    public ObservableBoolean refreshing = new ObservableBoolean();
    public ObservableBoolean isAutoRefresh = new ObservableBoolean();

    // adapter 相关
    private ObservableArrayList<T> items;
    private BindingRecyclerViewAdapter<T> adapter;

    public VMBaseList(@NonNull Application application, BaseModel model) {
        super(application, model);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        configAdapter();
    }

    public void configAdapter() {
        adapter = new BindingRecyclerViewAdapter<>();
    }

    @Override
    public void onStart() {
        super.onStart();
        autoRefresh();
    }

    /**
     * 自动刷新
     */
    public void autoRefresh() {
        isAutoRefresh.set(true);
        refresh();
    }

    /**
     * 下拉刷新数据
     */
    public void refresh() {
        refreshing.set(true);
        requestData();
    }

    private void requestData() {
        if (!isAutoRefresh.get())
            updateStatus(StatusEvent.Status.LOADING);

        getCall(getParams().toParams())
                .enqueue(new ResultCallback<List<T>>() {
                    @Override
                    public void onSuccess(List<T> data) {
                        if (null == data) {
                            sendMessage(R.string.failure_result_common, true);
                            updateStatus(StatusEvent.Status.FAILURE, true);
                            return;
                        }
                        getItems().clear();
                        if (data.isEmpty()) sendMessage(R.string.data_empty);
                        else getItems().addAll(data);

                        updateStatus(StatusEvent.Status.SUCCESS, true);
                    }

                    @Override
                    public void onFinish() {
                        super.onFinish();
                        refreshing.set(false);
                        isAutoRefresh.set(false);
                    }

                    @Override
                    public void onFailed(ResultError error) {
                        updateStatus(StatusEvent.Status.ERROR, true);
                        sendMessage(error.getMessage(), true);
                    }
                });
    }

    public ObservableArrayList<T> getItems() {
        if (null == items) {
            items = new ObservableArrayList<>();
        }
        return items;
    }

    public abstract Call<Result<List<T>>> getCall(Map<String, Object> params);

    public abstract ReqBase getParams();
}
