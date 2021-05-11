package com.arpa.wms.hly.logic.home.inventory.move;

import android.os.Bundle;

import com.arpa.wms.hly.R;
import com.arpa.wms.hly.base.WrapBaseActivity;
import com.arpa.wms.hly.databinding.ActivityScanGoodsDetailBinding;
import com.arpa.wms.hly.logic.home.inventory.move.vm.VMScanGoodsDetail;
import com.arpa.wms.hly.ui.decoration.ItemDecorationUtil;
import com.arpa.wms.hly.utils.Const;

import androidx.annotation.Nullable;
import dagger.hilt.android.AndroidEntryPoint;

/**
 * author: 李一方(<a href="mailto:leergo@dingtalk.com">leergo@dingtalk.com</a>)<br/>
 * version: 1.0.0<br/>
 * since: 2021-04-22 3:30 PM
 *
 * <p>
 * 页面：移位商品详情
 * </p>
 */
@AndroidEntryPoint
public class ScanGoodsDetailActivity extends WrapBaseActivity<VMScanGoodsDetail, ActivityScanGoodsDetailBinding> {

    @Override
    public int getLayoutId() {
        return R.layout.activity_scan_goods_detail;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        viewBind.setViewModel(viewModel);
        viewBind.rvList.addItemDecoration(ItemDecorationUtil.getDividerBottom10DP());

        String container = getIntent().getStringExtra(Const.IntentKey.CONTAINER_CODE);
        String location = getIntent().getStringExtra(Const.IntentKey.LOCATION_NAME);
        String goodsbar = getIntent().getStringExtra(Const.IntentKey.GOODS_BAR_CODE);
        viewModel.getScanGoodsList(goodsbar, location, container);
    }
}
