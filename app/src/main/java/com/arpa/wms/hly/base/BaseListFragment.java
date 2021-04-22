package com.arpa.wms.hly.base;

import com.arpa.wms.hly.R;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;

/**
 * author: 李一方(<a href="mailto:leergo@dingtalk.com">leergo@dingtalk.com</a>)<br/>
 * version: 1.0.0<br/>
 * since: 2021-04-21 3:50 PM
 *
 * <p>
 * 内容描述区域
 * </p>
 */
public abstract class BaseListFragment <T> extends BaseFragment {
    protected List<T> data;
    protected BaseAdapter<T> adapter;

    @BindView(R.id.srl_refresh)
    SmartRefreshLayout srlRefresh;
    @BindView(R.id.rv_list)
    RecyclerView rvList;

    private int pageNumber = 1;
    private int pageSize = 10;

    @Override
    protected void initData() {
        data = new ArrayList<>();
        srlRefresh.autoRefresh();
    }

    @Override
    protected void setViews() {
        srlRefresh.setOnRefreshListener(refreshLayout -> {
            pageNumber = 1;
            requestData(true);
        });
        srlRefresh.setOnLoadMoreListener(refreshLayout -> {
            pageNumber++;
            requestData(false);
        });

        DividerItemDecoration divider = new DividerItemDecoration(Objects.requireNonNull(getContext()), DividerItemDecoration.VERTICAL);
        divider.setDrawable(Objects.requireNonNull(ContextCompat.getDrawable(getContext(), R.drawable.divider_line_vertical_10dp)));
        rvList.addItemDecoration(divider);
        rvList.setAdapter(adapter);
    }

    protected void requestData(boolean isRefresh) {
        if (isRefresh) {
            srlRefresh.finishRefresh();
        } else {
            srlRefresh.finishLoadMore();
        }
    }
}
