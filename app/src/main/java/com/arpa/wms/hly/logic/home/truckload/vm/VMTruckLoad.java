package com.arpa.wms.hly.logic.home.truckload.vm;

import android.app.Application;

import com.arpa.and.wms.arch.base.BaseModel;
import com.arpa.wms.hly.BR;
import com.arpa.wms.hly.R;
import com.arpa.wms.hly.base.WrapBindingRVAdapter;
import com.arpa.wms.hly.base.viewmodel.VMBaseRefreshList;
import com.arpa.wms.hly.bean.base.ReqPage;
import com.arpa.wms.hly.bean.base.ResultPage;
import com.arpa.wms.hly.bean.req.ReqTruckLoad;
import com.arpa.wms.hly.bean.res.ResTruckLoad;
import com.arpa.wms.hly.ui.listener.ViewListener;

import java.util.Map;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import dagger.hilt.android.lifecycle.HiltViewModel;
import me.tatarka.bindingcollectionadapter2.ItemBinding;
import retrofit2.Call;

/**
 * author: 李一方(<a href="mailto:leergo@dingtalk.com">leergo@dingtalk.com</a>)<br/>
 * version: 1.0.0<br/>
 * since: 2021-04-25 2:01 PM
 *
 * <p>
 * ViewModel：装车出厂（列表）
 * </p>
 */
@HiltViewModel
public class VMTruckLoad extends VMBaseRefreshList<ResTruckLoad> {
    private final ItemBinding<ResTruckLoad> itemBinding = ItemBinding.of(BR.data, R.layout.item_truck_load);
    private final ReqTruckLoad reqTruckLoad = new ReqTruckLoad(PAGE_SIZE);

    @Inject
    public VMTruckLoad(@NonNull Application application, BaseModel model) {
        super(application, model);
    }

    @Override
    public void configAdapter() {
        setAdapter(new WrapBindingRVAdapter<>());
    }

    @Override
    public Call<ResultPage<ResTruckLoad>> getCall(Map<String, Object> params) {
        return apiService.getTruckLoadList(params);
    }

    @Override
    public ReqPage getParams() {
        // TODO: 设置请求装车入参 @lyf 2021-05-12 09:40:11
        return reqTruckLoad;
    }

    @Override
    public ItemBinding<ResTruckLoad> getItemBinding() {
        itemBinding.bindExtra(BR.listener, (ViewListener.DataTransCallback<ResTruckLoad>) data -> {

        });
        return itemBinding;
    }
}
