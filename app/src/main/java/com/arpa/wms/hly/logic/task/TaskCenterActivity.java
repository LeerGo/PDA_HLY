package com.arpa.wms.hly.logic.task;

import android.os.Bundle;

import com.arpa.and.wms.arch.base.BaseActivity;
import com.arpa.wms.hly.BR;
import com.arpa.wms.hly.R;
import com.arpa.wms.hly.databinding.ActivityTaskCenterBinding;

import androidx.annotation.Nullable;
import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class TaskCenterActivity extends BaseActivity<VMTaskCenter, ActivityTaskCenterBinding> {
    @Override
    public int getLayoutId() {
        return R.layout.activity_task_center;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        viewBind.setVariable(BR.viewModel, viewModel);
    }
}