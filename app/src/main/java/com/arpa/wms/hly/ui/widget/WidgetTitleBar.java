package com.arpa.wms.hly.ui.widget;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.arpa.wms.hly.R;
import com.arpa.wms.hly.ui.listener.ViewListener;
import com.arpa.wms.hly.utils.DensityUtils;

/**
 * author: 李一方(<a href="mailto:leergo@dingtalk.com">leergo@dingtalk.com</a>)<br/>
 * version: 1.0.0<br/>
 * since: 2021-04-22 9:42 AM
 */
public class WidgetTitleBar extends RelativeLayout {
    @Deprecated
    public ViewListener.VoidCallback onBack;
    private ImageView ivBack;
    private TextView tvTitle;
    private TextView tvSub;

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
        tvSub = findViewById(R.id.tv_sub);
    }

    private void initAttrs(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.WidgetTitleBar);
        int padding;
        if (isInEditMode()) {
            padding = 32;
        } else {
            padding = (int) typedArray.getDimension(R.styleable.WidgetTitleBar_wtbBackPadding, DensityUtils.dip2px(12));
        }
        setWtbTitle(typedArray.getString(R.styleable.WidgetTitleBar_wtbTitle));
        setWtbSub(typedArray.getString(R.styleable.WidgetTitleBar_wtbSub));
        if (typedArray.getBoolean(R.styleable.WidgetTitleBar_wtbShowBack, true)) {
            ivBack.setPadding(padding, padding, padding, padding);
            ivBack.setVisibility(VISIBLE);
        } else {
            ivBack.setVisibility(GONE);
        }
        typedArray.recycle();
    }

    private void setWtbSub(String sub) {
        if (TextUtils.isEmpty(sub)) {
            tvSub.setVisibility(GONE);
        } else {
            tvSub.setVisibility(VISIBLE);
            tvSub.setText(sub);
        }
    }

    public void setWtbTitle(String title) {
        tvTitle.setText(title);
    }

    private void bindAction(Context context) {
        if (null != onBack) {
            ivBack.setOnClickListener(v -> onBack.call());
        } else {
            ivBack.setOnClickListener(v -> ((Activity) context).finish());
        }
    }

    @Deprecated
    public void setOnBack(ViewListener.VoidCallback onBack) {
        this.onBack = onBack;
    }

    public void setOnAction(ViewListener.VoidCallback onAction) {
        if (null != onAction)
            tvSub.setOnClickListener(v -> onAction.call());
    }
}
