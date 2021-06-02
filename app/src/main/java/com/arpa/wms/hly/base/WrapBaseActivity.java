package com.arpa.wms.hly.base;

import android.os.Bundle;

import com.arpa.and.arch.base.BaseViewModel;
import com.arpa.and.arch.base.livedata.StatusEvent;
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
 * base：基础 activity 的封装，注册消息、状态事件，调整 loading 动画
 * </p>
 */
public abstract class WrapBaseActivity <VM extends BaseViewModel, VDB extends ViewDataBinding>
        extends com.arpa.and.arch.base.BaseActivity<VM, VDB> {
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ToastUtils.cancel();
    }
}
