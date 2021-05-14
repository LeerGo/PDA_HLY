package com.arpa.wms.hly.ui.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.widget.RelativeLayout;

import com.arpa.wms.hly.R;
import com.arpa.wms.hly.ui.listener.ViewListener.DataTransCallback;

import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.databinding.BindingAdapter;
import androidx.databinding.InverseBindingAdapter;
import androidx.databinding.InverseBindingListener;

/**
 * author: 李一方(<a href="mailto:leergo@dingtalk.com">leergo@dingtalk.com</a>)<br/>
 * version: 1.0.0<br/>
 * since: 2021-04-22 2:07 PM
 *
 * <p>
 * 控件：属性输入条目
 * </p>
 */
public class WidgetInputItem extends RelativeLayout {
    private AppCompatTextView tvTitle;
    private AppCompatEditText etInput;
    private AppCompatImageView ivIcon;

    private boolean isEnable = true;
    private DataTransCallback<String> onTextChanged;

    public WidgetInputItem(@NonNull Context context) {
        super(context);
    }

    public WidgetInputItem(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        initViews(context);
        initAttrs(context, attrs);
    }

    private void initViews(Context context) {
        LayoutInflater.from(context).inflate(R.layout.widget_input_item, this, true);
        tvTitle = findViewById(R.id.tv_title);
        etInput = findViewById(R.id.et_input);
        ivIcon = findViewById(R.id.iv_icon);
        addTextWatcher();
    }

    private void addTextWatcher() {
        etInput.setOnFocusChangeListener((v, hasFocus) -> {
            if (hasFocus) {
                ((AppCompatEditText) v).addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        if (null != onTextChanged) onTextChanged.transfer(s.toString());
                    }

                    @Override
                    public void afterTextChanged(Editable s) {
                    }
                });
            }
        });
    }

    private void initAttrs(Context context, AttributeSet attrs) {
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.WidgetInputItem);
        setInputTitle(array.getString(R.styleable.WidgetInputItem_inputTitle));
        setInputText(array.getString(R.styleable.WidgetInputItem_inputText));
        setInputHint(array.getString(R.styleable.WidgetInputItem_inputHint));
        setInputIcon(array.getDrawable(R.styleable.WidgetInputItem_inputIcon));
        setInputEnable(array.getBoolean(R.styleable.WidgetInputItem_inputEnable, true));
        setInputGravity(array.getInt(R.styleable.WidgetInputItem_inputGravity, -1));
        array.recycle();
    }

    private void setInputTitle(String title) {
        tvTitle.setText(title);
    }

    private void setInputEnable(boolean isEnable) {
        this.isEnable = isEnable;
        etInput.setEnabled(isEnable);
    }

    public void setInputGravity(int gravity) {
        if (gravity == -1) etInput.setGravity(Gravity.END);
        else etInput.setGravity(gravity);
    }

    private void setInputIcon(Drawable drawable) {
        if (null != drawable) {
            ivIcon.setVisibility(VISIBLE);
            ivIcon.setImageDrawable(drawable);
        } else {
            ivIcon.setVisibility(GONE);
        }
    }

    private void setInputHint(String hint) {
        etInput.setHint(hint);
    }

    @BindingAdapter({"inputText"})
    public static void setInputText(WidgetInputItem view, String text) {
        view.setInputText(text);
    }

    @InverseBindingAdapter(attribute = "inputText")
    public static String getInputText(WidgetInputItem view) {
        return view.getInputText();
    }

    private String getInputText() {
        return Objects.requireNonNull(etInput.getText()).toString();
    }

    public void setInputText(String text) {
        etInput.setText(text);
    }

    @BindingAdapter("inputTextAttrChanged")
    public static void setOnAttrsChangeList(WidgetInputItem view, InverseBindingListener listener) {
        view.setOnTextChanged(data -> listener.onChange());
    }

    public void setOnTextChanged(DataTransCallback<String> onTextChanged) {
        this.onTextChanged = onTextChanged;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return !isEnable;
    }
}
