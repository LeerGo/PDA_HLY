package com.arpa.wms.hly.logic.home.goods.recheck;

import android.os.Bundle;

import com.arpa.and.wms.arch.base.BaseActivity;
import com.arpa.wms.hly.R;
import com.arpa.wms.hly.databinding.ActivityGoodsRecheckBinding;

import androidx.annotation.Nullable;
import dagger.hilt.android.AndroidEntryPoint;

/**
 * author: 李一方(<a href="mailto:leergo@dingtalk.com">leergo@dingtalk.com</a>)<br/>
 * version: 1.0.0<br/>
 * since: 2021-04-25 2:01 PM
 *
 * <p>
 * 页面：商品待复核列表
 * </p>
 */
@AndroidEntryPoint
public class GoodsRecheckActivity extends BaseActivity<VMGoodsRecheck, ActivityGoodsRecheckBinding> {
    @Override
    public int getLayoutId() {
        return R.layout.activity_goods_recheck;
    }

    @Override
    public void initData(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {

    }
}
