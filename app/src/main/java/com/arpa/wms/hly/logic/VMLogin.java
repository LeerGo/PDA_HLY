package com.arpa.wms.hly.logic;

import android.app.Application;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.arpa.and.wms.arch.base.BaseModel;
import com.arpa.and.wms.arch.base.DataViewModel;
import com.arpa.and.wms.arch.base.livedata.StatusEvent;
import com.arpa.and.wms.arch.http.callback.ApiCallback;
import com.arpa.wms.hly.R;
import com.arpa.wms.hly.bean.ResWarehouse;
import com.arpa.wms.hly.bean.Result;
import com.arpa.wms.hly.logic.home.HomeActivity;
import com.arpa.wms.hly.net.ApiService;

import java.util.ArrayList;
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
public class VMLogin extends DataViewModel {
    public final ObservableField<String> userName = new ObservableField<>("");
    public final ObservableField<String> userPass = new ObservableField<>("");
    protected final MutableLiveData<List<ResWarehouse>> warehouseLiveData = new MutableLiveData<>();

    @ViewModelInject
    public VMLogin(@NonNull Application application, BaseModel model) {
        super(application, model);
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    public void login() {
        Log.e("@@@@ L51", "VMLogin:login() -> --------------------");
        //        getWarehouseWithoutAuth(userName.get());
        startActivity(HomeActivity.class);
    }

    public void getWarehouseWithoutAuth(String loginID) {
        if (TextUtils.isEmpty(loginID)) {
            Toast.makeText(getApplication(), "请填写账号信息", Toast.LENGTH_SHORT).show();
            return;
        }

        updateStatus(StatusEvent.Status.LOADING);
        getRetrofitService(ApiService.class)
                .getWarehouseWithoutAuth(loginID)
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
                            sendMessage(R.string.result_failure, true);
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
