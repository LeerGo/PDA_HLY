package com.arpa.wms.hly.logic.home.goods.recheck;

import android.content.Intent;
import android.os.Bundle;

import com.arpa.wms.hly.R;
import com.arpa.wms.hly.base.WrapBaseActivity;
import com.arpa.wms.hly.databinding.ActivityGoodsRecheckConfirmBinding;
import com.arpa.wms.hly.logic.home.goods.recheck.vm.VMGoodsRecheckConfirm;
import com.arpa.wms.hly.utils.Const.IntentKey;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
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

    /**
     * 批次登记的 activity result
     */
    private final ActivityResultLauncher<Intent> batchResult =
            registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                    result -> {
                        if (result.getResultCode() == RESULT_OK) {
                            viewModel.batchCodeList = result.getData().getStringArrayListExtra(IntentKey.DATA);
                            StringBuilder sb = new StringBuilder();
                            for (int i = 0; i < viewModel.batchCodeList.size(); i++) {
                                sb.append(viewModel.batchCodeList.get(i));
                                if (i < viewModel.batchCodeList.size() - 1) sb.append(",");
                            }
                            viewModel.obvBatchCode.set(sb.toString());
                        }
                    });

    @Override
    public int getLayoutId() {
        return R.layout.activity_goods_recheck_confirm;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        viewBind.setViewModel(viewModel);
        viewModel.request.setParams(
                getIntent().getStringExtra(IntentKey.OUTBOUND_CODE),
                getIntent().getStringExtra(IntentKey.OUTBOUND_ITEM_CODE)
        );
        viewBind.acbBatchRegist.setOnClickListener(v -> {
            Bundle bundle = new Bundle();
            bundle.putString(IntentKey.CODE, viewModel.request.getOutboundItemCode());
            bundle.putString(IntentKey.GOODS_NAME, viewModel.detail.get().getGoodsName());
            bundle.putString(IntentKey.GOODS_UNIT_NAME, viewModel.detail.get().getGoodsUnitName());
            bundle.putInt(IntentKey.GOODS_COUNT, viewModel.detail.get().getWaitRecheckQuantity());
            bundle.putStringArrayList(IntentKey.DATA, viewModel.batchCodeList);
            batchResult.launch(newIntent(GoodsRecheckBatchActivity.class, bundle));
        });
    }
}
