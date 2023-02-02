package com.arpa.wms.hly.logic.home.goods.take;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;

import com.arpa.wms.hly.BR;
import com.arpa.wms.hly.R;
import com.arpa.wms.hly.base.WrapBaseActivity;
import com.arpa.wms.hly.bean.GoodsItemVO;
import com.arpa.wms.hly.databinding.ActivityGoodsTakeConfirmBinding;
import com.arpa.wms.hly.logic.home.goods.take.vm.VMGoodsTakeConfirm;
import com.arpa.wms.hly.ui.decoration.BothItemDecoration;
import com.arpa.wms.hly.ui.dialog.DialogDateSelect;
import com.arpa.wms.hly.ui.dialog.DialogGoodStatusSelect;
import com.arpa.wms.hly.ui.dialog.DialogGoodsTaskScan;
import com.arpa.wms.hly.ui.dialog.DialogLocation;
import com.arpa.wms.hly.ui.dialog.DialogTips;
import com.arpa.wms.hly.ui.listener.ViewListener;
import com.arpa.wms.hly.utils.Const.DateType;
import com.arpa.wms.hly.utils.Const.IntentKey;

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
        viewBind.rvList.addItemDecoration(new BothItemDecoration(BothItemDecoration.BOTTOM, false));
        viewBind.acbWholeConfirm.setOnClickListener(v ->
                showDialogFragment(new DialogTips("整单确认", "当前订单存在未收货商品，整单确认后未收货商品不能继续收货。", () -> viewModel.orderConfirm(true)))
        );
        viewModel.request.setParams(getIntent().getStringExtra(IntentKey.CODE), getIntent().getStringExtra(IntentKey.RECEIVE_CODE));
        viewModel.supplier = getIntent().getStringExtra(IntentKey.SUPPLIER);
        viewModel.dialogMsg.observe(this, (Observer<String>) msg -> showDialogFragment(new DialogTips(msg)));
        viewModel.itemBinding
                .bindExtra(BR.onLocationClick, (ViewListener.OnItemClickListener<GoodsItemVO>) (view, position, raw) -> {
                    showDialogFragment(new DialogLocation(data -> {
                        raw.setLocation(data);
                        viewModel.update(position, raw);
                    }));
                })
                .bindExtra(BR.onStatusClick, (ViewListener.OnItemClickListener<GoodsItemVO>) (view, position, raw) ->
                        showDialogFragment(new DialogGoodStatusSelect(raw.getGoodsStatus(), viewModel.detail.get().getInventoryStatusList(),
                                data -> {
                                    raw.setStatus(data);
                                    viewModel.update(position, raw);
                                })))
                .bindExtra(BR.onDateClick, (ViewListener.OnDateClickListener<GoodsItemVO>) (view, position, dateType, raw) -> {
                            DialogDateSelect dialogDateSelect;
                            if (dateType == DateType.gmtExpire) {
                                dialogDateSelect = new DialogDateSelect(
                                        dateType, raw.getGmtManufacture(), raw.getExpirationQuantity(),
                                        date -> {
                                            raw.setGmtExpire(date);
                                            viewModel.update(position, raw);
                                        });
                            } else {
                                dialogDateSelect = new DialogDateSelect(dateType, date -> {
                                    if (dateType == DateType.gmtManufacture) raw.setGmtManufacture(date);
                                    if (dateType == DateType.gmtStock) raw.setGmtStock(date);
                                    if (dateType == DateType.extendFive) raw.setExtendFive(date);
                                    if (dateType == DateType.extendSix) raw.setExtendSix(date);
                                    viewModel.update(position, raw);
                                });
                            }
                            showDialogFragment(dialogDateSelect);
                        }
                );
        viewBind.wsbSearch.setOnSearchClick(data -> {
            viewModel.request.setGoodsBarCode(data);
            viewModel.requestData();
        });
        viewBind.wiiScan.setOnClickListener(v -> showDialogFragment(new DialogGoodsTaskScan(data -> {
            viewModel.detail.get().setScan(data.getIndex());
            viewModel.scanText.set(data.getContent());
        })));
    }
}
