package com.arpa.wms.hly.logic.home.goods.recheck;

import android.os.Bundle;
import android.os.Message;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;

import com.arpa.wms.hly.R;
import com.arpa.wms.hly.base.WrapBaseLazyFragment;
import com.arpa.wms.hly.bean.SNCodeTip;
import com.arpa.wms.hly.databinding.FragmentGoodsRecheckDetailBinding;
import com.arpa.wms.hly.logic.home.goods.recheck.vm.VMGoodsRecheckDetail;
import com.arpa.wms.hly.logic.home.goods.recheck.vm.VMGoodsRecheckDetailList;
import com.arpa.wms.hly.logic.home.goods.recheck.vm.VMSerialDetail;
import com.arpa.wms.hly.ui.dialog.DialogTips;
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
public class GoodsRecheckDetailFragment extends WrapBaseLazyFragment<VMGoodsRecheckDetailList, FragmentGoodsRecheckDetailBinding> {
    private VMSerialDetail vmSerial;

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
    public void onDestroy() {
        if (null!=vmSerial) {
            vmSerial.release();
        }
        super.onDestroy();
    }

    @Override
    public void onLazyLoad() {
        viewModel.requestData();
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        super.initData(savedInstanceState);

        viewBind.setViewModel(viewModel);
        viewModel.initParams(requireArguments());
        int recheckStatus = requireArguments().getInt(Const.IntentKey.STATUS);
        String code = requireArguments().getString(Const.IntentKey.CODE);
        observeSerial(recheckStatus, code);
        observeParent(recheckStatus);
        viewModel.getSingleLiveEvent().observeForever(this::processEvent);
    }

    private void processEvent(Message message) {
        switch (message.what) {

            case Const.Message.MSG_BATCH_REPEAT:
                showDialogFragment(new DialogTips("校验提示", "该批次号已当前或其他单据录入", () -> {}));
                break;

            case Const.Message.MSG_BATCH_VERIFY:
                SNCodeTip tip = (SNCodeTip) message.obj;
                showDialogFragment(new DialogTips("校验提示", tip.getTip(), "删除", "录入",
                        () -> vmSerial.addSNCode(tip.getRule(), tip.getCode()), () -> {}));
                break;
            default:
                break;
        }
    }

    private void observeSerial(int recheckStatus, String code) {
        if (Const.TASK_STATUS.RECHECK_WAIT == recheckStatus) {
            vmSerial = obtainViewModel(VMSerialDetail.class);
            vmSerial.register(this, viewModel);
            vmSerial.setTaskCode(code);
            vmSerial.setItems(viewModel.items);
            viewBind.setVmSerial(vmSerial);
            viewBind.wsbSearch.setOnSearchClick(vmSerial::onScan);
        }
    }

    private void observeParent(int recheckStatus) {
        VMGoodsRecheckDetail parentModel = new ViewModelProvider(requireActivity()).get(VMGoodsRecheckDetail.class);
        viewModel.refresh.observe(this, it -> parentModel.refreshHeader());
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
