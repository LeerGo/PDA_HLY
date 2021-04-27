package com.arpa.wms.hly.demo.refresh;

import android.os.Bundle;

import com.arpa.and.wms.arch.base.BaseActivity;
import com.arpa.wms.hly.R;
import com.arpa.wms.hly.databinding.ActivityDemoList2RefreshBinding;

import androidx.annotation.Nullable;
import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class DemoListRefreshActivity extends BaseActivity<VMDemoListRefresh, ActivityDemoList2RefreshBinding> {

    @Override
    public int getLayoutId() {
        return R.layout.activity_demo_list2_refresh;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        viewBind.setVm(viewModel);
//        viewModel.refreshing.observeForever(isRefreshing -> {
//            Log.e("@@@@ L24", "DemoListRefreshActivity:initData() -> isRefreshing = " + isRefreshing);
//            if (!isRefreshing) {
//                viewBind.refreshLayout.finishRefresh();
//            }
//        });
    }
}