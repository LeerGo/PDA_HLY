package com.arpa.wms.hly.logic;

import android.os.Bundle;

import com.arpa.and.wms.arch.base.BaseActivity;
import com.arpa.and.wms.arch.base.livedata.StatusEvent;
import com.arpa.wms.hly.BR;
import com.arpa.wms.hly.R;
import com.arpa.wms.hly.bean.ResWarehouse;
import com.arpa.wms.hly.databinding.ActivityLoginBinding;
import com.arpa.wms.hly.ui.dialog.DialogWarehouseSelect;
import com.arpa.wms.hly.ui.listener.DialogDismissListener;
import com.arpa.wms.hly.utils.ToastUtils;

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
// TODO: 框架的日志打印还需要重新做一下 参考 MVVMHabit @lyf 2021-04-23 03:31:14
// TODO: 框架的条状 activity 可已参照 MVVMHabit 修改一下，这样就不用写 onLoginClick 方法 @lyf 2021-04-23 04:24:28
@AndroidEntryPoint
public class LoginActivity extends BaseActivity<VMLogin, ActivityLoginBinding> implements DialogDismissListener<ResWarehouse> {
    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        viewBind.setVariable(BR.vmLogin, viewModel);
        registerMessageEvent(ToastUtils::showShort);
        registerStatusEvent(status -> {
            if (status == StatusEvent.Status.LOADING) {
                showLoading();
            } else {
                hideLoading();
            }
        });
        viewModel.getWarehouseLiveData().observe(this, list -> {
            if (list.size() == 1) {
                viewModel.login(list.get(0));
            } else {
                showDialogFragment(new DialogWarehouseSelect(list, this));
            }
        });
    }

    @Override
    public void onDialogSure(ResWarehouse data) {
        viewModel.login(data);
    }
}
