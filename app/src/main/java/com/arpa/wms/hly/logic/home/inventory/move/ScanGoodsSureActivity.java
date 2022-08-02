package com.arpa.wms.hly.logic.home.inventory.move;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.arpa.wms.hly.R;
import com.arpa.wms.hly.base.WrapBaseActivity;
import com.arpa.wms.hly.databinding.ActivityScanGoodsSureBinding;
import com.arpa.wms.hly.logic.home.inventory.move.vm.VMScanGoodsSure;

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
@AndroidEntryPoint
public class ScanGoodsSureActivity extends WrapBaseActivity<VMScanGoodsSure, ActivityScanGoodsSureBinding> {

    @Override
    public int getLayoutId() {
        return R.layout.activity_scan_goods_sure;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        super.initData(savedInstanceState);

        viewBind.setViewModel(viewModel);
        viewBind.wsbSearch.setOnSearchClick(data -> viewModel.searchGoodsOnLocation(data));
    }
}
