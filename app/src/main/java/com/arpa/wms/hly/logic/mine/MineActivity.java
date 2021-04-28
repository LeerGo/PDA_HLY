package com.arpa.wms.hly.logic.mine;

import android.os.Bundle;

import com.arpa.and.wms.arch.base.BaseActivity;
import com.arpa.and.wms.arch.base.livedata.StatusEvent;
import com.arpa.wms.hly.BR;
import com.arpa.wms.hly.R;
import com.arpa.wms.hly.bean.ResWarehouse;
import com.arpa.wms.hly.databinding.ActivityMineBinding;
import com.arpa.wms.hly.ui.dialog.DialogWarehouseSelect;
import com.arpa.wms.hly.ui.listener.DialogDismissListener;
import com.arpa.wms.hly.utils.Const.SPKEY;
import com.arpa.wms.hly.utils.ToastUtils;

import androidx.annotation.Nullable;
import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MineActivity extends BaseActivity<VMMine, ActivityMineBinding> implements DialogDismissListener<ResWarehouse> {

    @Override
    public int getLayoutId() {
        return R.layout.activity_mine;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        viewBind.setVariable(BR.vmMine, viewModel);
        registerMessageEvent(ToastUtils::showShort);
        registerStatusEvent(status -> {
            if (status == StatusEvent.Status.LOADING) {
                showLoading();
            } else {
                hideLoading();
            }
        });
        viewModel.getWarehouseLiveData().observe(this, list -> {
            if (list.size() == 1) {
                ToastUtils.showShort("只有一个仓库无法切换");
            } else {
                showDialogFragment(new DialogWarehouseSelect(list, this));
            }
        });
    }

    @Override
    public void onDialogSure(ResWarehouse data) {
        viewModel.spPut(SPKEY.WAREHOUSE_CODE, data.getCode());
        viewModel.spPut(SPKEY.WAREHOUSE_NAME, data.getName());
        viewModel.getWarehouse().set(data.getName());
    }
}