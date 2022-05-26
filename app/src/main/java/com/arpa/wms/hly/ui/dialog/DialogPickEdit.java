package com.arpa.wms.hly.ui.dialog;

import android.os.Bundle;

import com.arpa.and.arch.base.BaseDialogFragment;
import com.arpa.wms.hly.R;
import com.arpa.wms.hly.bean.PickingItemVO;
import com.arpa.wms.hly.databinding.DialogPickEditBinding;
import com.arpa.wms.hly.ui.dialog.vm.VMDialogPickEdit;
import com.arpa.wms.hly.ui.listener.ViewListener;
import com.arpa.wms.hly.utils.ToastUtils;

import java.util.List;

import androidx.annotation.Nullable;
import dagger.hilt.android.AndroidEntryPoint;

/**
 * author: 李一方(<a href="mailto:leergo@dingtalk.com">leergo@dingtalk.com</a>)<br/>
 * version: 1.0.0<br/>
 * since: 2022/5/13 09:15
 *
 * <p>
 * 弹窗：拣货编辑
 * </p>
 */
@AndroidEntryPoint
public class DialogPickEdit extends BaseDialogFragment<VMDialogPickEdit, DialogPickEditBinding> {
    private ViewListener.DataTransCallback<List<PickingItemVO>> listener;

    public DialogPickEdit(ViewListener.DataTransCallback<List<PickingItemVO>> listener) {
        this.listener = listener;
    }

    @Override
    public int getLayoutId() {
        return R.layout.dialog_pick_edit;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        registerMessageEvent(ToastUtils::showShortSafe);
        viewBind.setViewModel(viewModel);
        viewBind.btnCancel.setOnClickListener(v -> dismiss());
        viewBind.btnSure.setOnClickListener(V -> {
            if (viewModel.checkItemsValid()) {
                listener.transfer(viewModel.items);
                dismiss();
            }
        });
    }
}
