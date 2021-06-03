package com.arpa.wms.hly.logic.home.goods.recheck;

import android.os.Bundle;

import com.arpa.wms.hly.R;
import com.arpa.wms.hly.base.WrapBaseLazyFragment;
import com.arpa.wms.hly.databinding.FragmentGoodsRecheckDetailBinding;
import com.arpa.wms.hly.logic.home.goods.recheck.vm.VMGoodsRecheckDetail;
import com.arpa.wms.hly.logic.home.goods.recheck.vm.VMGoodsRecheckDetailList;
import com.arpa.wms.hly.ui.decoration.BothItemDecoration;
import com.arpa.wms.hly.utils.Const;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
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
public class GoodsRecheckDetailFragment extends WrapBaseLazyFragment<VMGoodsRecheckDetailList, FragmentGoodsRecheckDetailBinding> {

    public static GoodsRecheckDetailFragment newInstance(int outboundStatus, String outboundCode) {
        GoodsRecheckDetailFragment fragment = new GoodsRecheckDetailFragment();
        Bundle args = new Bundle();
        args.putInt(Const.IntentKey.STATUS, outboundStatus);
        args.putString(Const.IntentKey.CODE, outboundCode);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_goods_recheck_detail;
    }

    @Override
    public void onLazyLoad() {
        viewModel.autoRefresh();
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        super.initData(savedInstanceState);

        viewBind.setViewModel(viewModel);
        int recheckStatus = requireArguments().getInt(Const.IntentKey.STATUS);
        viewModel.request.setParams(recheckStatus, requireArguments().getString(Const.IntentKey.CODE));
        viewBind.rvList.addItemDecoration(new BothItemDecoration());
        VMGoodsRecheckDetail parentModel = new ViewModelProvider(requireActivity()).get(VMGoodsRecheckDetail.class);
        parentModel.searchLiveData.observe(requireActivity(),
                searchInfo -> {
                    if (recheckStatus == searchInfo.getStatus()) {
                        viewModel.request.setGoodsBarCode(searchInfo.getKeyWord());
                        viewModel.autoRefresh();
                    }
                }
        );
    }
}
