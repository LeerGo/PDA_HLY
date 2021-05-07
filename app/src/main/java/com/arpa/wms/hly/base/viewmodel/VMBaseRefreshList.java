package com.arpa.wms.hly.base.viewmodel;

import android.app.Application;

import com.arpa.and.wms.arch.base.BaseModel;
import com.arpa.and.wms.arch.base.livedata.StatusEvent;
import com.arpa.and.wms.arch.http.callback.ApiCallback;
import com.arpa.and.wms.arch.util.GsonUtils;
import com.arpa.wms.hly.R;
import com.arpa.wms.hly.bean.base.ReqPage;
import com.arpa.wms.hly.bean.base.ResultPage;

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
 * 基础架构-ViewModel：分页加载
 * </p>
 */
public abstract class VMBaseRefreshList <T> extends WrapDataViewModel {
    // 分页相关
    public final static int PAGE_SIZE = 10;

    // SmartRefreshLayout 属性标记
    public ObservableBoolean refreshing = new ObservableBoolean();
    public ObservableBoolean moreLoading = new ObservableBoolean();
    public ObservableBoolean hasMore = new ObservableBoolean();
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
        autoRefresh();
    }

    public void configAdapter() {
        // FIXME: 如果没有设置过 adapter，会有空指针 @lyf 2021-05-06 10:45:16
        adapter = new BindingRecyclerViewAdapter<>();
    }

    /**
     * 自动刷新
     */
    public void autoRefresh() {
        isAutoRefresh.set(true);
        refresh();
    }

    public void refresh() {
        getParams().pageReset();
        refreshing.set(true);
        requestData(true);
    }

    private void requestData(boolean isRefresh) {
        updateStatus(StatusEvent.Status.LOADING);
        getCall(GsonUtils.getInstance().pojo2Map(getParams()))
                .enqueue(new ApiCallback<ResultPage<T>>() {
                    @Override
                    public void onResponse(Call<ResultPage<T>> call, ResultPage<T> result) {
                        if (result != null) {
                            if (result.isSuccess()) { //成功
                                boolean isEmpty = result.getData().getRecords().isEmpty();
                                int listSize = result.getData().getRecords().size();

                                if (isRefresh) {
                                    getItems().clear();
                                    if (isEmpty)
                                        sendMessage(R.string.data_empty);
                                } else {
                                    if (isEmpty)
                                        sendMessage(R.string.data_no_more, true);
                                    hasMore.set(listSize == PAGE_SIZE);
                                }
                                getItems().addAll(result.getData().getRecords());
                                updateStatus(StatusEvent.Status.SUCCESS, true);
                            } else {
                                sendMessage(result.getMsg(), true);
                                updateStatus(StatusEvent.Status.FAILURE, true);
                            }
                        } else {
                            sendMessage(R.string.failure_result_common, true);
                            updateStatus(StatusEvent.Status.FAILURE, true);
                        }
                        refreshComplete();
                    }

                    private void refreshComplete() {
                        if (isRefresh) refreshing.set(false);
                        else moreLoading.set(false);
                    }

                    @Override
                    public void onError(Call<ResultPage<T>> call, Throwable t) {
                        refreshComplete();
                        updateStatus(StatusEvent.Status.ERROR, true);
                        sendMessage(t.getMessage(), true);
                    }
                });
    }

    public ObservableArrayList<T> getItems() {
        if (null == items) {
            items = new ObservableArrayList<>();
        }
        return items;
    }

    public abstract Call<ResultPage<T>> getCall(Map<String, Object> params);

    public abstract ReqPage getParams();

    public BindingRecyclerViewAdapter<T> getAdapter() {
        return adapter;
    }

    public void setAdapter(BindingRecyclerViewAdapter<T> adapter) {
        this.adapter = adapter;
    }

    public void loadMore() {
        getParams().pageIncrease();
        moreLoading.set(true);
        requestData(false);
    }

    public abstract ItemBinding<T> getItemBinding();
}
