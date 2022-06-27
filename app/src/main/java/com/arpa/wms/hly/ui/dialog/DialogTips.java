package com.arpa.wms.hly.ui.dialog;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;

import com.arpa.and.arch.base.BaseDialogFragment;
import com.arpa.wms.hly.R;
import com.arpa.wms.hly.ui.listener.ViewListener.VoidCallback;

/**
 * author: 李一方(<a href="mailto:leergo@dingtalk.com">leergo@dingtalk.com</a>)<br/>
 * version: 1.0.0<br/>
 * since: 2021-04-25 3:58 PM
 *
 * <p>
 * Dialog: 提示弹窗
 * </p>
 */
public class DialogTips extends BaseDialogFragment {
    private String title;
    private String content;
    private String btnLeft;
    private String btnRight;
    private VoidCallback onSure;
    private VoidCallback onCancel;

    public DialogTips(String content, VoidCallback onSure) {
        this(null, content, onSure);
    }

    public DialogTips(String title, String content, VoidCallback onSure) {
        this(title, content, onSure, null);
    }

    public DialogTips(String title, String content, VoidCallback onSure, VoidCallback onCancel) {
        this(title, content, null, null, onSure, onCancel);
    }

    public DialogTips(String title, String content, String btnLeft, String btnRight, VoidCallback onSure, VoidCallback onCancel) {
        this.title = title;
        this.content = content;
        this.btnLeft = btnLeft;
        this.btnRight = btnRight;
        this.onSure = onSure;
        this.onCancel = onCancel;
    }

    public DialogTips(String content) {
        this(null, content, null);
    }

    @Override
    public boolean isBinding() {
        return false;
    }

    @Override
    public int getLayoutId() {
        return R.layout.dialog_tips;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        setContent();
        setOptArea();
    }

    private void setContent() {
        AppCompatTextView tvTitle = (AppCompatTextView) findViewById(R.id.tv_title);
        AppCompatTextView tvContent = (AppCompatTextView) findViewById(R.id.tv_content);
        AppCompatTextView tvCancel = (AppCompatTextView) findViewById(R.id.tv_cancel);
        AppCompatTextView tvSure = (AppCompatTextView) findViewById(R.id.tv_sure);

        tvContent.setText(content);
        if (TextUtils.isEmpty(title)) {
            tvTitle.setVisibility(View.GONE);
        } else {
            tvTitle.setText(title);
            tvTitle.setVisibility(View.VISIBLE);
        }
        if (!TextUtils.isEmpty(btnLeft)) tvCancel.setText(btnLeft);
        if (!TextUtils.isEmpty(btnRight)) tvSure.setText(btnRight);
    }

    private void setOptArea() {
        AppCompatTextView tvCancel = (AppCompatTextView) findViewById(R.id.tv_cancel);
        AppCompatTextView tvSure = (AppCompatTextView) findViewById(R.id.tv_sure);
        tvSure.setOnClickListener(v -> onOpt(onSure));
        tvCancel.setOnClickListener(v -> onOpt(onCancel));
        if (null == onCancel) tvCancel.setVisibility(View.GONE);
    }

    private void onOpt(VoidCallback callback) {
        dismiss();
        if (null != callback) callback.call();
    }
}
