package com.arpa.wms.hly.logic.home.goods.pick;

import android.os.Bundle;

import com.arpa.wms.hly.R;
import com.arpa.wms.hly.base.WrapBaseActivity;
import com.arpa.wms.hly.databinding.ActivityGoodsPickTaskBinding;
import com.arpa.wms.hly.ui.form.TFormScrollHelper;

import androidx.annotation.Nullable;
import dagger.hilt.android.AndroidEntryPoint;

/**
 * author: 李一方(<a href="mailto:leergo@dingtalk.com">leergo@dingtalk.com</a>)<br/>
 * version: 1.0.0<br/>
 * since: 2021-07-12 17:45
 *
 * <p>
 * 页面：拣货
 * </p>
 */
@AndroidEntryPoint
public class GoodsPickTaskActivity extends WrapBaseActivity<VMGoodsPickTask, ActivityGoodsPickTaskBinding> {
    @Override
    public int getLayoutId() {
        return R.layout.activity_goods_pick_task;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        viewBind.setViewModel(viewModel);

        TFormScrollHelper scrollHelper = new TFormScrollHelper();
        scrollHelper.connectScrollView(viewBind.osvTitle);
        scrollHelper.connectScrollView(viewBind.osvContent);
        scrollHelper.connectRecyclerView(viewBind.rvContent, TFormScrollHelper.LEFT_AREA);
        scrollHelper.connectRecyclerView(viewBind.rvOperate, TFormScrollHelper.RIGHT_AREA);
    }
}
