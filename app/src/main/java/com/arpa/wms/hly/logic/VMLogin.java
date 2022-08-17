package com.arpa.wms.hly.logic;

import android.app.Application;
import android.text.TextUtils;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;

import com.arpa.and.arch.base.BaseModel;
import com.arpa.and.arch.base.livedata.StatusEvent.Status;
import com.arpa.wms.hly.BuildConfig;
import com.arpa.wms.hly.bean.req.ReqLoginSSO;
import com.arpa.wms.hly.logic.common.vm.VMWarehouse;
import com.arpa.wms.hly.net.callback.ResultCallback;
import com.arpa.wms.hly.net.exception.ResultError;
import com.arpa.wms.hly.utils.Const.SPKEY;
import com.arpa.wms.hly.utils.ToastUtils;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

/**
 * author: 李一方(<a href="mailto:leergo@dingtalk.com">leergo@dingtalk.com</a>)<br/>
 * version: 1.0.0<br/>
 * since: 2021-04-23 2:20 PM
 *
 * <p>
 * ViewModel：登录
 * </p>
 */
@HiltViewModel
public class VMLogin extends VMWarehouse {
    private final ObservableField<String> userName = new ObservableField<>();
    private final ObservableField<String> userPass = new ObservableField<>();
    private final ObservableField<Boolean> isShowPass = new ObservableField<>();

    @Inject
    public VMLogin(@NonNull Application application, BaseModel model) {
        super(application, model);
        if (BuildConfig.DEBUG) {
            userName.set("test02");
            userPass.set("test02");

            // userName.set("yangguihua");
            // userPass.set("abcd1234");

            // userName.set("yangguihua");
            // userPass.set("abcd1234  ");
        }
    }

    /**
     * 清理输入
     *
     * @param isUsername
     *         是否为用户名
     */
    public void clearInput(boolean isUsername) {
        if (isUsername) {
            userName.set("");
        } else {
            userPass.set("");
        }
    }

    /**
     * SSO 单点登录
     */
    public void loginSSO() {
        ReqLoginSSO sso = new ReqLoginSSO(userName.get(), userPass.get());
        if (checkInput(userName.get(), userPass.get())) return;
        updateStatus(Status.LOADING);
        apiService.loginSSO(sso.toParams())
                .enqueue(new ResultCallback<String>() {
                    @Override
                    public void onSuccess(String data) {
                        spPut(SPKEY.TOKEN_SSO, data);
                        spPut(SPKEY.USER_NAME, userName.get());
                        // 以 SSO 形式获仓库列表
                        VMLogin.super.getWarehouseWithSSO();
                    }

                    @Override
                    public void onFailed(ResultError error) {
                        sendMessage(error.getMessage());
                        updateStatus(Status.ERROR, true);
                    }
                });
    }

    /**
     * 检查输入
     */
    private boolean checkInput(String loginID, String password) {
        if (TextUtils.isEmpty(loginID)) {
            ToastUtils.showShort("请填写账号信息");
            return true;
        }
        if (TextUtils.isEmpty(password)) {
            ToastUtils.showShort("请填写密码");
            return true;
        }
        return false;
    }

    public void showPass(boolean isShowPass) {
        this.isShowPass.set(isShowPass);
    }


    public ObservableField<String> getUserName() {
        return userName;
    }

    public ObservableField<String> getUserPass() {
        return userPass;
    }

    public ObservableField<Boolean> getIsShowPass() {
        return isShowPass;
    }
}
