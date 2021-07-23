package com.arpa.wms.hly.logic;

import android.os.Bundle;

import com.arpa.wms.hly.BuildConfig;
import com.arpa.wms.hly.R;
import com.arpa.wms.hly.base.WrapBaseActivity;
import com.arpa.wms.hly.bean.res.ResWarehouse;
import com.arpa.wms.hly.databinding.ActivityLoginBinding;
import com.arpa.wms.hly.ui.dialog.DialogApiChange;
import com.arpa.wms.hly.ui.dialog.DialogWarehouseSelect;
import com.arpa.wms.hly.ui.listener.ViewListener;

import androidx.annotation.Nullable;
import dagger.hilt.android.AndroidEntryPoint;

/**
 * author: 李一方(<a href="mailto:leergo@dingtalk.com">leergo@dingtalk.com</a>)<br/>
 * version: 1.0.0<br/>
 * since: 2021-04-21 2:04 PM
 *
 * <p>
 * 页面：登录
 * </p>
 */
@AndroidEntryPoint
public class LoginActivity extends WrapBaseActivity<VMLogin, ActivityLoginBinding> implements ViewListener.DataTransCallback<ResWarehouse> {
    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        super.initData(savedInstanceState);

        viewBind.setVmLogin(viewModel);
        viewModel.getWarehouseLiveData().observe(this, list -> {
            if (list.size() == 1) {
                viewModel.bindWarehouse(list.get(0), true);
            } else {
                showDialogFragment(new DialogWarehouseSelect(list, this));
            }
        });
        viewBind.tvWelcome.setOnClickListener(v -> {
            if (BuildConfig.DEBUG) {
                showDialogFragment(new DialogApiChange());
            }
        });
    }

    @Override
    public void transfer(ResWarehouse data) {
        viewModel.bindWarehouse(data, true);
    }
}
