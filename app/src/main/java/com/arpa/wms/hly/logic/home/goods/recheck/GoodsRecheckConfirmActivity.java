package com.arpa.wms.hly.logic.home.goods.recheck;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.arpa.wms.hly.R;
import com.arpa.wms.hly.base.WrapBaseActivity;
import com.arpa.wms.hly.databinding.ActivityGoodsRecheckConfirmBinding;
import com.arpa.wms.hly.logic.home.goods.recheck.vm.VMGoodsRecheckConfirm;
import com.arpa.wms.hly.ui.dialog.DialogTips;
import com.arpa.wms.hly.utils.Const;
import com.arpa.wms.hly.utils.SpanUtil;

import dagger.hilt.android.AndroidEntryPoint;

/**
 * author: 李一方(<a href="mailto:leergo@dingtalk.com">leergo@dingtalk.com</a>)<br/>
 * version: 1.0.0<br/>
 * since: 2021-04-25 2:01 PM
 *
 * <p>
 * 页面：商品复核确认
 * </p>
 */
@AndroidEntryPoint
public class GoodsRecheckConfirmActivity extends WrapBaseActivity<VMGoodsRecheckConfirm, ActivityGoodsRecheckConfirmBinding> {

    @Override
    protected void onResume() {
        super.onResume();

        viewModel.loadHistory();
        viewBind.wpiBatch.setValueSpan(SpanUtil.highlight(viewModel.obvBatchCode, viewModel.latestBatchNo.get(), viewModel.oldestBatchNo.get()));
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_goods_recheck_confirm;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        viewBind.setViewModel(viewModel);
        viewModel.initParams(getIntent());
        registerSingleLiveEvent(msg -> {
            if (Const.Message.MSG_DIALOG == msg.what) {
                showDialogFragment(new DialogTips("提示", "数据是否录入完毕，确认提交？", () -> viewModel.submit(), () -> {}));
            }
        });
    }
}
