package com.arpa.wms.hly.ui.dialog;

import android.os.Bundle;
import android.widget.ImageView;

import com.arpa.and.arch.base.BaseDialogFragment;
import com.arpa.wms.hly.R;
import com.arpa.wms.hly.bean.res.ResWarehouse;
import com.arpa.wms.hly.ui.adapter.DialogWarehouseSelectAdapter;
import com.arpa.wms.hly.ui.listener.ViewListener;
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
public class DialogWarehouseSelect extends BaseDialogFragment {
    private final List<ResWarehouse> warehouses;
    private final ViewListener.DataTransCallback<ResWarehouse> listener;

    private ResWarehouse result;

    public DialogWarehouseSelect(List<ResWarehouse> warehouses, ViewListener.DataTransCallback<ResWarehouse> listener) {
        this.warehouses = warehouses;
        this.listener = listener;
    }

    @Override
    public int getLayoutId() {
        return R.layout.dialog_warehouse_select;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        ImageView ivClose = (ImageView) findViewById(R.id.iv_close);
        AppCompatButton btnSure = (AppCompatButton) findViewById(R.id.btn_sure);
        RecyclerView rvWarehouse = (RecyclerView) findViewById(R.id.rv_warehouse);

        DialogWarehouseSelectAdapter adapter = new DialogWarehouseSelectAdapter(getContext());
        adapter.setOnItemClickListener((view, position, data) -> result = data);
        rvWarehouse.setAdapter(adapter);
        adapter.addAll(warehouses);

        ivClose.setOnClickListener(v -> dismiss());
        btnSure.setOnClickListener(v -> {
            if (null == result) {
                ToastUtils.showShort("请选择一个仓库");
            } else {
                listener.transfer(result);
                dismiss();
            }
        });
    }

    @Override
    public boolean isBinding() {
        return false;
    }
}