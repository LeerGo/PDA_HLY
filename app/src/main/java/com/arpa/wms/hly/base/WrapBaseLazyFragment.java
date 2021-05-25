package com.arpa.wms.hly.base;

import android.os.Bundle;

import com.arpa.and.wms.arch.base.BaseLazyFragment;
import com.arpa.and.wms.arch.base.BaseViewModel;
import com.arpa.and.wms.arch.base.livedata.StatusEvent;
import com.arpa.wms.hly.R;
import com.arpa.wms.hly.utils.ToastUtils;

import androidx.annotation.Nullable;
import androidx.databinding.ViewDataBinding;

/**
 * author: 李一方(<a href="mailto:leergo@dingtalk.com">leergo@dingtalk.com</a>)<br/>
 * version: 1.0.0<br/>
 * since: 2021-04-25 1:24 PM
 *
 * <p>
 * 基础：BaseLazyFragment 包装
 * </p>
 */
public abstract class WrapBaseLazyFragment <VM extends BaseViewModel, VDB extends ViewDataBinding>
        extends BaseLazyFragment<VM, VDB> {
    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        registerMessageEvent(ToastUtils::showShort);
        registerStatusEvent(status -> {
            if (status == StatusEvent.Status.LOADING) {
                showLoading();
            } else {
                hideLoading();
            }
        });
    }

    @Override
    protected void showProgressDialog(boolean isCancel) {
        showProgressDialog(R.layout.dialog_progress_arpa, isCancel);
    }
}
