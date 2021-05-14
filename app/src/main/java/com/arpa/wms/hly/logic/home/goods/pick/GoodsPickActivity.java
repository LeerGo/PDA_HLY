package com.arpa.wms.hly.logic.home.goods.pick;

import android.os.Bundle;

import com.arpa.wms.hly.R;
import com.arpa.wms.hly.base.WrapBaseActivity;
import com.arpa.wms.hly.databinding.ActivityGoodsPickBinding;

import androidx.annotation.Nullable;
import dagger.hilt.android.AndroidEntryPoint;

/**
 * author: 李一方(<a href="mailto:leergo@dingtalk.com">leergo@dingtalk.com</a>)<br/>
 * version: 1.0.0<br/>
 * since: 2021-05-14 16:07
 *
 * <p>
 * 页面：拣货
 * </p>
 */
@AndroidEntryPoint
public class GoodsPickActivity extends WrapBaseActivity<VMGoodsPick, ActivityGoodsPickBinding> {
    @Override
    public int getLayoutId() {
        return R.layout.activity_goods_pick;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        viewBind.setViewModel(viewModel);
    }
}
