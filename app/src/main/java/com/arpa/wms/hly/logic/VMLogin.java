package com.arpa.wms.hly.logic;

import android.app.Application;
import android.text.TextUtils;

import com.arpa.and.wms.arch.base.BaseModel;
import com.arpa.and.wms.arch.base.livedata.StatusEvent;
import com.arpa.wms.hly.bean.req.ReqLogin;
import com.arpa.wms.hly.bean.res.ResLogin;
import com.arpa.wms.hly.bean.res.ResWarehouse;
import com.arpa.wms.hly.logic.common.vm.VMWarehouse;
import com.arpa.wms.hly.logic.home.HomeActivity;
import com.arpa.wms.hly.net.ResultCallback;
import com.arpa.wms.hly.net.exception.ResultError;
import com.arpa.wms.hly.utils.Const.SPKEY;
import com.arpa.wms.hly.utils.ToastUtils;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;
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
    // TODO: 这里的默认值记得干掉 @lyf 2021-04-27 03:41:59
    private final ObservableField<String> userName = new ObservableField<>("admin");
    private final ObservableField<String> userPass = new ObservableField<>("abcd1234");
    private final ObservableField<Boolean> isShowPass = new ObservableField<>();

    @Inject
    public VMLogin(@NonNull Application application, BaseModel model) {
        super(application, model);
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

    public void getWarehouseWithoutAuth() {
        String loginID = userName.get();
        String password = userPass.get();
        if (TextUtils.isEmpty(loginID)) {
            ToastUtils.showShort("请填写账号信息");
            return;
        }
        if (TextUtils.isEmpty(password)) {
            ToastUtils.showShort("请填写密码");
            return;
        }
        super.getWarehouseWithoutAuth(loginID);
    }

    public void showPass(boolean isShowPass) {
        this.isShowPass.set(isShowPass);
    }

    /**
     * 登录功能
     *
     * @param warehouseSelect
     *         选择的仓库
     */
    public void login(ResWarehouse warehouseSelect) {
        updateStatus(StatusEvent.Status.LOADING);
        ReqLogin reqLogin = new ReqLogin();
        reqLogin.setPassword(userPass.get());
        reqLogin.setUsername(userName.get());
        reqLogin.setAuthorizeDataCode(warehouseSelect.getCode());

        apiService.authorize(reqLogin.toParams())
                .enqueue(new ResultCallback<ResLogin>() {

                    @Override
                    public void onSuccess(ResLogin data) {
                        spPut(SPKEY.IS_NEW_USER, false);
                        spPut(SPKEY.USER_NAME, userName.get());
                        spPut(SPKEY.WAREHOUSE_CODE, warehouseSelect.getCode());
                        spPut(SPKEY.WAREHOUSE_NAME, warehouseSelect.getName());
                        spPut(SPKEY.USER_TOKEN, data.getAccessToken());
                        spPut(SPKEY.PARTY_TYPE, data.getParty().getPartyType());
                        spPut(SPKEY.OPERATOR_NAME, data.getParty().getName());
                        spPut(SPKEY.OPERATOR_CODE, data.getParty().getCode());
                        updateStatus(StatusEvent.Status.SUCCESS, true);
                        startActivity(HomeActivity.class);
                        finish();
                    }

                    @Override
                    public void onFailed(ResultError error) {
                        updateStatus(StatusEvent.Status.ERROR, true);
                        sendMessage(error.getMessage(), true);
                    }
                });
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
