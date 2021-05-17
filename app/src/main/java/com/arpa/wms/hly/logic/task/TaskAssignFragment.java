package com.arpa.wms.hly.logic.task;

import android.os.Bundle;

import com.arpa.and.wms.arch.base.BaseLazyFragment;
import com.arpa.wms.hly.BR;
import com.arpa.wms.hly.R;
import com.arpa.wms.hly.bean.res.ResPdaTask;
import com.arpa.wms.hly.databinding.FragmentTaskAssignBinding;
import com.arpa.wms.hly.logic.task.vm.VMTaskAssign;
import com.arpa.wms.hly.ui.decoration.ItemDecorationUtil;
import com.arpa.wms.hly.ui.listener.ViewListener;
import com.arpa.wms.hly.utils.Const.IntentKey;

import androidx.annotation.Nullable;
import dagger.hilt.android.AndroidEntryPoint;

/**
 * author: 李一方(<a href="mailto:leergo@dingtalk.com">leergo@dingtalk.com</a>)<br/>
 * version: 1.0.0<br/>
 * since: 2021-04-22 10:54 AM
 *
 * <p>
 * 内容描述区域
 * </p>
 */
@AndroidEntryPoint
public class TaskAssignFragment extends BaseLazyFragment<VMTaskAssign, FragmentTaskAssignBinding> {
    public static TaskAssignFragment newInstance(int index) {
        TaskAssignFragment fragment = new TaskAssignFragment();
        Bundle args = new Bundle();
        args.putInt(IntentKey.INDEX, index);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_task_assign;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        viewBind.setVariable(BR.viewModel, viewModel);

        viewBind.rvList.addItemDecoration(ItemDecorationUtil.getDividerTop10DP());
        viewModel.type.set(getArguments() != null ? getArguments().getInt(IntentKey.INDEX, 0) : 0);
        viewModel.getItemBinding()
                .bindExtra(BR.listener, (ViewListener.DataTransCallback<ResPdaTask>) data -> {
                    // TODO: 跳转详情 @lyf 2021-05-06 10:09:04
                })
                .bindExtra(BR.select, (ViewListener.DataTransCallback<ResPdaTask>) data -> {
                    // 多选
                    data.setIsSelect(!data.getIsSelect());
                    viewBind.rvList.getAdapter().notifyDataSetChanged();
                });
    }

    @Override
    public void onLazyLoad() {
    }
}
