package com.arpa.wms.hly.demo;

import android.os.Bundle;

import com.arpa.and.wms.arch.base.BaseActivity;
import com.arpa.wms.hly.R;
import com.arpa.wms.hly.databinding.ActivityDemoList2Binding;

import androidx.annotation.Nullable;
import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class DemoListActivity extends BaseActivity<VMDemoList, ActivityDemoList2Binding> {

    @Override
    public int getLayoutId() {
        return R.layout.activity_demo_list2;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        viewBind.setVm(viewModel);
    }
}