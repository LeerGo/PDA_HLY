package com.arpa.and.wms.arch.binding.viewadapter;

import com.scwang.smart.refresh.layout.SmartRefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnLoadMoreListener;
import com.scwang.smart.refresh.layout.listener.OnRefreshListener;

import androidx.annotation.NonNull;
import androidx.databinding.BindingAdapter;

/**
 * author: 李一方(<a href="mailto:leergo@dingtalk.com">leergo@dingtalk.com</a>)<br/>
 * version: 1.0.0<br/>
 * since: 2021-04-26 9:25 AM
 *
 * <p>
 * 内容描述区域
 * </p>
 */
public class RefreshViewBindingAdapter {
    @BindingAdapter(value = {"refreshing", "moreLoading", "hasMore"}, requireAll = false)
    public static void bindSmartRefreshLayout(SmartRefreshLayout smartLayout, Boolean refreshing, Boolean moreLoading, Boolean hasMore) {
        if (null != refreshing && !refreshing) smartLayout.finishRefresh();
        if (null != moreLoading && !moreLoading) smartLayout.finishLoadMore();
        if (hasMore != null) smartLayout.setNoMoreData(!hasMore);
    }

    @BindingAdapter(value = {"onRefreshListener", "onLoadMoreListener"}, requireAll = false)
    public static void bindListener(@NonNull SmartRefreshLayout smartLayout, OnRefreshListener refreshListener, OnLoadMoreListener loadMoreListener) {
        smartLayout.setOnRefreshListener(refreshListener);
        smartLayout.setOnLoadMoreListener(loadMoreListener);
    }

    @BindingAdapter(value = {"autoRefresh"})
    public static void bindSmartRefreshLayout(SmartRefreshLayout smartLayout, Boolean autoRefresh) {
        if (null != autoRefresh && autoRefresh) smartLayout.autoRefresh();
    }
}
