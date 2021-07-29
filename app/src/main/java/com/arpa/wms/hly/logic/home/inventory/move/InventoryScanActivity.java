package com.arpa.wms.hly.logic.home.inventory.move;

import android.os.Bundle;

import com.arpa.wms.hly.R;
import com.arpa.wms.hly.base.WrapBaseActivity;
import com.arpa.wms.hly.databinding.ActivityInventoryScanBinding;
import com.arpa.wms.hly.logic.home.inventory.move.vm.VMInventoryScan;
import com.arpa.wms.hly.ui.listener.ViewListener;

import androidx.annotation.Nullable;

/**
 * author: 李一方(<a href="mailto:leergo@dingtalk.com">leergo@dingtalk.com</a>)<br/>
 * version: 1.0.0<br/>
 * since: 2021-04-22 3:30 PM
 *
 * <p>
 * 界面：扫描移除库位
 * </p>relskw
 */
public abstract class InventoryScanActivity extends WrapBaseActivity<VMInventoryScan, ActivityInventoryScanBinding>
        implements ViewListener.DataTransCallback<String> {

    @Override
    public int getLayoutId() {
        return R.layout.activity_inventory_scan;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        viewBind.setVmInventory(viewModel);
        viewBind.etScan.setOnSearchClick(this);
    }
}
