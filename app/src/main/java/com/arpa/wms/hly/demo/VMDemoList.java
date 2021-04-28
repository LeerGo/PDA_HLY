package com.arpa.wms.hly.demo;

import android.app.Application;
import android.util.Log;
import android.view.View;

import com.arpa.and.wms.arch.base.BaseModel;
import com.arpa.and.wms.arch.base.DataViewModel;
import com.arpa.and.wms.arch.base.livedata.StatusEvent;
import com.arpa.and.wms.arch.http.callback.ApiCallback;
import com.arpa.wms.hly.BR;
import com.arpa.wms.hly.R;
import com.arpa.wms.hly.bean.ResWarehouse;
import com.arpa.wms.hly.bean.base.Result;
import com.arpa.wms.hly.net.ApiService;
import com.arpa.wms.hly.ui.listener.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableList;
import androidx.hilt.lifecycle.ViewModelInject;
import me.tatarka.bindingcollectionadapter2.ItemBinding;
import retrofit2.Call;

/**
 * author: 李一方(<a href="mailto:leergo@dingtalk.com">leergo@dingtalk.com</a>)<br/>
 * version: 1.0.0<br/>
 * since: 2021-04-26 9:35 AM
 *
 * <p>
 * 内容描述区域
 * </p>
 */
public class VMDemoList extends DataViewModel implements OnItemClickListener<ResWarehouse> {
    public final DemoListAdapter adapter = new DemoListAdapter();
    public final ObservableList<ResWarehouse> items = new ObservableArrayList<>();
    public final ItemBinding<ResWarehouse> itemBinding = ItemBinding.of(BR.data, R.layout.item_demo_list2);
    protected final ApiService apiService = getRetrofitService(ApiService.class);

    @ViewModelInject
    public VMDemoList(@NonNull Application application, BaseModel model) {
        super(application, model);
        setAdapter();
        refreshData();
    }

    private void setAdapter() {
        adapter.setOnItemClickListener(this);
    }

    public void refreshData() {
        updateStatus(StatusEvent.Status.LOADING);
        apiService.getWarehouseWithoutAuth("admin")
                .enqueue(new ApiCallback<Result<List<ResWarehouse>>>() {
                    @Override
                    public void onResponse(Call<Result<List<ResWarehouse>>> call, Result<List<ResWarehouse>> result) {
                        if (result != null) {
                            if (result.isSuccess()) { //成功
                                updateStatus(StatusEvent.Status.SUCCESS, true);
                                items.addAll(result.getData());
                                return;
                            }
                            items.addAll(new ArrayList<>());
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

    @Override
    public void onItemClick(View view, int position, ResWarehouse data) {
        Log.e("@@@@ L56", "VMDemoList:onItemClick() -> position = " + position + ", data.code = " + data.getCode());
    }
}
