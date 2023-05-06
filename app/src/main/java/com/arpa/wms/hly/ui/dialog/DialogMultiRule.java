package com.arpa.wms.hly.ui.dialog;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.arpa.wms.hly.R;
import com.arpa.wms.hly.base.BaseBottomDialogFragment;
import com.arpa.wms.hly.bean.SNCutRule;
import com.arpa.wms.hly.databinding.DialogMultiRuleBinding;
import com.arpa.wms.hly.ui.dialog.vm.VMDialogMultiRule;
import com.arpa.wms.hly.ui.listener.ViewListener;
import com.arpa.wms.hly.utils.Const;

import java.util.HashMap;
import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;

/**
 * author: 李一方(<a href="mailto:leergo@dingtalk.com">leergo@dingtalk.com</a>)<br/>
 * version: 1.0.0<br/>
 * since: 2023-05-06 10:33
 *
 * <p>
 * dialog: 多规则选择弹窗
 * </p>
 */
@AndroidEntryPoint
public class DialogMultiRule extends BaseBottomDialogFragment<VMDialogMultiRule, DialogMultiRuleBinding> {
    private ViewListener.DataTransCallback<HashMap<Integer, SNCutRule>> callback;

    public static DialogMultiRule newInstance(HashMap<Integer, List<SNCutRule>> data, HashMap<Integer, SNCutRule> last, ViewListener.DataTransCallback<HashMap<Integer, SNCutRule>> callback) {
        Bundle args = new Bundle();
        args.putSerializable(Const.IntentKey.DATA, data);
        args.putSerializable(Const.IntentKey.RECORD, last);
        DialogMultiRule fragment = new DialogMultiRule(callback);
        fragment.setArguments(args);
        return fragment;
    }

    private DialogMultiRule(ViewListener.DataTransCallback<HashMap<Integer, SNCutRule>> callback) {
        this.callback = callback;
    }

    @Override
    public int getLayoutId() {
        return R.layout.dialog_multi_rule;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        super.initData(savedInstanceState);

        viewBind.setViewModel(viewModel);
        viewModel.initParams(requireArguments());
        viewBind.btnSure.setOnClickListener(v -> {
            if (viewModel.dataSel.isEmpty()) {
                viewModel.sendMessage("请选中一条切分规则");
                return;
            }
            callback.transfer(viewModel.dataSel);
            dismiss();
        });
    }

    @Override
    public boolean isBinding() {
        return true;
    }
}
