package com.arpa.wms.hly.logic.home.goods.take;

import android.os.Bundle;

import com.arpa.and.wms.arch.base.BaseActivity;
import com.arpa.wms.hly.R;
import com.arpa.wms.hly.databinding.ActivityGoodsTakeConfirmBinding;
import com.arpa.wms.hly.logic.home.goods.take.vm.VMGoodsTakeConfirm;

import androidx.annotation.Nullable;

/**
 * author: 李一方(<a href="mailto:leergo@dingtalk.com">leergo@dingtalk.com</a>)<br/>
 * version: 1.0.0<br/>
 * since: 2021-04-25 2:01 PM
 *
 * <p>
 * 页面：商品收货确认
 * </p>
 */
public class GoodsTakeConfirmActivity extends BaseActivity<VMGoodsTakeConfirm, ActivityGoodsTakeConfirmBinding> {
    @Override
    public int getLayoutId() {
        return R.layout.activity_goods_take_confirm;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        viewBind.setViewModel(viewModel);
    }
}
