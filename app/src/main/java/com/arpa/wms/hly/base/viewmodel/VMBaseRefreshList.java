package com.arpa.wms.hly.base.viewmodel;

import android.app.Application;

import com.arpa.and.wms.arch.base.BaseModel;
import com.arpa.and.wms.arch.base.DataViewModel;
import com.arpa.and.wms.arch.base.livedata.StatusEvent;
import com.arpa.and.wms.arch.http.callback.ApiCallback;
import com.arpa.wms.hly.R;
import com.arpa.wms.hly.bean.base.Result;
import com.arpa.wms.hly.net.ApiService;
import com.arpa.wms.hly.utils.ToastUtils;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableBoolean;
import androidx.databinding.ObservableList;
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
public abstract class VMBaseRefreshList <T, A extends BindingRecyclerViewAdapter<T>> extends DataViewModel {
    // 分页相关
    public final static int PAGE_SIZE = 10;
    public final ApiService apiService = getRetrofitService(ApiService.class);

    // 数据相关
    public int page = 1;

    // SmartRefreshLayout 属性标记
    public ObservableBoolean refreshing = new ObservableBoolean();
    public ObservableBoolean moreLoading = new ObservableBoolean();
    public ObservableBoolean hasMore = new ObservableBoolean();
    public ObservableBoolean isAutoRefresh = new ObservableBoolean();

    // adapter 相关
    private ObservableList<T> items;
    private ItemBinding<T> itemBinding;
    private A adapter;


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

    }

    /**
     * 自动刷新
     */
    public void autoRefresh() {
        isAutoRefresh.set(true);
        refresh();
    }

    public void refresh() {
        page = 1;
        refreshing.set(true);
        requestData(true);
    }

    private void requestData(boolean isRefresh) {
        updateStatus(StatusEvent.Status.LOADING);
        getCall().enqueue(new ApiCallback<Result<List<T>>>() {
            @Override
            public void onResponse(Call<Result<List<T>>> call, Result<List<T>> result) {
                if (result != null) {
                    if (result.isSuccess()) { //成功
                        if (isRefresh) getItems().clear();
                        getItems().addAll(result.getData());
                        updateStatus(StatusEvent.Status.SUCCESS, true);
                        hasMore.set(result.getData().size() == PAGE_SIZE);
                    } else {
                        sendMessage(result.getMsg(), true);
                        updateStatus(StatusEvent.Status.FAILURE, true);
                    }
                } else {
                    sendMessage(R.string.failure_result_common, true);
                    updateStatus(StatusEvent.Status.FAILURE, true);
                }
                if (isRefresh) refreshing.set(false);
                else moreLoading.set(false);
            }

            @Override
            public void onError(Call<Result<List<T>>> call, Throwable t) {
                updateStatus(StatusEvent.Status.ERROR, true);
                sendMessage(t.getMessage(), true);
            }
        });
    }

    public ObservableList<T> getItems() {
        if (null == items) {
            items = new ObservableArrayList<>();
        }
        return items;
    }

    // FIXME: 应该用 ResultPage @lyf 2021-04-27 09:46:08
    public abstract Call<Result<List<T>>> getCall();

    public A getAdapter() {
        return adapter;
    }

    public void setAdapter(A adapter) {
        this.adapter = adapter;
    }

    public void loadMore() {
        page += 1;
        moreLoading.set(true);
        if (page > 4) {
            hasMore.set(false);
            ToastUtils.showShort("IS END！");
        }
        requestData(false);
    }

    public abstract ItemBinding<T> getItemBinding();
}
