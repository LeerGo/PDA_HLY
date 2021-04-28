package com.arpa.wms.hly.ui.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;

import com.arpa.wms.hly.R;
import com.arpa.wms.hly.ui.listener.ViewListener.DataClickListener;

import androidx.appcompat.widget.LinearLayoutCompat;

/**
 * author: 李一方(<a href="mailto:leergo@dingtalk.com">leergo@dingtalk.com</a>)<br/>
 * version: 1.0.0<br/>
 * since: 2021-04-22 9:42 AM
 *
 * <p>
 * 内容描述区域
 * </p>
 */
public class WidgetSearchBar extends LinearLayoutCompat {
    private EditText etKey;
    private DataClickListener<String> onSearchClick;

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

        etKey = findViewById(R.id.et_key);
        etKey.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                extracted(v);
            }
            return false;
        });
        findViewById(R.id.ib_search).setOnClickListener(this::extracted);
    }

    private void extracted(View v) {
        if (null != onSearchClick) {
            onSearchClick.transfer(etKey.getText().toString().trim());
        }
    }

    private void initAttrs(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.WidgetSearchBar);
        setWsbHint(typedArray.getString(R.styleable.WidgetSearchBar_wsbHint));
        typedArray.recycle();
    }

    public void setWsbHint(String hint) {
        etKey.setHint(hint);
    }

    public void setOnSearchClick(DataClickListener<String> onSearchClick) {
        this.onSearchClick = onSearchClick;
    }
}
