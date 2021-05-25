package com.arpa.wms.hly.logic.task;

import android.os.Bundle;

import com.arpa.wms.hly.BR;
import com.arpa.wms.hly.R;
import com.arpa.wms.hly.base.WrapBaseLazyFragment;
import com.arpa.wms.hly.databinding.FragmentTaskAssignBinding;
import com.arpa.wms.hly.logic.task.vm.VMTaskAssign;
import com.arpa.wms.hly.ui.decoration.ItemDecorationUtil;
import com.arpa.wms.hly.utils.Const.ASSIGN_WORK;
import com.arpa.wms.hly.utils.Const.IntentKey;

import androidx.annotation.Nullable;
import dagger.hilt.android.AndroidEntryPoint;

/**
 * author: 李一方(<a href="mailto:leergo@dingtalk.com">leergo@dingtalk.com</a>)<br/>
 * version: 1.0.0<br/>
 * since: 2021-04-22 10:54 AM
 *
 * <p>
 * Fragment：任务指派
 * </p>
 */
@AndroidEntryPoint
public class TaskAssignFragment extends WrapBaseLazyFragment<VMTaskAssign, FragmentTaskAssignBinding> {
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
        super.initData(savedInstanceState);

        viewBind.setVariable(BR.viewModel, viewModel);
        viewBind.rvList.addItemDecoration(ItemDecorationUtil.getDividerTop10DP());
        viewModel.type.set(getArguments() != null ? getArguments().getInt(IntentKey.INDEX, ASSIGN_WORK.ASSIGN_NOT) : ASSIGN_WORK.ASSIGN_NOT);
        viewBind.btnAssignKeeper.setOnClickListener(view -> showStaffDialog(ASSIGN_WORK.WORK_CUSTODIAN));
        viewBind.btnAssignStevedore.setOnClickListener(view -> showStaffDialog(ASSIGN_WORK.WORK_STEVEDORE));
        viewBind.btnAssignForklift.setOnClickListener(view -> showStaffDialog(ASSIGN_WORK.WORK_FORKLIFT));
    }

    private void showStaffDialog(String assignType) {
        // TODO: 获取作业类型、人员的接口还没有 @lyf 2021-05-17 02:54:58
        // showDialogFragment(new DialogAssignSelect());
    }

    @Override
    public void onLazyLoad() {
    }
}
