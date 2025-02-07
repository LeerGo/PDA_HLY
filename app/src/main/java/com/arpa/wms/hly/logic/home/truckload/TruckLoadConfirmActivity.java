package com.arpa.wms.hly.logic.home.truckload;

import android.os.Bundle;

import com.arpa.wms.hly.R;
import com.arpa.wms.hly.base.WrapBaseActivity;
import com.arpa.wms.hly.databinding.ActivityTruckLoadConfirmBinding;
import com.arpa.wms.hly.logic.home.truckload.vm.VMTruckLoadConfirm;
import com.arpa.wms.hly.ui.decoration.BothItemDecoration;
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
public class TruckLoadConfirmActivity extends WrapBaseActivity<VMTruckLoadConfirm, ActivityTruckLoadConfirmBinding> {

    @Override
    public int getLayoutId() {
        return R.layout.activity_truck_load_confirm;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        viewBind.setViewModel(viewModel);
        viewModel.headerData = getIntent().getParcelableExtra(IntentKey.DATA);
        viewBind.rvList.addItemDecoration(new BothItemDecoration(true));
    }
}
