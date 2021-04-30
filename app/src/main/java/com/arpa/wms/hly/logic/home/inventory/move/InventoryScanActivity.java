package com.arpa.wms.hly.logic.home.inventory.move;

import android.os.Bundle;

import com.arpa.and.wms.arch.base.BaseActivity;
import com.arpa.wms.hly.R;
import com.arpa.wms.hly.databinding.ActivityInventoryScanBinding;

import androidx.annotation.Nullable;

/**
 * author: 李一方(<a href="mailto:leergo@dingtalk.com">leergo@dingtalk.com</a>)<br/>
 * version: 1.0.0<br/>
 * since: 2021-04-22 3:30 PM
 *
 * <p>
 * 界面：扫描移除库位
 * </p>
 */
public abstract class InventoryScanActivity extends BaseActivity<VMInventoryScan, ActivityInventoryScanBinding> {

    @Override
    public int getLayoutId() {
        return R.layout.activity_inventory_scan;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        viewBind.setVmInventory(viewModel);
    }
}
