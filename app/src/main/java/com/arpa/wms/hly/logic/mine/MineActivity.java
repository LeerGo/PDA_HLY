package com.arpa.wms.hly.logic.mine;

import android.os.Bundle;

import com.arpa.wms.hly.BuildConfig;
import com.arpa.wms.hly.R;
import com.arpa.wms.hly.base.WrapBaseActivity;
import com.arpa.wms.hly.bean.res.ResWarehouse;
import com.arpa.wms.hly.databinding.ActivityMineBinding;
import com.arpa.wms.hly.ui.dialog.DialogApiChange;
import com.arpa.wms.hly.ui.dialog.DialogModifyPass;
import com.arpa.wms.hly.ui.dialog.DialogWarehouseSelect;
import com.arpa.wms.hly.ui.listener.ViewListener.DataTransCallback;
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
public class MineActivity extends WrapBaseActivity<VMMine, ActivityMineBinding> implements DataTransCallback<ResWarehouse> {

    @Override
    public int getLayoutId() {
        return R.layout.activity_mine;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        viewBind.setVmMine(viewModel);
        viewModel.getWarehouseLiveData().observe(this, list -> {
            if (list.size() <= 1) {
                ToastUtils.showShort("只有一个仓库无法切换");
            } else {
                showDialogFragment(new DialogWarehouseSelect(list, this));
            }
        });
        viewBind.acbModifyPass.setOnClickListener(v ->
                showDialogFragment(new DialogModifyPass(data -> viewModel.modifyPassword(data)))
        );
        viewBind.ivAvatar.setOnClickListener(v -> {
            if (BuildConfig.DEBUG)
                showDialogFragment(new DialogApiChange(view -> viewModel.logout()));
        });
    }

    @Override
    public void transfer(ResWarehouse data) {
        viewModel.getWarehouse().set(data.getName());
        viewModel.bindWarehouse(data, false);
    }
}