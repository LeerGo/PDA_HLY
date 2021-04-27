package com.arpa.wms.hly.logic;

import android.annotation.SuppressLint;
import android.app.Application;
import android.text.TextUtils;

import com.arpa.and.wms.arch.base.BaseModel;
import com.arpa.and.wms.arch.base.livedata.StatusEvent;
import com.arpa.and.wms.arch.http.callback.ApiCallback;
import com.arpa.wms.hly.R;
import com.arpa.wms.hly.base.viewmodel.WrapDataViewModel;
import com.arpa.wms.hly.bean.ResLogin;
import com.arpa.wms.hly.bean.ResWarehouse;
import com.arpa.wms.hly.bean.Result;
import com.arpa.wms.hly.logic.home.HomeActivity;
import com.arpa.wms.hly.utils.Const.AppConfig;
import com.arpa.wms.hly.utils.Const.SPKEY;
import com.arpa.wms.hly.utils.Md5Utils;
import com.arpa.wms.hly.utils.ToastUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;
import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.MutableLiveData;
import retrofit2.Call;

/**
 * author: 李一方(<a href="mailto:leergo@dingtalk.com">leergo@dingtalk.com</a>)<br/>
 * version: 1.0.0<br/>
 * since: 2021-04-23 2:20 PM
 *
 * <p>
 * 内容描述区域
 * </p>
 */
public class VMLogin extends WrapDataViewModel {
    public final ObservableField<String> userName = new ObservableField<>();
    public final ObservableField<String> userPass = new ObservableField<>();
    public final ObservableField<Boolean> isShowPass = new ObservableField<>();
    protected final MutableLiveData<List<ResWarehouse>> warehouseLiveData = new MutableLiveData<>();

    @ViewModelInject
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
        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
        String time = df.format(new Date());

        HashMap<String, String> params = new HashMap<>();
        params.put("client_id", AppConfig.clientID);
        params.put("client_secret", AppConfig.clientSecret);
        params.put("grant_type", AppConfig.grantType);
        params.put("response_type", AppConfig.responseType);
        params.put("device_id", spGetString(SPKEY.DEVICE_ID));
        params.put("username", userName.get());
        params.put("password", Md5Utils.getBase64(userPass.get() + "_arpa_" + time));
        params.put("time", time);
        params.put("authorizeDataCode", warehouseSelect.getCode());
        apiService.authorize(params)
                .enqueue(new ApiCallback<Result<ResLogin>>() {
                    @Override
                    public void onResponse(Call<Result<ResLogin>> call, Result<ResLogin> result) {
                        // TODO: 记得下面这行注释放开 @lyf 2021-04-27 02:29:55
                        // spPut(SPKEY.IS_NEW_USER, false);
                        spPut(SPKEY.WAREHOUSE_CODE, warehouseSelect.getCode());
                        spPut(SPKEY.WAREHOUSE_NAME, warehouseSelect.getName());
                        ResLogin data = result.getData();
                        spPut(SPKEY.USER_TOKEN, data.getAccessToken());
                        spPut(SPKEY.PARTY_TYPE, data.getParty().getPartyType());
                        spPut(SPKEY.OPERATOR_NAME, data.getParty().getName());
                        spPut(SPKEY.OPERATOR_CODE, data.getParty().getCode());
                        startActivity(HomeActivity.class);
                    }

                    @Override
                    public void onError(Call<Result<ResLogin>> call, Throwable t) {

                    }
                });
    }

    /**
     * 获取仓库列表
     */
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

        updateStatus(StatusEvent.Status.LOADING);
        apiService.getWarehouseWithoutAuth(loginID)
                .enqueue(new ApiCallback<Result<List<ResWarehouse>>>() {
                    @Override
                    public void onResponse(Call<Result<List<ResWarehouse>>> call, Result<List<ResWarehouse>> result) {
                        if (result != null) {
                            if (result.isSuccess()) { //成功
                                updateStatus(StatusEvent.Status.SUCCESS, true);
                                warehouseLiveData.postValue(result.getData());
                                return;
                            }
                            warehouseLiveData.postValue(new ArrayList<>());
                            updateStatus(StatusEvent.Status.FAILURE, true);
                            sendMessage(result.getMsg(), true);
                        } else {
                            updateStatus(StatusEvent.Status.FAILURE, true);
                            sendMessage(R.string.failure_result_common, true);
                        }
                    }

                    @Override
                    public void onError(Call<Result<List<ResWarehouse>>> call, Throwable t) {
                        updateStatus(StatusEvent.Status.ERROR, true);
                        sendMessage(t.getMessage(), true);
                    }
                });
    }
}
