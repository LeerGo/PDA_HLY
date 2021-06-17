package com.arpa.wms.hly.logic.task;

import android.os.Bundle;

import com.arpa.wms.hly.R;
import com.arpa.wms.hly.base.WrapBaseActivity;
import com.arpa.wms.hly.bean.res.ResTaskAssign;
import com.arpa.wms.hly.databinding.ActivityTaskGoodsTakeBinding;
import com.arpa.wms.hly.logic.task.vm.VMTaskGoodsTake;
import com.arpa.wms.hly.ui.decoration.BothItemDecoration;

import androidx.annotation.Nullable;
import dagger.hilt.android.AndroidEntryPoint;

import static com.arpa.wms.hly.utils.Const.IntentKey.DATA;

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

        ResTaskAssign data = getIntent().getParcelableExtra(DATA);
        viewModel.headerData.set(data);

        viewBind.rvList.addItemDecoration(new BothItemDecoration(true));
        viewBind.wsbSearch.setOnSearchClick(keyWord -> {
            viewModel.request.setGoodsBarCode(keyWord);
            viewModel.refresh();
        });
    }
}