package com.arpa.wms.hly.logic.home.goods.take;

import android.os.Bundle;

import com.arpa.and.wms.arch.base.BaseActivity;
import com.arpa.wms.hly.R;
import com.arpa.wms.hly.bean.res.ResTaskAssign;
import com.arpa.wms.hly.databinding.ActivityPdataskTakeDetailBinding;
import com.arpa.wms.hly.logic.home.goods.take.vm.VMGoodsTakeDetail;
import com.arpa.wms.hly.utils.Const;

import androidx.annotation.Nullable;
import dagger.hilt.android.AndroidEntryPoint;

/**
 * author: 李一方(<a href="mailto:leergo@dingtalk.com">leergo@dingtalk.com</a>)<br/>
 * version: 1.0.0<br/>
 * since: 2021-04-25 2:01 PM
 *
 * <p>
 * 页面：商品收货详情
 * </p>
 */
@AndroidEntryPoint
public class GoodsTakeDetailActivity extends BaseActivity<VMGoodsTakeDetail, ActivityPdataskTakeDetailBinding> {
    @Override
    public int getLayoutId() {
        return R.layout.activity_pdatask_take_detail;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        viewBind.setViewModel(viewModel);
        ResTaskAssign data = getIntent().getParcelableExtra(Const.IntentKey.DATA);
        String receiveCode = data.getCode();
        viewModel.headerData.set(data);
        viewModel.fragments.add(GoodsTakeDetailFragment.newInstance(Const.TASK_STATUS.TAKE_WAIT, receiveCode));
        viewModel.fragments.add(GoodsTakeDetailFragment.newInstance(Const.TASK_STATUS.TAKE_YET, receiveCode));
    }
}
