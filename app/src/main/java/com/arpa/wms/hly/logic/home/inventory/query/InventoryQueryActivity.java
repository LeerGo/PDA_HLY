package com.arpa.wms.hly.logic.home.inventory.query;

import android.os.Bundle;

import com.arpa.wms.hly.R;
import com.arpa.wms.hly.base.WrapBaseActivity;
import com.arpa.wms.hly.databinding.ActivityInventoryQueryBinding;
import com.arpa.wms.hly.ui.decoration.BothItemDecoration;
import com.arpa.wms.hly.ui.listener.SimpleTextWatcher;

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
        viewBind.rvList.addItemDecoration(new BothItemDecoration());
        viewBind.wsbGoodbar.setWatcher(new SimpleTextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                viewModel.setKeyWord(s.toString(), false);
            }
        });
        viewBind.wsbLocation.setWatcher(new SimpleTextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                viewModel.setKeyWord(s.toString(), true);
            }
        });
        viewBind.wsbGoodbar.setOnSearchClick(data -> viewModel.refresh());
        viewBind.wsbLocation.setOnSearchClick(data -> viewModel.refresh());
        viewBind.wsbGoodbar.setOnClearClick(v -> viewModel.refresh());
        viewBind.wsbLocation.setOnClearClick(v -> viewModel.refresh());
    }
}