package com.arpa.wms.hly.ui.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;

import com.arpa.wms.hly.R;
import com.arpa.wms.hly.ui.listener.ViewListener.DataTransCallback;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.LinearLayoutCompat;

/**
 * author: 李一方(<a href="mailto:leergo@dingtalk.com">leergo@dingtalk.com</a>)<br/>
 * version: 1.0.0<br/>
 * since: 2021-04-22 9:42 AM
 *
 * <p>
 * 控件：搜索框封装
 * </p>
 */
public class WidgetSearchBar extends LinearLayoutCompat {
    private EditText etKey;
    private AppCompatImageView ivClear;
    private DataTransCallback<String> onSearchClick;
    private View.OnClickListener onClearClick;
    private boolean doClear = true;
    private long lastTimestamp = -1;

    public WidgetSearchBar(Context context) {
        super(context);
    }

    public WidgetSearchBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        initViews(context);
        initAttrs(context, attrs);
    }

    private void initViews(Context context) {
        LayoutInflater.from(context).inflate(R.layout.widget_search_bar, this, true);

        ivClear = findViewById(R.id.iv_clear);
        etKey = findViewById(R.id.et_key);
        etKey.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_SEARCH || (event != null && event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) {
                doSearch(v);
            }
            return false;
        });
        etKey.setOnFocusChangeListener((v, hasFocus) -> ivClear.setVisibility(hasFocus ? VISIBLE : GONE));
        ivClear.setOnClickListener(v -> {
            etKey.setText("");
            if (doClear) doSearch(v);
            if (null != onClearClick) onClearClick.onClick(v);
        });
        findViewById(R.id.ib_search).setOnClickListener(this::doSearch);
    }

    private void doSearch(View v) {
        if (null != onSearchClick) {
            // FIX：时间戳流控，解决一下两个问题  @lyf 2021-07-08 10:39:02
            //      1. 快速点击右侧搜索按钮时会请求多次，UI 上表现为加载多个页面
            //      2. 点击软键盘搜索时，出于兼容对 enter、search 两种模式同步监听
            //         有时就会出先 search 一次随后发出 enter，从而发起两次请求，
            //         在 UI 上就表现为唤起多个页面的问题
            long currentTimestamp = System.currentTimeMillis();
            if (currentTimestamp - lastTimestamp >= 1000) {
                lastTimestamp = System.currentTimeMillis();
                onSearchClick.transfer(etKey.getText().toString().trim());
            }
        }
    }

    private void initAttrs(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.WidgetSearchBar);
        setWsbHint(typedArray.getString(R.styleable.WidgetSearchBar_wsbHint));
        setWsbText(typedArray.getString(R.styleable.WidgetSearchBar_wsbText));
        typedArray.recycle();
    }

    public void setWsbText(String text) {
        etKey.setText(text);
    }

    public void setWsbHint(String hint) {
        etKey.setHint(hint);
    }

    public void setOnSearchClick(DataTransCallback<String> onSearchClick) {
        this.onSearchClick = onSearchClick;
    }

    public void setOnClearClick(OnClickListener onClearClick) {
        this.onClearClick = onClearClick;
    }

    public boolean isDoClear() {
        return doClear;
    }

    public void setDoClear(boolean doClear) {
        this.doClear = doClear;
    }
}
