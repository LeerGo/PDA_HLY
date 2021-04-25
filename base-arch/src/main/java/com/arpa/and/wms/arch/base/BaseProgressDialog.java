package com.arpa.and.wms.arch.base;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;

import com.arpa.and.wms.arch.R;

import androidx.annotation.NonNull;

/**
 *
 */
public class BaseProgressDialog extends Dialog {

    public BaseProgressDialog(@NonNull Context context) {
        this(context, R.style.ArpaProgressDialog);
    }

    public BaseProgressDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
        initUI();
    }

    private void initUI() {
        getWindow().getAttributes().gravity = Gravity.CENTER;
        setCanceledOnTouchOutside(false);
    }

    public BaseProgressDialog(@NonNull Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        initUI();
    }

    public static BaseProgressDialog newInstance(Context context) {
        return new BaseProgressDialog(context);
    }

}