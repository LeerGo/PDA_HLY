package com.arpa.wms.hly.ui.dialog;

import android.os.Bundle;

import com.arpa.wms.hly.R;
import com.arpa.wms.hly.base.BaseBottomDialogFragment;
import com.arpa.wms.hly.bean.GoodsScanSelectItem;
import com.arpa.wms.hly.ui.adapter.DialogGoodsScanAdapter;
import com.arpa.wms.hly.ui.decoration.DrawableItemDecoration;
import com.arpa.wms.hly.ui.listener.ViewListener.DataTransCallback;
import com.arpa.wms.hly.utils.ToastUtils;

import java.util.ArrayList;
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
 * Dialog: 是否扫码
 * </p>
 */
public class DialogGoodsTaskScan extends BaseBottomDialogFragment {
    private final DataTransCallback<GoodsScanSelectItem> listener;
    private final List<GoodsScanSelectItem> items = new ArrayList<>();
    private GoodsScanSelectItem result;

    public DialogGoodsTaskScan(DataTransCallback<GoodsScanSelectItem> listener) {
        this.listener = listener;
        items.add(new GoodsScanSelectItem(0, "不扫码"));
        items.add(new GoodsScanSelectItem(1, "扫码"));
    }

    @Override
    public int getLayoutId() {
        return R.layout.dialog_goods_take_scan;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        setSelectItem();
        setOptArea();
    }

    /**
     * 设置选中数据
     */
    private void setSelectItem() {
        RecyclerView rvAssign = (RecyclerView) findViewById(R.id.rv_assign);

        DialogGoodsScanAdapter adapter = new DialogGoodsScanAdapter(requireContext());
        adapter.setOnItemClickListener((view, position, data) -> result = data);
        adapter.addAll(items);
        rvAssign.addItemDecoration(DrawableItemDecoration.getDivider(requireContext()));
        rvAssign.setAdapter(adapter);
    }

    private void setOptArea() {
        AppCompatButton btnCancel = (AppCompatButton) findViewById(R.id.apt_cancel);
        AppCompatButton btnSure = (AppCompatButton) findViewById(R.id.apt_sure);

        btnCancel.setOnClickListener(v -> dismiss());
        btnSure.setOnClickListener(v -> {
            if (result == null) {
                ToastUtils.showShort("请选择是否需要扫描");
            }
            listener.transfer(result);
            dismiss();
        });
    }
}