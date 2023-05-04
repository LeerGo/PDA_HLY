package com.arpa.wms.hly.logic.home.goods.recheck;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;

import com.arpa.wms.hly.R;
import com.arpa.wms.hly.base.WrapBaseLazyFragment;
import com.arpa.wms.hly.databinding.FragmentGoodsRecheckDetailBinding;
import com.arpa.wms.hly.logic.home.goods.recheck.vm.VMGoodsRecheckDetail;
import com.arpa.wms.hly.logic.home.goods.recheck.vm.VMGoodsRecheckDetailDiffList;
import com.arpa.wms.hly.logic.home.goods.recheck.vm.VMSerialDetail;
import com.arpa.wms.hly.ui.listener.ViewListener;
import com.arpa.wms.hly.utils.Const;

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
public class GoodsRecheckDetailFragment extends WrapBaseLazyFragment<VMGoodsRecheckDetailDiffList, FragmentGoodsRecheckDetailBinding> {

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
        viewModel.requestData();
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        super.initData(savedInstanceState);

        viewBind.setViewModel(viewModel);

        VMSerialDetail vmSerial = obtainViewModel(VMSerialDetail.class);
        vmSerial.register(this, viewModel);
        // viewBind.wsbSearch.setOnSearchClick(vmSerial::onScan);
        viewBind.wsbSearch.setOnSearchClick(new ViewListener.DataTransCallback<String>() {
            @Override
            public void transfer(String data) {
                vmSerial.sendMessage("1111");
            }
        });

        viewModel.initParams(requireArguments());
        observeParent();
    }

    private void observeParent() {
        int recheckStatus = requireArguments().getInt(Const.IntentKey.STATUS);
        VMGoodsRecheckDetail parentModel = new ViewModelProvider(requireActivity()).get(VMGoodsRecheckDetail.class);
        parentModel.headerData.observe(requireActivity(), headerData -> viewModel.supplierName = headerData.getSupplierName());
        parentModel.searchLiveData.observe(requireActivity(),
                searchInfo -> {
                    if (recheckStatus == searchInfo.getStatus()) {
                        viewModel.request.setGoodsBarCode(searchInfo.getKeyWord());
                        viewModel.requestData();
                    }
                }
        );
    }
}
