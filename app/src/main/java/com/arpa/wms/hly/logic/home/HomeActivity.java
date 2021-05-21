package com.arpa.wms.hly.logic.home;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;

import com.arpa.wms.hly.BR;
import com.arpa.wms.hly.R;
import com.arpa.wms.hly.base.WrapBaseActivity;
import com.arpa.wms.hly.bean.MenuBean;
import com.arpa.wms.hly.databinding.ActivityHomeBinding;
import com.arpa.wms.hly.ui.decoration.GridItemDecoration;
import com.arpa.wms.hly.ui.listener.ViewListener;
import com.arpa.wms.hly.utils.DensityUtils;

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

        viewBind.setVariable(BR.vmHome, viewModel);
        viewBind.rvMenu.addItemDecoration(new GridItemDecoration(DensityUtils.dip2px(10)));
        viewModel.getItemBinding().bindExtra(BR.listener, (ViewListener.DataTransCallback<MenuBean>) data -> {
            if (!TextUtils.isEmpty(data.getPath())) {
                Intent intent = new Intent();
                intent.setAction(data.getPath());
                startActivity(intent);
            }
        });
    }
}