package com.arpa.wms.hly.ui.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.TextView;

import com.arpa.wms.hly.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.LinearLayoutCompat;

/**
 * author: 李一方(<a href="mailto:leergo@dingtalk.com">leergo@dingtalk.com</a>)<br/>
 * version: 1.0.0<br/>
 * since: 2021-04-22 2:07 PM
 *
 * <p>
 * 内容描述区域
 * </p>
 */
public class WidgetPropsItem extends LinearLayoutCompat {
    private TextView tvName;
    private TextView tvDesc;

    public WidgetPropsItem(@NonNull Context context) {
        super(context);
    }

    public WidgetPropsItem(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initViews(context);
        initAttrs(context, attrs);
    }

    private void initViews(Context context) {
        LayoutInflater.from(context).inflate(R.layout.widget_props_item, this, true);
        tvName = findViewById(R.id.tv_name);
        tvDesc = findViewById(R.id.tv_desc);
    }

    private void initAttrs(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.WidgetPropsItem);
        tvName.setText(typedArray.getText(R.styleable.WidgetPropsItem_wpi_name));
        setDesc(typedArray.getString(R.styleable.WidgetPropsItem_wpi_desc));
        typedArray.recycle();
    }

    public void setDesc(String desc) {
        tvDesc.setText(desc);
    }
}
