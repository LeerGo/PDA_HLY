package com.arpa.wms.hly.logic.mine;

import android.app.Application;
import android.content.Intent;
import android.util.Log;

import com.arpa.and.wms.arch.base.BaseModel;
import com.arpa.wms.hly.BuildConfig;
import com.arpa.wms.hly.base.viewmodel.WrapDataViewModel;
import com.arpa.wms.hly.logic.LoginActivity;
import com.arpa.wms.hly.utils.Const;
import com.arpa.wms.hly.utils.SPUtils;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;
import androidx.hilt.lifecycle.ViewModelInject;

/**
 * author: 李一方(<a href="mailto:leergo@dingtalk.com">leergo@dingtalk.com</a>)<br/>
 * version: 1.0.0<br/>
 * since: 2021-04-25 3:13 PM
 *
 * <p>
 * 内容描述区域
 * </p>
 */
public class VMMine extends WrapDataViewModel {
    private final ObservableField<String> account = new ObservableField<>();
    private final ObservableField<String> warehouse = new ObservableField<>();
    private final ObservableField<String> version = new ObservableField<>("v0.0.0");

    @ViewModelInject
    public VMMine(@NonNull Application application, BaseModel model) {
        super(application, model);
    }

    @Override
    public void onCreate() {
        super.onCreate();
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
    public void modifyPassword() {
        // TODO: 待实现 @lyf 2021-04-27 03:49:32
        Log.e("@@@@ L58", "VMMine:modifyPassword() -> 修改密码");
    }

    /**
     * 切换仓库
     */
    public void warehouseChange() {
        // TODO: 待实现 @lyf 2021-04-27 03:49:32
        Log.e("@@@@ L58", "VMMine:modifyPassword() -> 切换仓库");
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
