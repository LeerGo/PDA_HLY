package com.arpa.wms.hly.logic.home.inventory.move;

import android.os.Bundle;

import com.arpa.wms.hly.R;

import androidx.annotation.Nullable;
import dagger.hilt.android.AndroidEntryPoint;

/**
 * author: 李一方(<a href="mailto:leergo@dingtalk.com">leergo@dingtalk.com</a>)<br/>
 * version: 1.0.0<br/>
 * since: 2021-04-22 3:30 PM
 *
 * <p>
 * 页面：扫描移位库位
 * </p>
 */
@AndroidEntryPoint
public class ScanLocationActivity extends InventoryScanActivity {

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        viewModel.title.set("扫描移出库位");
        viewModel.searchHint.set(getResources().getString(R.string.hint_please_enter_location));
    }
}
