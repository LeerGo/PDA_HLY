package com.arpa.wms.hly.logic.mine;

import android.os.Bundle;

import com.arpa.wms.hly.BR;
import com.arpa.wms.hly.R;
import com.arpa.wms.hly.base.WrapBaseActivity;
import com.arpa.wms.hly.bean.res.ResWarehouse;
import com.arpa.wms.hly.databinding.ActivityMineBinding;
import com.arpa.wms.hly.ui.dialog.DialogModifyPass;
import com.arpa.wms.hly.ui.dialog.DialogWarehouseSelect;
import com.arpa.wms.hly.ui.listener.ViewListener.DataTransCallback;
import com.arpa.wms.hly.utils.Const;
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
public class MineActivity extends WrapBaseActivity<VMMine, ActivityMineBinding>
        implements DataTransCallback<ResWarehouse> {

    @Override
    public int getLayoutId() {
        return R.layout.activity_mine;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        super.initData(savedInstanceState);

        viewBind.setVariable(BR.vmMine, viewModel);
        viewModel.getWarehouseLiveData().observe(this, list -> {
            if (list.size() <= 1) {
                ToastUtils.showShort("只有一个仓库无法切换");
            } else {
                showDialogFragment(new DialogWarehouseSelect(list, this));
            }
            //            showDialogFragment(new DialogAssignSelect("分配保管员", list, this));
        });
        viewBind.acbModifyPass.setOnClickListener(v ->
                showDialogFragment(new DialogModifyPass(data -> viewModel.modifyPassword(data)))
        );
    }

    @Override
    public void transfer(ResWarehouse data) {
        viewModel.spPut(Const.SPKEY.WAREHOUSE_CODE, data.getCode());
        viewModel.spPut(Const.SPKEY.WAREHOUSE_NAME, data.getName());
        viewModel.getWarehouse().set(data.getName());
    }
}