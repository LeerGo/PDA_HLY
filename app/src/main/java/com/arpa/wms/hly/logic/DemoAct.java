package com.arpa.wms.hly.logic;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.arpa.wms.hly.R;
import com.arpa.wms.hly.base.WrapBaseActivity;
import com.arpa.wms.hly.databinding.ActivityDemoBinding;

import dagger.hilt.android.AndroidEntryPoint;

/**
 * author: 李一方(<a href="mailto:leergo@dingtalk.com">leergo@dingtalk.com</a>)<br/>
 * version: 1.0.0<br/>
 * since: 2023-04-19 15:26
 */
@AndroidEntryPoint
public class DemoAct extends WrapBaseActivity<VMDemo, ActivityDemoBinding> {
    @Override
    public int getLayoutId() {
        return R.layout.activity_demo;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        viewBind.setViewModel(viewModel);
    }
}
