package com.arpa.wms.hly.logic.home.goods.take;

import android.os.Bundle;

import com.arpa.and.wms.arch.base.BaseLazyFragment;
import com.arpa.wms.hly.BR;
import com.arpa.wms.hly.R;
import com.arpa.wms.hly.bean.res.ResPdaTask;
import com.arpa.wms.hly.databinding.FragmentGoodsTakeDetailBinding;
import com.arpa.wms.hly.logic.home.goods.take.vm.VMGoodsTake;
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
 * 页面：商品收货详情
 * </p>
 */
@AndroidEntryPoint
public class GoodsTakeDetailFragment extends BaseLazyFragment<VMGoodsTake, FragmentGoodsTakeDetailBinding> {

    public static GoodsTakeDetailFragment newInstance(String text) {
        GoodsTakeDetailFragment fragment = new GoodsTakeDetailFragment();
        Bundle args = new Bundle();
        args.putString(Const.IntentKey.INDEX, text);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onLazyLoad() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_goods_take_detail;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        viewBind.setViewModel(viewModel);
        viewBind.rvList.addItemDecoration(ItemDecorationUtil.getDividerTop10D10DP());
        viewModel.getItemBinding().bindExtra(BR.listener, (ViewListener.DataClickListener<ResPdaTask>) data -> {

        });
    }
}
