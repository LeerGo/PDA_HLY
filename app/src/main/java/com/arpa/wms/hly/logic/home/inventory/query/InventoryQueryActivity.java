package com.arpa.wms.hly.logic.home.inventory.query;

import android.os.Bundle;

import com.arpa.wms.hly.R;
import com.arpa.wms.hly.base.WrapBaseActivity;
import com.arpa.wms.hly.databinding.ActivityInventoryQueryBinding;
import com.arpa.wms.hly.ui.decoration.ItemDecorationUtil;

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
public class InventoryQueryActivity extends WrapBaseActivity<VMInventoryQuery, ActivityInventoryQueryBinding> {
    @Override
    public int getLayoutId() {
        return R.layout.activity_inventory_query;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        super.initData(savedInstanceState);

        viewBind.setViewModel(viewModel);
        viewBind.rvList.addItemDecoration(ItemDecorationUtil.getDividerBottom10DP());
        // TODO: 先临时这样，后面改为 xml 映射 @lyf 2021-05-08 07:51:32
        viewBind.wsbGoodbar.setOnSearchClick(data -> viewModel.filter(data, false));
        viewBind.wsbGoodbar.setOnClearClick(v -> viewModel.filter("", false));
        viewBind.wsbLocation.setOnSearchClick(data -> viewModel.filter(data, true));
        viewBind.wsbLocation.setOnClearClick(v -> viewModel.filter("", true));
    }
}