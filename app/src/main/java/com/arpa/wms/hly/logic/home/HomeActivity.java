package com.arpa.wms.hly.logic.home;

import android.os.Bundle;

import com.arpa.wms.hly.R;
import com.arpa.wms.hly.base.WrapBaseActivity;
import com.arpa.wms.hly.databinding.ActivityHomeBinding;
import com.arpa.wms.hly.ui.decoration.GridItemDecoration;

import androidx.annotation.Nullable;
import dagger.hilt.android.AndroidEntryPoint;

/**
 * author: 李一方(<a href="mailto:a94118@gmail.com">a94118@gmail.com</a>)<br/>
 * version: 1.0.0<br/>
 * since: 2021-04-23 03:03<br/>
 *
 * <p>
 * 页面：首页
 * </p>
 */
@AndroidEntryPoint
public class HomeActivity extends WrapBaseActivity<VMHome, ActivityHomeBinding> {

    @Override
    public int getLayoutId() {
        return R.layout.activity_home;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        super.initData(savedInstanceState);

        viewBind.setVmHome(viewModel);
        viewBind.rvMenu.addItemDecoration(new GridItemDecoration(10));
    }
}