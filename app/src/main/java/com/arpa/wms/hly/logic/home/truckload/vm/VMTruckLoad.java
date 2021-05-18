package com.arpa.wms.hly.logic.home.truckload.vm;

import android.app.Application;
import android.os.Bundle;

import com.arpa.and.wms.arch.base.BaseModel;
import com.arpa.wms.hly.BR;
import com.arpa.wms.hly.R;
import com.arpa.wms.hly.base.WrapBindingRVAdapter;
import com.arpa.wms.hly.base.viewmodel.VMBaseRefreshList;
import com.arpa.wms.hly.bean.base.ReqPage;
import com.arpa.wms.hly.bean.base.ResultPage;
import com.arpa.wms.hly.bean.req.ReqTruckLoad;
import com.arpa.wms.hly.bean.res.ResTaskAssign;
import com.arpa.wms.hly.logic.home.truckload.TruckLoadDetailActivity;
import com.arpa.wms.hly.ui.listener.ViewListener;
import com.arpa.wms.hly.utils.Const.IntentKey;

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
public class VMTruckLoad extends VMBaseRefreshList<ResTaskAssign> {
    private final ItemBinding<ResTaskAssign> itemBinding = ItemBinding.of(BR.data, R.layout.item_truck_load);
    private final ReqTruckLoad reqTruckLoad = new ReqTruckLoad(PAGE_SIZE);

    @Inject
    public VMTruckLoad(@NonNull Application application, BaseModel model) {
        super(application, model);
    }

    @Override
    public Call<ResultPage<ResTaskAssign>> getCall(Map<String, Object> params) {
        return apiService.pdaTasks(params);
    }

    @Override
    public ReqPage getParams() {
        return reqTruckLoad;
    }

    @Override
    public void configAdapter() {
        setAdapter(new WrapBindingRVAdapter<>());
    }

    @Override
    public ItemBinding<ResTaskAssign> getItemBinding() {
        itemBinding.bindExtra(BR.listener, (ViewListener.DataTransCallback<ResTaskAssign>) data -> {
            Bundle bundle = new Bundle();
            bundle.putParcelable(IntentKey.DATA, data);
            startActivity(TruckLoadDetailActivity.class, bundle);
        });
        return itemBinding;
    }
}
