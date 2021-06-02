package com.arpa.wms.hly.logic.home.goods.take;

import android.os.Bundle;

import com.arpa.wms.hly.BR;
import com.arpa.wms.hly.R;
import com.arpa.wms.hly.base.WrapBaseActivity;
import com.arpa.wms.hly.bean.GoodsItemVO;
import com.arpa.wms.hly.databinding.ActivityGoodsTakeConfirmBinding;
import com.arpa.wms.hly.logic.home.goods.take.vm.VMGoodsTakeConfirm;
import com.arpa.wms.hly.ui.decoration.BothItemDecoration;
import com.arpa.wms.hly.ui.dialog.DialogDateSelect;
import com.arpa.wms.hly.ui.dialog.DialogGoodStatusSelect;
import com.arpa.wms.hly.ui.dialog.DialogTips;
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
 * 页面：商品收货确认
 * </p>
 */
@AndroidEntryPoint
public class GoodsTakeConfirmActivity extends WrapBaseActivity<VMGoodsTakeConfirm, ActivityGoodsTakeConfirmBinding> {
    @Override
    public int getLayoutId() {
        return R.layout.activity_goods_take_confirm;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        super.initData(savedInstanceState);

        viewBind.setViewModel(viewModel);
        viewBind.rvList.addItemDecoration(new BothItemDecoration(true));
        viewBind.acbWholeConfirm.setOnClickListener(v ->
                showDialogFragment(new DialogTips(
                        "整单确认",
                        "当前订单存在未收货商品，整单确认后未收货商品不能继续收货。",
                        () -> viewModel.orderConfirm(false)))
        );
        viewModel.request.setParams(
                getIntent().getStringExtra(Const.IntentKey.RECEIVE_CODE),
                getIntent().getStringExtra(Const.IntentKey.RECEIVE_ITEM_CODE));
        viewModel.itemBinding
                .bindExtra(BR.onStatusClick,
                        (ViewListener.OnItemClickListener<GoodsItemVO>) (view, position, raw) ->
                                showDialogFragment(new DialogGoodStatusSelect(
                                        raw.getGoodsStatus(),
                                        viewModel.detail.getInventoryStatusList(),
                                        data -> {
                                            raw.setGoodsStatus(data.getCode());
                                            raw.setGoodsStatusName(data.getName());
                                            viewModel.update(position, raw);
                                        }))
                ).bindExtra(BR.onDateClick,
                (ViewListener.OnItemClickListener<GoodsItemVO>) (view, position, data) ->
                        showDialogFragment(new DialogDateSelect(date -> {
                            data.setGmtManufacture(date);
                            viewModel.update(position, data);
                        }))
        );
    }
}
