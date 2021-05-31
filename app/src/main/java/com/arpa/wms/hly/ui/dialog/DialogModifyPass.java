package com.arpa.wms.hly.ui.dialog;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Toast;

import com.arpa.and.arch.base.BaseDialogFragment;
import com.arpa.wms.hly.R;
import com.arpa.wms.hly.bean.req.ReqModifyPass;
import com.arpa.wms.hly.ui.listener.ViewListener.DataTransCallback;

import java.util.Objects;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatEditText;

/**
 * author: 李一方(<a href="mailto:leergo@dingtalk.com">leergo@dingtalk.com</a>)<br/>
 * version: 1.0.0<br/>
 * since: 2021-04-25 3:58 PM
 *
 * <p>
 * Dialog: 选择日期
 * </p>
 */
public class DialogModifyPass extends BaseDialogFragment {
    private final DataTransCallback<ReqModifyPass> onSure;
    private final ReqModifyPass data = new ReqModifyPass();
    private AppCompatEditText etOldPass;
    private AppCompatEditText etNewPass;
    private AppCompatEditText etNewPassRepeat;

    public DialogModifyPass(DataTransCallback<ReqModifyPass> onSure) {
        this.onSure = onSure;
    }

    @Override
    public boolean isBinding() {
        return false;
    }

    @Override
    public int getLayoutId() {
        return R.layout.dialog_modify_pass;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        setContent();
        setOptArea();
    }

    private void setContent() {
        etOldPass = (AppCompatEditText) findViewById(R.id.et_old_pass);
        etNewPass = (AppCompatEditText) findViewById(R.id.et_new_pass);
        etNewPassRepeat = (AppCompatEditText) findViewById(R.id.et_new_pass_repeat);
    }

    private void setOptArea() {
        findViewById(R.id.iv_close).setOnClickListener(v -> dismiss());
        findViewById(R.id.acb_sure).setOnClickListener(v -> {
            data.setOldPwd(Objects.requireNonNull(etOldPass.getText()).toString());
            data.setNewPwd(Objects.requireNonNull(etNewPass.getText()).toString());
            data.setNewRepwd(Objects.requireNonNull(etNewPassRepeat.getText()).toString());
            if (verify(data)) {
                dismiss();
                onSure.transfer(data);
            }
        });
    }

    private boolean verify(ReqModifyPass data) {
        if (TextUtils.isEmpty(data.getOldPwd())) {
            Toast.makeText(requireActivity().getApplicationContext(), "请输入原密码", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (TextUtils.isEmpty(data.getNewPwd())) {
            Toast.makeText(getActivity(), "请输入新密码", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (TextUtils.isEmpty(data.getNewRepwd())) {
            Toast.makeText(getActivity(), "请再次输入新密码", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (!data.getNewPwd().equals(data.getNewRepwd())) {
            Toast.makeText(getActivity(), "输入两次新密码不一致", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}