package com.arpa.wms.hly.logic.task;

import android.os.Bundle;

import com.arpa.wms.hly.R;
import com.arpa.wms.hly.base.WrapBaseActivity;
import com.arpa.wms.hly.bean.res.ResTaskAssign;
import com.arpa.wms.hly.databinding.ActivityTaskGoodsPickBinding;
import com.arpa.wms.hly.logic.task.vm.VMTaskGoodsPick;
import com.arpa.wms.hly.ui.decoration.BothItemDecoration;

import java.util.Objects;

import androidx.annotation.Nullable;
import dagger.hilt.android.AndroidEntryPoint;

import static com.arpa.wms.hly.utils.Const.IntentKey.DATA;

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
        ResTaskAssign data = getIntent().getParcelableExtra(DATA);
        viewModel.headerData.setValue(data);
        viewModel.headerData.observe(this, resTaskAssign -> viewBind.incHeader.setData(resTaskAssign));
        viewModel.reqPickDetail.setSourceCode(Objects.requireNonNull(data).getSourceCode());
        viewBind.rvList.addItemDecoration(new BothItemDecoration(true));
    }
}