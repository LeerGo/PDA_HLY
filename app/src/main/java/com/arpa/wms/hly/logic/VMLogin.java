package com.arpa.wms.hly.logic;

import android.app.Application;
import android.text.TextUtils;

import com.arpa.and.arch.base.BaseModel;
import com.arpa.and.arch.base.livedata.StatusEvent;
import com.arpa.wms.hly.bean.req.ReqLogin;
import com.arpa.wms.hly.bean.req.ReqLoginSSO;
import com.arpa.wms.hly.bean.res.ResLogin;
import com.arpa.wms.hly.bean.res.ResWarehouse;
import com.arpa.wms.hly.logic.common.vm.VMWarehouse;
import com.arpa.wms.hly.logic.home.HomeActivity;
import com.arpa.wms.hly.net.callback.ResultCallback;
import com.arpa.wms.hly.net.exception.ResultError;
import com.arpa.wms.hly.utils.Const.SPKEY;
import com.arpa.wms.hly.utils.ToastUtils;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;
import dagger.hilt.android.lifecycle.HiltViewModel;

import static com.arpa.wms.hly.net.ApiService.API.isSSOMode;

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

    /**
     * 登录逻辑
     */
    public void doLogin() {
        if (isSSOMode) {
            loginSSO();
        } else {
            getWarehouseWithoutAuth();
        }
    }

    /**
     * SSO 单点登录
     */
    // TODO: 调整为先登录后获取仓库 @宝新 处理接口 @lyf 2021-06-04 10:20:46
    private void loginSSO() {
        ReqLoginSSO sso = new ReqLoginSSO(userName.get(), userPass.get());
        if (checkInput(userName.get(), userPass.get())) return;
        updateStatus(StatusEvent.Status.LOADING);
        apiService.loginSSO(sso.toParams())
                .enqueue(new ResultCallback<String>() {
                    @Override
                    public void onSuccess(String data) {
                        spPut(SPKEY.IS_NEW_USER, false);
                        spPut(SPKEY.TOKEN_SSO, data);
                        updateStatus(StatusEvent.Status.SUCCESS, true);
                    }

                    @Override
                    public void onFailed(ResultError error) {
                        sendMessage(error.getMessage());
                        updateStatus(StatusEvent.Status.ERROR, true);
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

    /**
     * 获取仓库
     */
    public void getWarehouseWithoutAuth() {
        String loginID = userName.get();
        String password = userPass.get();
        if (checkInput(loginID, password)) return;
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
    // FIXME: 切换 SSO 登陆后删除 @lyf 2021-06-04 10:18:16
    @Deprecated
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
                        spPut(SPKEY.TOKEN_WMS, data.getAccessToken());
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
