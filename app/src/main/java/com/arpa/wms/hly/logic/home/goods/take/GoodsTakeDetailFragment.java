package com.arpa.wms.hly.logic.home.goods.take;

import android.os.Bundle;

import com.arpa.wms.hly.R;
import com.arpa.wms.hly.base.WrapBaseLazyFragment;
import com.arpa.wms.hly.databinding.FragmentGoodsTakeDetailBinding;
import com.arpa.wms.hly.logic.home.goods.take.vm.VMGoodsTakeDetailList;
import com.arpa.wms.hly.ui.decoration.ItemDecorationUtil;
import com.arpa.wms.hly.utils.Const.IntentKey;

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
public class GoodsTakeDetailFragment extends WrapBaseLazyFragment<VMGoodsTakeDetailList, FragmentGoodsTakeDetailBinding> {

    public static GoodsTakeDetailFragment newInstance(int receiveStatus, String receiveCode) {
        GoodsTakeDetailFragment fragment = new GoodsTakeDetailFragment();
        Bundle args = new Bundle();
        args.putInt(IntentKey.STATUS, receiveStatus);
        args.putString(IntentKey.CODE, receiveCode);
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
        super.initData(savedInstanceState);

        viewBind.setViewModel(viewModel);
        viewModel.request.setParams(requireArguments().getInt(IntentKey.STATUS), requireArguments().getString(IntentKey.CODE));
        viewBind.rvList.addItemDecoration(ItemDecorationUtil.getDividerTop10DP());
    }
}
