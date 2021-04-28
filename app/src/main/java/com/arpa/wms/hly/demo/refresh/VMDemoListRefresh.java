package com.arpa.wms.hly.demo.refresh;

import android.app.Application;
import android.util.Log;
import android.view.View;

import com.arpa.and.wms.arch.base.BaseModel;
import com.arpa.wms.hly.BR;
import com.arpa.wms.hly.R;
import com.arpa.wms.hly.base.viewmodel.VMBaseRefreshList;
import com.arpa.wms.hly.bean.ResWarehouse;
import com.arpa.wms.hly.bean.base.Result;
import com.arpa.wms.hly.demo.DemoListAdapter;
import com.arpa.wms.hly.ui.listener.ViewListener.OnItemClickListener;

import java.util.List;

import androidx.annotation.NonNull;
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
public class VMDemoListRefresh extends VMBaseRefreshList<ResWarehouse, DemoListAdapter> implements OnItemClickListener<ResWarehouse> {

    @ViewModelInject
    public VMDemoListRefresh(@NonNull Application application, BaseModel model) {
        super(application, model);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        autoRefresh();
    }

    @Override
    public void configAdapter() {
        setAdapter(new DemoListAdapter());
        getAdapter().setOnItemClickListener(this);
    }

    @Override
    public Call<Result<List<ResWarehouse>>> getCall() {
        return apiService.getWarehouseWithoutAuth("admin");
    }

    @Override
    public ItemBinding<ResWarehouse> getItemBinding() {
        return ItemBinding.of(BR.data, R.layout.item_demo_list2);
    }

    @Override
    public void onItemClick(View view, int position, ResWarehouse data) {
        Log.e("@@@@ L56", "VMDemoList:onItemClick() -> position = " + position + ", data.code = " + data.getCode());
    }
}
