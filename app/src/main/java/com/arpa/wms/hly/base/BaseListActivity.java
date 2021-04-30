package com.arpa.wms.hly.base;

import android.os.Bundle;

import com.arpa.and.wms.arch.base.BaseActivity;
import com.arpa.wms.hly.base.viewmodel.VMBaseRefreshList;
import com.arpa.wms.hly.utils.ToastUtils;

import androidx.annotation.Nullable;
import androidx.databinding.ViewDataBinding;

/**
 * author: 李一方(<a href="mailto:leergo@dingtalk.com">leergo@dingtalk.com</a>)<br/>
 * version: 1.0.0<br/>
 * since: 2021-04-25 1:24 PM
 *
 * <p>
 * 基础：上下拉刷刷新页面
 * </p>
 */
public abstract class BaseListActivity <VM extends VMBaseRefreshList, VDB extends ViewDataBinding> extends BaseActivity<VM, VDB> {
    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        registerMessageEvent(ToastUtils::showShort);
    }
}
