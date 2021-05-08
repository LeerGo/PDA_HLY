package com.arpa.wms.hly.logic.home.inventory.query;

import android.app.Application;

import com.arpa.and.wms.arch.base.BaseModel;
import com.arpa.wms.hly.BR;
import com.arpa.wms.hly.R;
import com.arpa.wms.hly.base.viewmodel.VMBaseRefreshList;
import com.arpa.wms.hly.bean.base.ReqPage;
import com.arpa.wms.hly.bean.base.ResultPage;
import com.arpa.wms.hly.bean.req.ReqInventory;
import com.arpa.wms.hly.bean.res.ResInventory;

import java.util.Map;

import androidx.annotation.NonNull;
import androidx.hilt.lifecycle.ViewModelInject;
import me.tatarka.bindingcollectionadapter2.ItemBinding;
import retrofit2.Call;

/**
 * author: 李一方(<a href="mailto:leergo@dingtalk.com">leergo@dingtalk.com</a>)<br/>
 * version: 1.0.0<br/>
 * since: 2021-04-22 3:30 PM
 *
 * <p>
 * ViewModel：库存查询
 * </p>
 */
public class VMInventoryQuery extends VMBaseRefreshList<ResInventory> {
    private final ItemBinding<ResInventory> itemBinding = ItemBinding.of(BR.data, R.layout.item_inventory_query);
    public final ReqInventory reqInventory = new ReqInventory(PAGE_SIZE);

    @ViewModelInject
    public VMInventoryQuery(@NonNull Application application, BaseModel model) {
        super(application, model);
    }

    @Override
    public void onCreate() {
        configAdapter();
    }

    @Override
    public Call<ResultPage<ResInventory>> getCall(Map<String, Object> params) {
        return apiService.inventoryQuery(params);
    }

    @Override
    public ReqPage getParams() {
        return reqInventory;
    }

    @Override
    public ItemBinding<ResInventory> getItemBinding() {
        return itemBinding;
    }
}
