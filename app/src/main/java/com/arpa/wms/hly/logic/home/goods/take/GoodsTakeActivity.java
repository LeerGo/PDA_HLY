package com.arpa.wms.hly.logic.home.goods.take;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.arpa.wms.hly.R;
import com.arpa.wms.hly.base.WrapBaseActivity;
import com.arpa.wms.hly.databinding.ActivityPdataskTakeBinding;
import com.arpa.wms.hly.logic.home.goods.take.vm.VMGoodsTake;
import com.arpa.wms.hly.ui.decoration.BothItemDecoration;

import dagger.hilt.android.AndroidEntryPoint;

/**
 * author: 李一方(<a href="mailto:leergo@dingtalk.com">leergo@dingtalk.com</a>)<br/>
 * version: 1.0.0<br/>
 * since: 2021-04-25 2:01 PM
 *
 * <p>
 * 页面：商品待收货列表
 * </p>
 */
@AndroidEntryPoint
public class GoodsTakeActivity extends WrapBaseActivity<VMGoodsTake, ActivityPdataskTakeBinding> {
    @Override
    public int getLayoutId() {
        return R.layout.activity_pdatask_take;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        super.initData(savedInstanceState);

        viewBind.setViewModel(viewModel);
        viewBind.rvList.addItemDecoration(new BothItemDecoration());
        viewBind.etScan.setOnSearchClick(data -> viewModel.search(data));
    }
}
