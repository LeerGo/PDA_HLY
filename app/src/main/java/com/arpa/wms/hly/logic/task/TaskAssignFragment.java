package com.arpa.wms.hly.logic.task;

import android.os.Bundle;

import com.arpa.wms.hly.R;
import com.arpa.wms.hly.base.WrapBaseLazyFragment;
import com.arpa.wms.hly.bean.res.ResTaskAssign;
import com.arpa.wms.hly.databinding.FragmentTaskAssignBinding;
import com.arpa.wms.hly.logic.task.vm.VMTaskAssign;
import com.arpa.wms.hly.ui.decoration.BothItemDecoration;
import com.arpa.wms.hly.ui.dialog.DialogAssignSelect;
import com.arpa.wms.hly.ui.dialog.DialogTips;
import com.arpa.wms.hly.utils.Const.ASSIGN_WORK;
import com.arpa.wms.hly.utils.Const.IntentKey;
import com.arpa.wms.hly.utils.ToastUtils;

import androidx.annotation.Nullable;
import dagger.hilt.android.AndroidEntryPoint;

import static com.arpa.wms.hly.utils.Const.ASSIGN_WORK.ASSIGN_NOT;
import static com.arpa.wms.hly.utils.Const.ASSIGN_WORK.WORKER_TYPE;
import static com.arpa.wms.hly.utils.Const.ASSIGN_WORK.WORKER_TYPE_NAME;

/**
 * author: 李一方(<a href="mailto:leergo@dingtalk.com">leergo@dingtalk.com</a>)<br/>
 * version: 1.0.0<br/>
 * since: 2021-04-22 10:54 AM
 *
 * <p>
 * Fragment：任务指派
 * </p>
 *
 * 注意一下特性，@李普芝 已经答复 @lyf 2021-06-16 05:23:15
 * 从【任务列表】跳转到【收货详情】、【拣货详情】，有几个小问题需要确认一下：
 * 1. 跳转到的【收货详情】、【拣货详情】是不是都不在区分“已收货/待收货”的两种状态？ok
 * 不区分，显示所有的
 * 2. 跳转到的【收货详情】、【拣货详情】列表里条目点击还需要跳转对应详情页面嘛？特别是拣货 OK
 * 不再跳转，只到收货详情、拣货详情页，可以查看就行
 * 3. 【任务列表】里，“待指派、已指派”两个列表是都需要点击条目跳转对应详情的嘛？ok
 * 都需要
 *
 * 任务列表，右上角显示状态还有【装车】@夏宝新 沟通 ok
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
    public void onLazyLoad() {
        viewModel.autoRefresh();
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        super.initData(savedInstanceState);

        viewBind.setViewModel(viewModel);
        viewBind.rvList.addItemDecoration(new BothItemDecoration());
        int type = requireArguments().getInt(IntentKey.INDEX, ASSIGN_NOT);
        viewModel.type.set(type);
        viewBind.btnAssignKeeper.setOnClickListener(view -> showDialog(type, ASSIGN_WORK.CUSTODIAN));
        viewBind.btnAssignStevedore.setOnClickListener(view -> showDialog(type, ASSIGN_WORK.STEVEDORE));
        viewBind.btnAssignForklift.setOnClickListener(view -> showDialog(type, ASSIGN_WORK.FORKLIFT));
        viewModel.resTaskWorker.observe(this, resTaskWorker ->
                showDialogFragment(new DialogAssignSelect(viewModel.workerType, resTaskWorker, data -> viewModel.taskAssign(data)))
        );
    }

    /**
     * 显示弹窗
     */
    private void showDialog(int type, int assignType) {
        if (type == ASSIGN_NOT) {
            assignStaffDialog(assignType);
        } else {
            cancelAssignStaffDialog(assignType);
        }
    }

    /**
     * 分配工作人员
     */
    private void assignStaffDialog(int assignType) {
        // 判断是否有选中的任务
        boolean isSelect = false;
        for (ResTaskAssign item : viewModel.getItems()) {
            if (item.isSelect()) {
                isSelect = true;
                break;
            }
        }

        // 存在选中的才能分配
        if (isSelect) {
            // 获取作业类型、人员的接口还没有 @lyf 2021-05-17 02:54:58
            viewModel.getWorkStaff(assignType);
        } else {
            ToastUtils.showShortSafe("尚未选择任务");
        }
    }

    /**
     * 取消分配工作人员
     */
    private void cancelAssignStaffDialog(int assignType) {
        showDialogFragment(new DialogTips("取消当前已分配的" + WORKER_TYPE_NAME[assignType], () -> viewModel.taskAssignCancel(WORKER_TYPE[assignType])));
    }
}
