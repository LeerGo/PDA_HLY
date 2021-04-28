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

/**
 * author: 李一方(<a href="mailto:a94118@gmail.com">a94118@gmail.com</a>)<br/>
 * version: 1.0.0<br/>
 * since: 2021-04-28 04:12<br/>
 *
 * <p>
 * 页面：我的
 * </p>
 */
@AndroidEntryPoint
public class MineActivity extends BaseActivity<VMMine, ActivityMineBinding> implements DialogDismissListener<ResWarehouse> {

    @Override
    public int getLayoutId() {
        return R.layout.activity_mine;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        viewBind.setVariable(BR.vmMine, viewModel);
        // TODO:这里的代码可以抽取复用 @lyf 2021-04-28 02:47:46
        registerMessageEvent(ToastUtils::showShort);
        registerStatusEvent(status -> {
            if (status == StatusEvent.Status.LOADING) {
                showLoading();
            } else {
                hideLoading();
            }
        });
        // --------------------------------- 截止
        viewModel.getWarehouseLiveData().observe(this, list -> {
            if (list.size() <= 1) {
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