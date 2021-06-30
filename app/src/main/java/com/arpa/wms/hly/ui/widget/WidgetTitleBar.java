package com.arpa.wms.hly.ui.widget;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.arpa.wms.hly.R;

/**
 * author: 李一方(<a href="mailto:leergo@dingtalk.com">leergo@dingtalk.com</a>)<br/>
 * version: 1.0.0<br/>
 * since: 2021-04-22 9:42 AM
 *
 * <p>
 * 内容描述区域
 * </p>
 */
public class WidgetTitleBar extends RelativeLayout {
    private ImageView ivBack;
    private TextView tvTitle;

    public WidgetTitleBar(Context context) {
        super(context);
    }

    public WidgetTitleBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        initViews(context);
        initAttrs(context, attrs);
        bindAction(context);
    }

    private void initViews(Context context) {
        LayoutInflater.from(context).inflate(R.layout.widget_title_bar, this, true);
        ivBack = findViewById(R.id.iv_back);
        tvTitle = findViewById(R.id.tv_title);
    }

    private void initAttrs(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.WidgetTitleBar);
        int padding = typedArray.getInteger(R.styleable.WidgetTitleBar_wtbBackPadding, 28);
        setWtbTitle(typedArray.getString(R.styleable.WidgetTitleBar_wtbTitle));
        if (typedArray.getBoolean(R.styleable.WidgetTitleBar_wtbShowBack, true)) {
            ivBack.setPadding(padding, padding, padding, padding);
            ivBack.setVisibility(VISIBLE);
        } else {
            ivBack.setVisibility(GONE);
        }
        typedArray.recycle();
    }

    public void setWtbTitle(String title) {
        tvTitle.setText(title);
    }

    private void bindAction(Context context) {
        ivBack.setOnClickListener(v -> ((Activity) context).finish());
    }
}
