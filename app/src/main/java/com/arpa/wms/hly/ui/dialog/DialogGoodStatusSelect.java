package com.arpa.wms.hly.ui.dialog;

import android.os.Bundle;
import android.text.TextUtils;

import com.arpa.wms.hly.R;
import com.arpa.wms.hly.base.BaseBottomDialogFragment;
import com.arpa.wms.hly.bean.InventoryStatus;
import com.arpa.wms.hly.ui.adapter.DialogGoodStatusAdapter;
import com.arpa.wms.hly.ui.decoration.DrawableItemDecoration;
import com.arpa.wms.hly.ui.listener.ViewListener.DataTransCallback;
import com.arpa.wms.hly.utils.ToastUtils;

import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
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
public class DialogGoodStatusSelect extends BaseBottomDialogFragment {
    private final List<InventoryStatus> staffList;
    private final DataTransCallback<InventoryStatus> listener;
    private InventoryStatus result;


    public DialogGoodStatusSelect(String currentStatusCode, List<InventoryStatus> staffList, DataTransCallback<InventoryStatus> listener) {
        this.staffList = staffList;
        this.listener = listener;
        if (!TextUtils.isEmpty(currentStatusCode)) {
            result = new InventoryStatus(currentStatusCode);
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.dialog_good_status_select;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        setSelectItem();
        setOptArea();
    }

    private void setSelectItem() {
        RecyclerView rvAssign = (RecyclerView) findViewById(R.id.rv_assign);

        DialogGoodStatusAdapter adapter = new DialogGoodStatusAdapter(requireContext(), staffList.indexOf(result));
        adapter.setOnItemClickListener((view, position, data) -> result = data);
        rvAssign.addItemDecoration(DrawableItemDecoration.getDivider(requireContext()));
        rvAssign.setAdapter(adapter);
        adapter.addAll(staffList);
    }

    private void setOptArea() {
        AppCompatButton btnCancel = (AppCompatButton) findViewById(R.id.apt_cancel);
        AppCompatButton btnSure = (AppCompatButton) findViewById(R.id.apt_sure);

        btnCancel.setOnClickListener(v -> dismiss());
        btnSure.setOnClickListener(v -> {
            if (null == result) {
                ToastUtils.showShort("请选择一个收货状态");
            } else {
                listener.transfer(result);
                dismiss();
            }
        });
    }
}