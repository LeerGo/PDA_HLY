package com.arpa.wms.hly.logic.home.goods.recheck;

import android.os.Bundle;

import com.arpa.and.wms.arch.base.BaseLazyFragment;
import com.arpa.wms.hly.BR;
import com.arpa.wms.hly.R;
import com.arpa.wms.hly.bean.res.ResPdaTask;
import com.arpa.wms.hly.databinding.ActivityPdataskRecheckBinding;
import com.arpa.wms.hly.logic.home.goods.recheck.vm.VMGoodsRecheck;
import com.arpa.wms.hly.ui.decoration.ItemDecorationUtil;
import com.arpa.wms.hly.ui.listener.ViewListener;
import com.arpa.wms.hly.utils.Const;

import androidx.annotation.Nullable;
import dagger.hilt.android.AndroidEntryPoint;

/**
 * author: 李一方(<a href="mailto:leergo@dingtalk.com">leergo@dingtalk.com</a>)<br/>
 * version: 1.0.0<br/>
 * since: 2021-04-25 2:01 PM
 *
 * <p>
 * 页面：商品待复核、已复核列表
 * </p>
 */
@AndroidEntryPoint
public class GoodsRecheckDetailFragment extends BaseLazyFragment<VMGoodsRecheck, ActivityPdataskRecheckBinding> {

    public static GoodsRecheckDetailFragment newInstance(String text) {
        GoodsRecheckDetailFragment fragment = new GoodsRecheckDetailFragment();
        Bundle args = new Bundle();
        args.putString(Const.IntentKey.INDEX, text);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_pdatask_recheck;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        viewBind.setViewModel(viewModel);
        viewBind.wsbSearch.setOnSearchClick(data -> viewModel.search(data));
        viewBind.rvList.addItemDecoration(ItemDecorationUtil.getDividerBottom10DP());
        viewModel.getItemBinding().bindExtra(BR.listener, (ViewListener.DataTransCallback<ResPdaTask>) data -> {

        });
    }

    @Override
    public void onLazyLoad() {

    }
}
