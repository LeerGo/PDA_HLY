package com.arpa.wms.hly.logic.home.goods.take;

import android.os.Bundle;

import com.arpa.and.wms.arch.base.BaseActivity;
import com.arpa.wms.hly.BR;
import com.arpa.wms.hly.R;
import com.arpa.wms.hly.bean.GoodsTakeBatchItem;
import com.arpa.wms.hly.databinding.ActivityGoodsTakeConfirmBinding;
import com.arpa.wms.hly.logic.home.goods.take.vm.VMGoodsTakeConfirm;
import com.arpa.wms.hly.ui.decoration.ItemDecorationUtil;
import com.arpa.wms.hly.ui.dialog.DialogDateSelect;
import com.arpa.wms.hly.ui.listener.ViewListener;

import androidx.annotation.Nullable;
import dagger.hilt.android.AndroidEntryPoint;

/**
 * author: 李一方(<a href="mailto:leergo@dingtalk.com">leergo@dingtalk.com</a>)<br/>
 * version: 1.0.0<br/>
 * since: 2021-04-25 2:01 PM
 *
 * <p>
 * 页面：商品收货确认
 * </p>
 */
@AndroidEntryPoint
public class GoodsTakeConfirmActivity extends BaseActivity<VMGoodsTakeConfirm, ActivityGoodsTakeConfirmBinding> {
    @Override
    public int getLayoutId() {
        return R.layout.activity_goods_take_confirm;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        viewBind.setViewModel(viewModel);
        viewBind.rvList.addItemDecoration(ItemDecorationUtil.getDividerBottom10DP());
        viewModel.itemBinding
                .bindExtra(BR.onStatusClick, (ViewListener.DataTransCallback<GoodsTakeBatchItem>) data ->
                        showDialogFragment(new DialogDateSelect(data::setReceivedState))
                )
                .bindExtra(BR.onDateClick,
                        (ViewListener.OnItemClickListener<GoodsTakeBatchItem>) (view, position, data) -> showDialogFragment(new DialogDateSelect(date -> {
                            data.setProductDate(date);
                            viewModel.update(position, data);
                        })));
    }
}
