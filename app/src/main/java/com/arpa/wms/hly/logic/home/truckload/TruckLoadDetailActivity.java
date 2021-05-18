package com.arpa.wms.hly.logic.home.truckload;

import android.os.Bundle;

import com.arpa.wms.hly.R;
import com.arpa.wms.hly.base.WrapBaseActivity;
import com.arpa.wms.hly.databinding.ActivityTruckLoadDetailBinding;
import com.arpa.wms.hly.logic.home.truckload.vm.VMTruckLoadDetail;
import com.arpa.wms.hly.ui.decoration.ItemDecorationUtil;
import com.arpa.wms.hly.utils.Const.IntentKey;

import androidx.annotation.Nullable;
import dagger.hilt.android.AndroidEntryPoint;

/**
 * author: 李一方(<a href="mailto:leergo@dingtalk.com">leergo@dingtalk.com</a>)<br/>
 * version: 1.0.0<br/>
 * since: 2021-04-25 2:01 PM
 *
 * <p>
 * 界面：装车出厂（列表）
 * </p>
 */
@AndroidEntryPoint
public class TruckLoadDetailActivity extends WrapBaseActivity<VMTruckLoadDetail, ActivityTruckLoadDetailBinding> {

    @Override
    public int getLayoutId() {
        return R.layout.activity_truck_load_detail;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        viewBind.setViewModel(viewModel);
        viewModel.truckLoadHeader.set(getIntent().getParcelableExtra(IntentKey.DATA));
        viewBind.rvList.addItemDecoration(ItemDecorationUtil.getDividerBottom10DP());
    }
}
