package com.arpa.wms.hly.ui.dialog;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.arpa.wms.hly.R;
import com.arpa.wms.hly.base.BaseBottomDialogFragment;
import com.arpa.wms.hly.bean.TaskJobType;
import com.arpa.wms.hly.bean.TaskStaffSelect;
import com.arpa.wms.hly.bean.res.ResTaskWorker;
import com.arpa.wms.hly.ui.adapter.DialogAssignSelectAdapter;
import com.arpa.wms.hly.ui.decoration.DrawableItemDecoration;
import com.arpa.wms.hly.ui.listener.ViewListener.DataTransCallback;
import com.arpa.wms.hly.utils.Const.ASSIGN_WORK;
import com.arpa.wms.hly.utils.ToastUtils;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.recyclerview.widget.RecyclerView;

/**
 * author: 李一方(<a href="mailto:leergo@dingtalk.com">leergo@dingtalk.com</a>)<br/>
 * version: 1.0.0<br/>
 * since: 2021-04-25 3:58 PM
 *
 * <p>
 * Dialog: 选择仓库
 * </p>
 */
public class DialogAssignSelect extends BaseBottomDialogFragment {
    private final int workType;
    private final ResTaskWorker data;
    private final DataTransCallback<TaskStaffSelect> listener;
    private final TaskStaffSelect result = new TaskStaffSelect();

    public DialogAssignSelect(int workType, ResTaskWorker data, DataTransCallback<TaskStaffSelect> listener) {
        this.data = data;
        this.workType = workType;
        this.listener = listener;
    }

    @Override
    public int getLayoutId() {
        return R.layout.dialog_assign_select;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        // 默认选中第一个 fix:http://192.168.31.42:801//zentao/bug-view-26065.html
        result.setJobType(data.getJobType().get(0));

        setTitles();
        setSelectItem();
        setOptArea();
    }

    /**
     * 设置标题
     */
    private void setTitles() {
        LinearLayoutCompat llType = (LinearLayoutCompat) findViewById(R.id.ll_type);
        AppCompatTextView tvTitle = (AppCompatTextView) findViewById(R.id.tv_title);
        switch (workType) {
            case ASSIGN_WORK.CUSTODIAN:
                tvTitle.setText("分配保管员");
                llType.setVisibility(View.GONE);
                break;
            case ASSIGN_WORK.FORKLIFT:
                tvTitle.setText("分配叉车工");
                llType.setVisibility(View.GONE);
                break;
            case ASSIGN_WORK.STEVEDORE:
                tvTitle.setText("分配装卸工");
                llType.setVisibility(View.VISIBLE);
                setWorkType();
                break;
        }
    }

    /**
     * 设置
     */
    @SuppressLint("RtlHardcoded")
    private void setWorkType() {
        AppCompatSpinner acsWorkType = (AppCompatSpinner) findViewById(R.id.acs_work_type);
        // 建立Adapter并且绑定数据源
        ArrayAdapter<TaskJobType> adapter = new ArrayAdapter<>(getContext(), R.layout.item_work_type, data.getJobType());
        acsWorkType.setAdapter(adapter);
        acsWorkType.setGravity(Gravity.RIGHT);
        acsWorkType.setSelection(0, true);
        ((TextView) acsWorkType.getSelectedView()).setTextColor(getResources().getColor(R.color.colorPrimary));
        acsWorkType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ((TextView) view).setTextColor(getResources().getColor(R.color.colorPrimary));
                result.setJobType(data.getJobType().get(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    /**
     * 设置选中数据
     */
    private void setSelectItem() {
        RecyclerView rvAssign = (RecyclerView) findViewById(R.id.rv_assign);

        DialogAssignSelectAdapter adapter = new DialogAssignSelectAdapter(requireContext());
        adapter.setOnItemClickListener((view, position, data) -> {
            if (data.isSelect()) result.addStaff(data);
            else result.removeStaff(data);
        });
        rvAssign.addItemDecoration(DrawableItemDecoration.getDivider(requireContext()));
        rvAssign.setAdapter(adapter);
        adapter.addAll(data.getStaff());
    }

    private void setOptArea() {
        AppCompatButton btnCancel = (AppCompatButton) findViewById(R.id.apt_cancel);
        AppCompatButton btnSure = (AppCompatButton) findViewById(R.id.apt_sure);

        btnCancel.setOnClickListener(v -> dismiss());
        btnSure.setOnClickListener(v -> {
            if (workType == ASSIGN_WORK.STEVEDORE && null == result.getJobType()) {
                ToastUtils.showShort("请选择作业类型");
            }
            if (result.getStaffs().isEmpty()) {
                ToastUtils.showShort("请选择工作人员");
                return;
            }
            listener.transfer(result);
            dismiss();
        });
    }
}