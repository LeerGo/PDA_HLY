package com.arpa.wms.hly.logic.mine;

import android.app.Application;
import android.content.Intent;

import com.arpa.and.arch.base.BaseModel;
import com.arpa.and.arch.base.livedata.StatusEvent.Status;
import com.arpa.wms.hly.BuildConfig;
import com.arpa.wms.hly.base.viewmodel.WrapDataViewModel;
import com.arpa.wms.hly.bean.req.ReqModifyPass;
import com.arpa.wms.hly.logic.LoginActivity;
import com.arpa.wms.hly.logic.common.vm.VMWarehouse;
import com.arpa.wms.hly.net.callback.ResultCallback;
import com.arpa.wms.hly.net.exception.ResultError;
import com.arpa.wms.hly.utils.Const;
import com.arpa.wms.hly.utils.SPUtils;
import com.arpa.wms.hly.utils.Utils;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;
import dagger.hilt.android.lifecycle.HiltViewModel;

/**
 * author: 李一方(<a href="mailto:leergo@dingtalk.com">leergo@dingtalk.com</a>)<br/>
 * version: 1.0.0<br/>
 * since: 2021-04-25 3:13 PM
 *
 * <p>
 * ViewModel：我的
 * </p>
 */
@HiltViewModel
public class VMMine extends WrapDataViewModel {
    public final VMWarehouse vmWarehouse;
    private final ObservableField<String> account = new ObservableField<>();
    private final ObservableField<String> warehouse = new ObservableField<>();
    private final ObservableField<String> version = new ObservableField<>();

    @Inject
    public VMMine(@NonNull Application application, BaseModel model) {
        super(application, model);
        vmWarehouse = new VMWarehouse(application, model);
    }

    @Override
    public void onStart() {
        super.onStart();

        initInfo();
    }

    private void initInfo() {
        version.set("v" + BuildConfig.VERSION_NAME);
        account.set(spGetString(Const.SPKEY.USER_NAME));
        warehouse.set(spGetString(Const.SPKEY.WAREHOUSE_NAME));
    }

    /**
     * 退出登录
     */
    public void logout() {
        SPUtils.getInstance().clear();
        startActivity(LoginActivity.class, Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        finish();
    }

    /**
     * 修改密码
     */
    public void modifyPassword(ReqModifyPass data) {
        updateStatus(Status.LOADING);
        apiService.updatePass(data.toParams())
                .enqueue(new ResultCallback<Object>() {
                    @Override
                    public void onSuccess(Object data) {
                        updateStatus(Status.SUCCESS, true);
                        sendMessage("修改密码成功");
                        Intent intent = new Intent(Utils.getContext(), LoginActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                        Utils.getContext().startActivity(intent);
                    }

                    @Override
                    public void onFailed(ResultError error) {
                        sendMessage(error.getMessage(), true);
                        updateStatus(Status.ERROR, true);
                    }
                });
    }

    /**
     * 切换仓库
     */
    public void getWarehouseList() {
        updateStatus(Status.LOADING);
        vmWarehouse.getWarehouseWithSSO();
    }

    public ObservableField<String> getAccount() {
        return account;
    }

    public ObservableField<String> getWarehouse() {
        return warehouse;
    }

    public ObservableField<String> getVersion() {
        return version;
    }
}
