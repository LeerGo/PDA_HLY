package com.arpa.wms.hly.logic.common.vm;

import android.app.Application;

import com.arpa.and.arch.base.BaseModel;
import com.arpa.and.arch.base.livedata.StatusEvent.Status;
import com.arpa.wms.hly.R;
import com.arpa.wms.hly.base.viewmodel.WrapDataViewModel;
import com.arpa.wms.hly.bean.res.ResWarehouse;
import com.arpa.wms.hly.logic.home.HomeActivity;
import com.arpa.wms.hly.net.callback.ResultCallback;
import com.arpa.wms.hly.net.exception.ResultError;
import com.arpa.wms.hly.utils.Const;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

/**
 * author: 李一方(<a href="mailto:leergo@dingtalk.com">leergo@dingtalk.com</a>)<br/>
 * version: 1.0.0<br/>
 * since: 2021-04-28 11:11
 *
 * <p>
 * 内容描述区域
 * </p>
 */
public class VMWarehouse extends WrapDataViewModel {
    private final MutableLiveData<List<ResWarehouse>> warehouseLiveData = new MutableLiveData<>();

    public VMWarehouse(@NonNull Application application, BaseModel model) {
        super(application, model);
    }

    /**
     * 获取仓库列表 SSO
     */
    public void getWarehouseWithSSO() {
        apiService.getWarehouseWithSSO()
                .enqueue(new ResultCallback<List<ResWarehouse>>() {

                    @Override
                    public void onSuccess(List<ResWarehouse> data) {
                        warehouseLiveData.postValue(data);
                        updateStatus(Status.SUCCESS, true);
                    }

                    @Override
                    public void onFailed(ResultError error) {
                        sendMessage(error.getMessage(), true);
                        updateStatus(Status.ERROR, true);
                    }
                });
    }

    /**
     * 绑定仓库
     *
     * @param isFirstBind
     *         true - 第一次绑定仓库（登陆）
     */
    public void bindWarehouse(ResWarehouse warehouseSelect, boolean isFirstBind) {
        apiService.bindWarehouse(warehouseSelect.getCode())
                .enqueue(new ResultCallback<Object>() {
                    @Override
                    public void onSuccess(Object data) {
                        spPut(Const.SPKEY.WAREHOUSE_CODE, warehouseSelect.getCode());
                        spPut(Const.SPKEY.WAREHOUSE_NAME, warehouseSelect.getName());
                        updateStatus(Status.SUCCESS);

                        if (isFirstBind){
                            spPut(Const.SPKEY.IS_NEW_USER, false);
                            sendMessage(R.string.request_success);
                            startActivity(HomeActivity.class);
                            finish();
                        } else {
                            sendMessage("仓库修改成功");
                        }
                    }

                    @Override
                    public void onFailed(ResultError error) {
                        sendMessage(error.getMessage());
                        updateStatus(Status.ERROR);
                    }
                });
    }

    public MutableLiveData<List<ResWarehouse>> getWarehouseLiveData() {
        return warehouseLiveData;
    }
}
