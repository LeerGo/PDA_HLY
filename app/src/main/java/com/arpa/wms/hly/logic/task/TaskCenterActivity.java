package com.arpa.wms.hly.logic.task;

import android.os.Bundle;

import com.arpa.wms.hly.R;
import com.arpa.wms.hly.base.WrapBaseActivity;
import com.arpa.wms.hly.databinding.ActivityTaskCenterBinding;
import com.arpa.wms.hly.logic.task.vm.VMTaskCenter;

import androidx.annotation.Nullable;
import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class TaskCenterActivity extends WrapBaseActivity<VMTaskCenter, ActivityTaskCenterBinding> {
    @Override
    public int getLayoutId() {
        return R.layout.activity_task_center;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        viewBind.setViewModel(viewModel);
    }
}