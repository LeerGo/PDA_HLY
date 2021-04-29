package com.arpa.wms.hly.logic.home.goods.take;

import android.os.Bundle;

import com.arpa.and.wms.arch.base.BaseActivity;
import com.arpa.wms.hly.R;
import com.arpa.wms.hly.databinding.ActivityGoodsTakeBinding;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DividerItemDecoration;
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
public class GoodsTakeActivity extends BaseActivity<VMGoodsTake, ActivityGoodsTakeBinding> {
    @Override
    public int getLayoutId() {
        return R.layout.activity_goods_take;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        viewBind.setViewModel(viewModel);
        viewBind.wsbSearch.setOnSearchClick(data -> viewModel.search(data));
        DividerItemDecoration itemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        itemDecoration.setDrawable(getResources().getDrawable(R.drawable.divider_line_vertical_10dp));
        viewBind.rvList.addItemDecoration(itemDecoration);
    }
}
