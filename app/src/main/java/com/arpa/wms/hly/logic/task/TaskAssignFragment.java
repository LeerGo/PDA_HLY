package com.arpa.wms.hly.logic.task;

import android.os.Bundle;
import android.view.View;

import com.arpa.wms.hly.R;
import com.arpa.wms.hly.base.BaseListFragment;
import com.arpa.wms.hly.bean.TaskAssignBean;
import com.arpa.wms.hly.ui.adapter.TaskAssignAdapter;
import com.arpa.wms.hly.utils.Const.IntentKey;

import androidx.appcompat.widget.AppCompatButton;
import butterknife.BindView;
import butterknife.OnClick;

/**
 * author: 李一方(<a href="mailto:leergo@dingtalk.com">leergo@dingtalk.com</a>)<br/>
 * version: 1.0.0<br/>
 * since: 2021-04-22 10:54 AM
 *
 * <p>
 * 内容描述区域
 * </p>
 */
public class TaskAssignFragment <T> extends BaseListFragment<T> {
    @BindView(R.id.btn_assign_keeper)
    AppCompatButton btnAssignKeeper;
    @BindView(R.id.btn_assign_stevedore)
    AppCompatButton btnAssignStevedore;
    @BindView(R.id.btn_assign_forklift)
    AppCompatButton btnAssignForklift;

    private int type;

    public static TaskAssignFragment<TaskAssignBean> newInstance(int index) {
        TaskAssignFragment<TaskAssignBean> fragment = new TaskAssignFragment<>();
        Bundle args = new Bundle();
        args.putInt(IntentKey.INDEX, index);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_task_assign;
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void initData() {
        super.initData();
        adapter = new TaskAssignAdapter<>(getActivity());
        type = getArguments().getInt(IntentKey.INDEX);
    }

    @Override
    protected void setViews() {
        // TODO: 这里的 type 魔法数抽取到 const 里 @lyf 2021-04-22 01:30:26
        if (type == 0) {
            btnAssignKeeper.setText("分配保管员");
            btnAssignStevedore.setText("分配装卸工");
            btnAssignForklift.setText("分配叉车工");
        } else {
            btnAssignKeeper.setText("取消保管员");
            btnAssignStevedore.setText("取消装卸工");
            btnAssignForklift.setText("取消叉车工");
        }
        super.setViews();
    }

    @OnClick({R.id.ib_all})
    public void onClick(View view) {
        if (view.getId() == R.id.ib_all) {
            view.setSelected(!view.isSelected());
        }
    }
}
