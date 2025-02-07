package com.arpa.wms.hly.base.viewmodel;

import android.app.Application;

import com.arpa.and.arch.base.BaseModel;
import com.arpa.and.arch.base.livedata.StatusEvent.Status;
import com.arpa.wms.hly.R;
import com.arpa.wms.hly.bean.base.ReqPage;
import com.arpa.wms.hly.bean.base.ResultPage;
import com.arpa.wms.hly.net.callback.ResultPageCallback;
import com.arpa.wms.hly.net.exception.ResultError;

import java.util.List;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableBoolean;
import me.tatarka.bindingcollectionadapter2.BindingRecyclerViewAdapter;
import me.tatarka.bindingcollectionadapter2.ItemBinding;
import retrofit2.Call;

/**
 * author: 李一方(<a href="mailto:leergo@dingtalk.com">leergo@dingtalk.com</a>)<br/>
 * version: 1.0.0<br/>
 * since: 2021-04-26 9:35 AM
 *
 * <p>
 * base: 基础分页加载 ViewModel
 * </p>
 */
public abstract class VMBaseRefreshList <T> extends WrapDataViewModel {
    // 分页相关
    public final static int PAGE_SIZE = 10;

    // SmartRefreshLayout 属性标记
    public ObservableBoolean refreshing = new ObservableBoolean();
    public ObservableBoolean moreLoading = new ObservableBoolean();
    public ObservableBoolean hasMore = new ObservableBoolean(true);
    public ObservableBoolean isAutoRefresh = new ObservableBoolean();

    // adapter 相关
    private ObservableArrayList<T> items;
    private BindingRecyclerViewAdapter<T> adapter;

    public VMBaseRefreshList(@NonNull Application application, BaseModel model) {
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
        getParams().pageReset();
        refreshing.set(true);
        requestData(true);
    }

    private void requestData(boolean isRefresh) {
        if (!isAutoRefresh.get())
            updateStatus(Status.LOADING);

        getCall(getParams().toParams())
                .enqueue(new ResultPageCallback<T>() {
                    @Override
                    public void onSuccess(List<T> data) {
                        if (null == data) {
                            sendMessage(R.string.failure_result_common, true);
                            updateStatus(Status.FAILURE, true);
                            return;
                        }

                        boolean isEmpty = data.isEmpty();
                        int listSize = data.size();

                        if (isRefresh) {
                            getItems().clear();
                            if (isEmpty) sendMessage(R.string.data_empty);
                        } else {
                            if (isEmpty) sendMessage(R.string.data_no_more, true);
                        }
                        hasMore.set(listSize == PAGE_SIZE);
                        getItems().addAll(data);
                        updateStatus(Status.SUCCESS, true);
                    }

                    @Override
                    public void onFinish() {
                        refreshComplete(isRefresh);
                        isAutoRefresh.set(false);
                    }

                    @Override
                    public void onFailed(ResultError error) {
                        updateStatus(Status.ERROR, true);
                        sendMessage(error.getMessage(), true);
                    }
                });
    }

    protected void refreshComplete(boolean isRefresh) {
        if (isRefresh) refreshing.set(false);
        else moreLoading.set(false);
    }

    public abstract Call<ResultPage<T>> getCall(Map<String, Object> params);

    public ObservableArrayList<T> getItems() {
        if (null == items) {
            items = new ObservableArrayList<>();
        }
        return items;
    }

    public abstract ReqPage getParams();

    public void configAdapter() {
        adapter = new BindingRecyclerViewAdapter<>();
    }

    public BindingRecyclerViewAdapter<T> getAdapter() {
        return adapter;
    }

    public void setAdapter(BindingRecyclerViewAdapter<T> adapter) {
        this.adapter = adapter;
    }

    /**
     * 上拉加载
     */
    public void loadMore() {
        getParams().pageIncrease();
        moreLoading.set(true);
        requestData(false);
    }

    public abstract ItemBinding<T> getItemBinding();
}
