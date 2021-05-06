package com.arpa.wms.hly.logic.common.vm;

import android.app.Application;

import com.arpa.and.wms.arch.base.BaseModel;
import com.arpa.and.wms.arch.base.livedata.StatusEvent;
import com.arpa.and.wms.arch.http.callback.ApiCallback;
import com.arpa.wms.hly.base.viewmodel.WrapDataViewModel;
import com.arpa.wms.hly.bean.res.ResWarehouse;
import com.arpa.wms.hly.bean.base.Result;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import retrofit2.Call;

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
     * 获取仓库列表
     */
    public void getWarehouseWithoutAuth(String loginID) {
        updateStatus(StatusEvent.Status.LOADING);
        apiService.getWarehouseWithoutAuth(loginID)
                .enqueue(new ApiCallback<Result<List<ResWarehouse>>>() {
                    @Override
                    public void onResponse(Call<Result<List<ResWarehouse>>> call, Result<List<ResWarehouse>> result) {
                        if (result.isSuccess()) { //成功
                            updateStatus(StatusEvent.Status.SUCCESS, true);
                            warehouseLiveData.postValue(result.getData());
                        } else {
                            updateStatus(StatusEvent.Status.FAILURE, true);
                            sendMessage(result.getMsg(), true);
                        }
                    }

                    @Override
                    public void onError(Call<Result<List<ResWarehouse>>> call, Throwable t) {
                        updateStatus(StatusEvent.Status.ERROR, true);
                        sendMessage(t.getMessage(), true);
                    }
                });
    }

    public MutableLiveData<List<ResWarehouse>> getWarehouseLiveData() {
        return warehouseLiveData;
    }

}
