package com.arpa.wms.hly.logic.task;

import android.os.Bundle;

import com.arpa.wms.hly.R;
import com.arpa.wms.hly.base.WrapBaseActivity;
import com.arpa.wms.hly.databinding.ActivityTaskGoodsTakeBinding;
import com.arpa.wms.hly.logic.task.vm.VMTaskGoodsTake;

import androidx.annotation.Nullable;
import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class TaskGoodsTakeActivity extends WrapBaseActivity<VMTaskGoodsTake, ActivityTaskGoodsTakeBinding> {
    @Override
    public int getLayoutId() {
        return R.layout.activity_task_goods_take;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        viewBind.setViewModel(viewModel);
    }
}