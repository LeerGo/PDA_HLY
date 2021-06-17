package com.arpa.wms.hly.logic.task;

import android.os.Bundle;

import com.arpa.wms.hly.R;
import com.arpa.wms.hly.base.WrapBaseActivity;
import com.arpa.wms.hly.databinding.ActivityTaskGoodsPickBinding;
import com.arpa.wms.hly.logic.task.vm.VMTaskGoodsPick;

import androidx.annotation.Nullable;
import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class TaskGoodsPickActivity extends WrapBaseActivity<VMTaskGoodsPick, ActivityTaskGoodsPickBinding> {
    @Override
    public int getLayoutId() {
        return R.layout.activity_task_goods_pick;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        viewBind.setViewModel(viewModel);
    }
}