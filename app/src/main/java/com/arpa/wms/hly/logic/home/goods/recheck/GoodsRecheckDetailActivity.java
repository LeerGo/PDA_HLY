package com.arpa.wms.hly.logic.home.goods.recheck;

import android.os.Bundle;

import com.arpa.and.wms.arch.base.BaseActivity;
import com.arpa.wms.hly.BR;
import com.arpa.wms.hly.R;
import com.arpa.wms.hly.databinding.ActivityPdataskRecheckDetailBinding;
import com.arpa.wms.hly.logic.home.goods.recheck.vm.VMGoodsRecheckDetail;
import com.arpa.wms.hly.utils.Const;

import androidx.annotation.Nullable;
import dagger.hilt.android.AndroidEntryPoint;

/**
 * author: 李一方(<a href="mailto:leergo@dingtalk.com">leergo@dingtalk.com</a>)<br/>
 * version: 1.0.0<br/>
 * since: 2021-04-25 2:01 PM
 *
 * <p>
 * 页面：商品待复核详情
 * </p>
 */
@AndroidEntryPoint
public class GoodsRecheckDetailActivity extends BaseActivity<VMGoodsRecheckDetail, ActivityPdataskRecheckDetailBinding> {
    @Override
    public int getLayoutId() {
        return R.layout.activity_pdatask_recheck_detail;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        viewBind.setVariable(BR.viewModel, viewModel);
        viewModel.data.set(getIntent().getParcelableExtra(Const.IntentKey.DATA));
        //        viewBind.wsbSearch.setOnSearchClick(data -> viewModel.search(data));
        //        viewBind.rvList.addItemDecoration(ItemDecorationUtil.getDividerBottom10DP());
        //        viewModel.getItemBinding().bindExtra(BR.listener, (ViewListener.DataTransCallback<ResPdaTask>) data -> {
        //
        //        });
    }
}
