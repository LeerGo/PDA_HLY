package com.arpa.wms.hly.base.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableBoolean;
import androidx.recyclerview.widget.AsyncDifferConfig;
import androidx.recyclerview.widget.DiffUtil;

import com.arpa.and.arch.base.BaseModel;
import com.arpa.and.arch.base.livedata.StatusEvent.Status;
import com.arpa.wms.hly.R;
import com.arpa.wms.hly.bean.base.ReqBase;
import com.arpa.wms.hly.bean.base.Result;
import com.arpa.wms.hly.net.callback.ResultCallback;
import com.arpa.wms.hly.net.exception.ResultError;

import java.util.List;
import java.util.Map;

import me.tatarka.bindingcollectionadapter2.BindingRecyclerViewAdapter;
import me.tatarka.bindingcollectionadapter2.ItemBinding;
import me.tatarka.bindingcollectionadapter2.collections.AsyncDiffObservableList;
import retrofit2.Call;

/**
 * author: 李一方(<a href="mailto:leergo@dingtalk.com">leergo@dingtalk.com</a>)<br/>
 * version: 1.0.0<br/>
 * since: 2021-04-26 9:35 AM
 *
 * <p>
 * base: 普通列表数据加载 ViewModel
 * </p>
 */
public abstract class VMBaseDiffList<T> extends WrapDataViewModel {
    public ObservableBoolean refreshing = new ObservableBoolean();
    public ObservableBoolean isAutoRefresh = new ObservableBoolean();

    // adapter 相关
    private AsyncDiffObservableList<T> items;
    private BindingRecyclerViewAdapter<T> adapter;

    public VMBaseDiffList(@NonNull Application application, BaseModel model) {
        super(application, model);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        configAdapter();
    }

    @Override
    public void onStart() {
        super.onStart();
        if (setAutoRefresh()) {
            autoRefresh();
        }
    }

    protected boolean setAutoRefresh() {
        return true;
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
            updateStatus(Status.LOADING);

        getCall(getParams().toParams()).enqueue(new ResultCallback<>() {
            @Override
            public void onSuccess(List<T> data) {
                if (null == data) {
                    sendMessage(R.string.failure_result_common, true);
                    updateStatus(Status.FAILURE, true);
                    return;
                }

                if (data.isEmpty()) sendMessage(R.string.data_empty);
                getItems().update(data);

                updateStatus(Status.SUCCESS, true);
            }

            @Override
            public void onFinish() {
                super.onFinish();
                refreshing.set(false);
                isAutoRefresh.set(false);
            }

            @Override
            public void onFailed(ResultError error) {
                updateStatus(Status.ERROR, true);
                sendMessage(error.getMessage(), true);
            }
        });
    }

    public AsyncDiffObservableList<T> getItems() {
        if (null == items) {
            items = new AsyncDiffObservableList<T>(getDiffConfig());
        }
        return items;
    }

    protected AsyncDifferConfig<T> getDiffConfig() {
        return new AsyncDifferConfig.Builder<>(new DiffUtil.ItemCallback<T>() {
            @Override
            public boolean areItemsTheSame(@NonNull T oldItem, @NonNull T newItem) {
                return false;
            }

            @Override
            public boolean areContentsTheSame(@NonNull T oldItem, @NonNull T newItem) {
                return false;
            }
        }).build();
    }

    public abstract Call<Result<List<T>>> getCall(Map<String, Object> params);

    public abstract ReqBase getParams();

    public void configAdapter() {
        adapter = new BindingRecyclerViewAdapter<>();
    }

    public BindingRecyclerViewAdapter<T> getAdapter() {
        return adapter;
    }

    public abstract ItemBinding<T> getItemBinding();
}
