package com.arpa.wms.hly.logic.home.goods.take.vm;

import android.app.Application;

import com.arpa.and.wms.arch.base.BaseModel;
import com.arpa.and.wms.arch.base.livedata.StatusEvent;
import com.arpa.and.wms.arch.http.callback.ApiCallback;
import com.arpa.and.wms.arch.util.GsonUtils;
import com.arpa.wms.hly.BR;
import com.arpa.wms.hly.R;
import com.arpa.wms.hly.base.viewmodel.WrapDataViewModel;
import com.arpa.wms.hly.bean.base.Result;
import com.arpa.wms.hly.bean.req.ReqGoodsTakeDetail;
import com.arpa.wms.hly.bean.res.ResGoodsTakeDetail;
import com.arpa.wms.hly.bean.res.ResGoodsTakeDetail.ItemsBean;
import com.arpa.wms.hly.utils.Const;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableBoolean;
import androidx.hilt.lifecycle.ViewModelInject;
import me.tatarka.bindingcollectionadapter2.ItemBinding;
import retrofit2.Call;

/**
 * author: 李一方(<a href="mailto:leergo@dingtalk.com">leergo@dingtalk.com</a>)<br/>
 * version: 1.0.0<br/>
 * since: 2021-05-06 15:08
 *
 * <p>
 * 内容描述区域
 * </p>
 */
public class VMGoodsTakeDetailList extends WrapDataViewModel {
    // 分页相关
    public final static int PAGE_SIZE = 10;
    public final ReqGoodsTakeDetail reqGoodsTakeDetail = new ReqGoodsTakeDetail(PAGE_SIZE);
    // adapter 相关
    private final ObservableArrayList<ItemsBean> items = new ObservableArrayList<>();
    // SmartRefreshLayout 属性标记
    public ObservableBoolean refreshing = new ObservableBoolean();
    public ObservableBoolean moreLoading = new ObservableBoolean();
    public ObservableBoolean hasMore = new ObservableBoolean();
    public ObservableBoolean isAutoRefresh = new ObservableBoolean();

    @ViewModelInject
    public VMGoodsTakeDetailList(@NonNull Application application, BaseModel model) {
        super(application, model);
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

    public void refresh() {
        reqGoodsTakeDetail.pageReset();
        refreshing.set(true);
        requestData(true);
    }

    /**
     * 请求数据
     *
     * @param isRefresh
     *         true - 下拉刷新数据
     */
    private void requestData(boolean isRefresh) {
        updateStatus(StatusEvent.Status.LOADING);
        apiService.goodsTakeDetailList(GsonUtils.getInstance().pojo2Map(reqGoodsTakeDetail))
                .enqueue(new ApiCallback<Result<List<ResGoodsTakeDetail>>>() {
                    @Override
                    public void onResponse(Call<Result<List<ResGoodsTakeDetail>>> call, Result<List<ResGoodsTakeDetail>> result) {
                        if (result != null) {
                            if (result.isSuccess() && null != result.getData()) { //成功
                                boolean isEmpty = result.getData().isEmpty();
                                int listSize = result.getData().size();

                                if (isRefresh) {
                                    items.clear();
                                    if (isEmpty) sendMessage(R.string.data_empty);
                                } else {
                                    if (isEmpty) sendMessage(R.string.data_no_more, true);
                                    hasMore.set(listSize == PAGE_SIZE);
                                }
                                items.addAll(result.getData().get(0).getItems());
                                updateStatus(StatusEvent.Status.SUCCESS, true);
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
                    public void onError(Call<Result<List<ResGoodsTakeDetail>>> call, Throwable t) {
                        updateStatus(StatusEvent.Status.ERROR, true);
                        sendMessage(t.getMessage(), true);
                    }
                });
    }

    public ObservableArrayList<ItemsBean> getItems() {
        return items;
    }

    public void loadMore() {
        reqGoodsTakeDetail.pageIncrease();
        moreLoading.set(true);
        requestData(false);
    }

    public ItemBinding<ItemsBean> getItemBinding() {
        ItemBinding<ItemsBean> itemBinding;
        if (reqGoodsTakeDetail.getReceiveStatus().equals(Const.TASK_STATUS.TAKE_WAIT)) {
            itemBinding = ItemBinding.of(BR.data, R.layout.item_goods_take_detail_wait);
        } else {
            itemBinding = ItemBinding.of(BR.data, R.layout.item_goods_take_detail_yet);
        }
        return itemBinding;
    }
}
