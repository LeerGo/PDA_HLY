package com.arpa.wms.hly.ui.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.DigitsKeyListener;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.databinding.BindingAdapter;
import androidx.databinding.InverseBindingAdapter;
import androidx.databinding.InverseBindingListener;

import com.arpa.wms.hly.R;
import com.arpa.wms.hly.ui.filter.InputFilterMinMax;
import com.arpa.wms.hly.ui.listener.ViewListener.DataTransCallback;

import java.util.Objects;

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
    private static final String DIGITS_NUMBER = "1234567890";

    private AppCompatTextView tvTitle;
    private AppCompatEditText etInput;
    private AppCompatImageView ivIcon;

    private int inputType;
    private int maxValue = -1;
    private int minValue = 0;
    private String digits;
    private boolean isEnable = true;
    private DataTransCallback<String> onSearchClick;
    private DataTransCallback<String> onTextChanged;
    private final TextWatcher watcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            //判断当前的输入第一个数是不是为0
            if (s.toString().matches("^0")) {
                etInput.setText("");
            } else {
                if (null != onTextChanged) onTextChanged.transfer(s.toString());
            }
        }

        @Override
        public void afterTextChanged(Editable s) {
        }
    };

    public WidgetInputItem(@NonNull Context context) {
        super(context);
    }

    public WidgetInputItem(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        initViews(context);
        initAttrs(context, attrs);
        addFocusListener();
    }

    private void initViews(Context context) {
        LayoutInflater.from(context).inflate(R.layout.widget_input_item, this, true);
        tvTitle = findViewById(R.id.tv_title);
        etInput = findViewById(R.id.et_input);
        ivIcon = findViewById(R.id.iv_icon);
    }

    private void initAttrs(Context context, AttributeSet attrs) {
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.WidgetInputItem);
        setInputTitle(array.getString(R.styleable.WidgetInputItem_inputTitle));
        setInputText(array.getString(R.styleable.WidgetInputItem_inputText));
        setInputHint(array.getString(R.styleable.WidgetInputItem_inputHint));
        setInputIcon(array.getDrawable(R.styleable.WidgetInputItem_inputIcon));
        setInputEnable(array.getBoolean(R.styleable.WidgetInputItem_inputEnable, true));
        setInputGravity(array.getInt(R.styleable.WidgetInputItem_inputGravity, -1));
        setInputLength(array.getInt(R.styleable.WidgetInputItem_inputLength, Integer.MAX_VALUE));
        setInputImeOptions(array.getInt(R.styleable.WidgetInputItem_inputImeOptions, EditorInfo.IME_ACTION_UNSPECIFIED));
        inputType = array.getInt(R.styleable.WidgetInputItem_inputType, EditorInfo.TYPE_CLASS_TEXT);
        digits = array.getString(R.styleable.WidgetInputItem_inputDigits);
        setInputType(inputType);
        array.recycle();
    }

    private void setInputType(int inputType) {
        etInput.setInputType(inputType);
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

    public void setInputLength(Integer inputLength) {
        etInput.setFilters(new InputFilter[]{new InputFilter.LengthFilter(inputLength)});
    }

    private void addFocusListener() {
        etInput.setOnFocusChangeListener((v, hasFocus) -> {
            if (hasFocus) {
                etInput.addTextChangedListener(watcher);
                if (inputType == EditorInfo.TYPE_CLASS_NUMBER) {
                    setInputDigits(DIGITS_NUMBER);
                    setInputLimit();
                } else {
                    setInputDigits(digits);
                }
            } else {
                etInput.removeTextChangedListener(watcher);
            }
        });
    }

    private void setInputLimit() {
        maxValue = maxValue == -1 ? Integer.MAX_VALUE : maxValue;
        minValue = Math.max(minValue, 0);
        etInput.setFilters(new InputFilter[]{new InputFilterMinMax(minValue, maxValue)});
    }

    private void setInputDigits(String digits) {
        if (!TextUtils.isEmpty(digits))
            etInput.setKeyListener(DigitsKeyListener.getInstance(digits));
    }

    @BindingAdapter(value = {"inputMax", "inputMin"}, requireAll = false)
    public static void setInputMax(WidgetInputItem view, int maxValue, int minValue) {
        view.setMaxValue(maxValue);
        view.setMinValue(minValue);
    }

    public void setMaxValue(int maxValue) {
        this.maxValue = maxValue;
        setInputLimit();
    }

    public void setMinValue(int minValue) {
        this.minValue = minValue;
        setInputLimit();
    }

    @BindingAdapter("inputValue")
    public static void setInputValue(WidgetInputItem view, Integer value) {
        view.setInputText(null == value ? "" : value.toString());
    }

    @InverseBindingAdapter(attribute = "inputText")
    public static String getInputText(WidgetInputItem view) {
        return view.getInputText();
    }

    public String getInputText() {
        return Objects.requireNonNull(etInput.getText()).toString();
    }

    public void setInputText(String text) {
        if (null != text && text.equals(etInput.getText().toString())) return;
        etInput.setText(text);
        if (!TextUtils.isEmpty(text)) etInput.setSelection(text.length());
    }

    @InverseBindingAdapter(attribute = "inputValue")
    public static Integer getInputValue(WidgetInputItem view) {
        if (TextUtils.isEmpty(view.getInputText())) return null;
        return Integer.parseInt(view.getInputText());
    }

    @BindingAdapter({"inputTextAttrChanged"})
    public static void setInputTextAttrChanged(WidgetInputItem view, InverseBindingListener listener) {
        view.setOnTextChanged(data -> listener.onChange());
    }

    public void setOnTextChanged(DataTransCallback<String> onTextChanged) {
        this.onTextChanged = onTextChanged;
    }

    @BindingAdapter({"inputValueAttrChanged"})
    public static void setInputValueAttrChanged(WidgetInputItem view, InverseBindingListener listener) {
        view.setOnTextChanged(data -> listener.onChange());
    }

    @BindingAdapter("inputFocus")
    public static void setInputFocus(WidgetInputItem view, Boolean inputFocus) {
        view.setInputFocus(null != inputFocus && inputFocus);
    }

    /**
     * 设置输入焦点
     */
    private void setInputFocus(boolean inputFocus) {
        if (inputFocus) {
            etInput.setFocusableInTouchMode(true);
            etInput.setFocusable(true);
            etInput.findFocus();
            etInput.requestFocus();
        } else {
            etInput.setFocusable(false);
            etInput.clearFocus();
        }
    }

    private void setInputImeOptions(int imeOption) {
        etInput.setImeOptions(imeOption);
        etInput.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_SEARCH || (event != null && event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) {
                doSearch(v);
            }
            return false;
        });
    }

    private long lastTimestamp = -1;

    private void doSearch(TextView v) {
        if (null != onSearchClick) {
            // FIX：时间戳流控，解决一下两个问题  @lyf 2021-07-08 10:39:02
            //      1. 快速点击右侧搜索按钮时会请求多次，UI 上表现为加载多个页面
            //      2. 点击软键盘搜索时，出于兼容对 enter、search 两种模式同步监听
            //         有时就会出先 search 一次随后发出 enter，从而发起两次请求，
            //         在 UI 上就表现为唤起多个页面的问题
            long currentTimestamp = System.currentTimeMillis();
            if (currentTimestamp - lastTimestamp >= 1000) {
                lastTimestamp = System.currentTimeMillis();
                onSearchClick.transfer(etInput.getText().toString().trim());
            }
        }
    }

    public void setOnSearchClick(DataTransCallback<String> onSearchClick) {
        this.onSearchClick = onSearchClick;
    }

    @BindingAdapter(value = "inputAction")
    public static void setOnSearch(WidgetInputItem view, DataTransCallback<Object> callback) {
        if (callback != null) {
            view.setOnSearchClick(callback::transfer);
        }
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return !isEnable;
    }
}
