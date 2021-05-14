package com.arpa.wms.hly.ui.dialog;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import com.arpa.and.wms.arch.base.BaseDialogFragment;
import com.arpa.wms.hly.R;
import com.arpa.wms.hly.ui.listener.ViewListener.VoidCallback;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;

/**
 * author: 李一方(<a href="mailto:leergo@dingtalk.com">leergo@dingtalk.com</a>)<br/>
 * version: 1.0.0<br/>
 * since: 2021-04-25 3:58 PM
 *
 * <p>
 * Dialog: 选择日期
 * </p>
 */
public class DialogTips extends BaseDialogFragment {
    private String title;
    private String content;
    private VoidCallback onSure;
    private VoidCallback onCancel;

    public DialogTips(String content, VoidCallback onSure) {
        this(null, content, onSure);

    }

    public DialogTips(String title, String content, VoidCallback onSure) {
        this(title, content, onSure, null);
    }

    public DialogTips(String title, String content, VoidCallback onSure, VoidCallback onCancel) {
        this.title = title;
        this.content = content;
        this.onSure = onSure;
        this.onCancel = onCancel;
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

        tvContent.setText(content);
        if (TextUtils.isEmpty(title)) {
            tvTitle.setVisibility(View.GONE);
        } else {
            tvTitle.setText(title);
            tvTitle.setVisibility(View.VISIBLE);
        }
    }

    private void setOptArea() {
        findViewById(R.id.tv_cancel).setOnClickListener(v -> onOpt(onCancel));
        findViewById(R.id.tv_sure).setOnClickListener(v -> onOpt(onSure));
    }

    private void onOpt(VoidCallback callback) {
        dismiss();
        if (null != callback) callback.call();
    }
}