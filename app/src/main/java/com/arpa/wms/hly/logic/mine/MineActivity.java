package com.arpa.wms.hly.logic.mine;

import android.os.Bundle;

import com.arpa.and.wms.arch.base.BaseActivity;
import com.arpa.wms.hly.BR;
import com.arpa.wms.hly.R;
import com.arpa.wms.hly.databinding.ActivityMineBinding;

import androidx.annotation.Nullable;
import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MineActivity extends BaseActivity<VMMine, ActivityMineBinding> {

    @Override
    public int getLayoutId() {
        return R.layout.activity_mine;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        viewBind.setVariable(BR.vmMine, viewModel);
    }
}