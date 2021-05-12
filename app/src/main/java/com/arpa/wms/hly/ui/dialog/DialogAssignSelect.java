package com.arpa.wms.hly.ui.dialog;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.arpa.and.wms.arch.base.BaseDialogFragment;
import com.arpa.wms.hly.R;
import com.arpa.wms.hly.bean.res.ResWarehouse;
import com.arpa.wms.hly.ui.adapter.DialogAssignSelectAdapter;
import com.arpa.wms.hly.ui.decoration.ItemDecorationUtil;
import com.arpa.wms.hly.ui.listener.ViewListener.DataClickListener;
import com.arpa.wms.hly.utils.ToastUtils;

import java.util.List;

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
public class DialogAssignSelect extends BaseDialogFragment {
    private final String title;
    private final List<String> workType;
    private final List<ResWarehouse> staffList;
    private final DataClickListener<ResWarehouse> listener;

    private ResWarehouse result;

    public DialogAssignSelect(String title, List<ResWarehouse> staffList, DataClickListener<ResWarehouse> listener) {
        this(title, null, staffList, listener);
    }

    public DialogAssignSelect(String title, List<String> workType, List<ResWarehouse> staffList, DataClickListener<ResWarehouse> listener) {
        this.title = title;
        this.workType = workType;
        this.staffList = staffList;
        this.listener = listener;
    }

    @Override
    protected void setWindow(Window window, float widthRatio) {
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.width = getWidthPixels();
        lp.gravity = Gravity.BOTTOM;
        lp.windowAnimations = R.style.ArpaDialogAnimationTrans;
        window.setAttributes(lp);

    }

    @Override
    public boolean isBinding() {
        return false;
    }

    @Override
    public int getLayoutId() {
        return R.layout.dialog_assign_select;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        setTitles();
        setSelectItem();
        setOptArea();
    }

    private void setTitles() {
        LinearLayoutCompat llType = (LinearLayoutCompat) findViewById(R.id.ll_type);
        AppCompatTextView tvTitle = (AppCompatTextView) findViewById(R.id.tv_title);
        tvTitle.setText(title);

        if (null == workType) {
            llType.setVisibility(View.GONE);
        } else {
            llType.setVisibility(View.VISIBLE);
            setWorkType();
        }
    }

    private void setWorkType() {
        AppCompatSpinner acsWorkType = (AppCompatSpinner) findViewById(R.id.acs_work_type);
        // 建立数据源
        String[] mItems = getResources().getStringArray(R.array.languages);
        // 建立Adapter并且绑定数据源
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), R.layout.item_work_type, mItems);
        acsWorkType.setAdapter(adapter);
        acsWorkType.setGravity(Gravity.RIGHT);
        acsWorkType.setSelection(0, true);
        ((TextView) acsWorkType.getSelectedView()).setTextColor(getResources().getColor(R.color.colorPrimary));
        acsWorkType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ((TextView) view).setTextColor(getResources().getColor(R.color.colorPrimary));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void setSelectItem() {
        RecyclerView rvAssign = (RecyclerView) findViewById(R.id.rv_assign);

        DialogAssignSelectAdapter adapter = new DialogAssignSelectAdapter(getContext());
        adapter.setOnItemClickListener((view, position, data) -> result = data);
        rvAssign.addItemDecoration(ItemDecorationUtil.getDividerBottom(R.drawable.divider_line_horizontal));
        rvAssign.setAdapter(adapter);
        adapter.addAll(staffList);
    }

    private void setOptArea() {
        AppCompatButton btnCancel = (AppCompatButton) findViewById(R.id.apt_cancel);
        AppCompatButton btnSure = (AppCompatButton) findViewById(R.id.apt_sure);

        btnCancel.setOnClickListener(v -> dismiss());
        btnSure.setOnClickListener(v -> {
            if (null == result) {
                ToastUtils.showShort("请选择一个仓库");
            } else {
                listener.transfer(result);
                dismiss();
            }
        });
    }
}