package com.arpa.wms.hly.logic;

import android.os.Bundle;

import com.arpa.and.wms.arch.base.BaseActivity;
import com.arpa.and.wms.arch.base.livedata.StatusEvent;
import com.arpa.wms.hly.BR;
import com.arpa.wms.hly.R;
import com.arpa.wms.hly.databinding.ActivityLoginBinding;

import androidx.annotation.Nullable;
import dagger.hilt.android.AndroidEntryPoint;
import timber.log.Timber;


/**
 * author: 李一方(<a href="mailto:leergo@dingtalk.com">leergo@dingtalk.com</a>)<br/>
 * version: 1.0.0<br/>
 * since: 2021-04-21 2:04 PM
 *
 * <p>
 * 内容描述区域
 * </p>
 */
// TODO: 框架的日志打印还需要重新做一下 @lyf 2021-04-23 03:31:14
// TODO: 框架的条状 activity 可已参照 MVVMHabit 修改一下，这样就不用写 onLoginClick 方法 @lyf 2021-04-23 04:24:28
@AndroidEntryPoint
public class LoginActivity extends BaseActivity<VMLogin, ActivityLoginBinding> {

    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        viewBind.setVariable(BR.vmLogin, viewModel);
        registerMessageEvent(message -> {
            Timber.e("message:%s", message);
            //            ToastUtils.showToast(getContext(), message);
        });
        registerStatusEvent(status -> {
            switch (status) {
                case StatusEvent.Status.LOADING:
                    //                    if (!getViewDataBinding().srl.isRefreshing()) {
                    showLoading();
                    //                    }
                    break;
                case StatusEvent.Status.SUCCESS:
                case StatusEvent.Status.FAILURE:
                case StatusEvent.Status.ERROR:
                    hideLoading();
                    //                    getViewDataBinding().srl.setRefreshing(false);
                    break;
            }
        });
        viewModel.warehouseLiveData.observe(this, list -> {
            //            Log.e("@@@@ L35", "LoginActivity:initData() -> ===================" + list.size());
        });
    }
}
