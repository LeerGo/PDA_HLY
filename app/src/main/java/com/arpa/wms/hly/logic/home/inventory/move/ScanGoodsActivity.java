package com.arpa.wms.hly.logic.home.inventory.move;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.arpa.wms.hly.R;
import com.arpa.wms.hly.utils.Const.IntentKey;

import dagger.hilt.android.AndroidEntryPoint;

/**
 * author: 李一方(<a href="mailto:leergo@dingtalk.com">leergo@dingtalk.com</a>)<br/>
 * version: 1.0.0<br/>
 * since: 2021-04-22 3:30 PM
 *
 * <p>
 * 页面：扫描移位商品
 * </p>
 */
@Deprecated
@AndroidEntryPoint
public class ScanGoodsActivity extends InventoryScanActivity {

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        super.initData(savedInstanceState);

        viewModel.title.set("扫描移位商品");
        viewModel.searchHint.set(getResources().getString(R.string.hint_please_enter_goodsbar));
    }

    @Override
    public void transfer(String goodsBar) {
        String location = getIntent().getStringExtra(IntentKey.LOCATION_NAME);
        String container = getIntent().getStringExtra(IntentKey.CONTAINER_CODE);
        viewModel.scanGoods(goodsBar, location, container);
    }
}
