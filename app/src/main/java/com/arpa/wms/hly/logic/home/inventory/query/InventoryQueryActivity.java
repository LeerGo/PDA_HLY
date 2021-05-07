package com.arpa.wms.hly.logic.home.inventory.query;

import android.os.Bundle;

import com.arpa.and.wms.arch.base.BaseActivity;
import com.arpa.wms.hly.R;
import com.arpa.wms.hly.databinding.ActivityInventoryQueryBinding;

import androidx.annotation.Nullable;
import dagger.hilt.android.AndroidEntryPoint;

/**
 * author: 李一方(<a href="mailto:leergo@dingtalk.com">leergo@dingtalk.com</a>)<br/>
 * version: 1.0.0<br/>
 * since: 2021-04-22 3:30 PM
 *
 * <p>
 * 界面：库存查询
 * </p>
 */
@AndroidEntryPoint
public class InventoryQueryActivity extends BaseActivity<VMInventoryQuery, ActivityInventoryQueryBinding> {
    @Override
    public int getLayoutId() {
        return R.layout.activity_inventory_query;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        viewBind.setViewModel(viewModel);
        viewBind.wsbLocation.setOnSearchClick(data -> {
            viewModel.reqInventory.setLocationName(data);
            viewModel.refresh();
        });
        viewBind.wsbGoodbar.setOnSearchClick(data -> {
            viewModel.reqInventory.setGoodsBarCode(data);
            viewModel.refresh();
        });
    }
}
