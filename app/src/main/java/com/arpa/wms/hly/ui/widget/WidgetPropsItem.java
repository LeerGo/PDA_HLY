package com.arpa.wms.hly.ui.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.SpannableStringBuilder;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.LinearLayoutCompat;

import com.arpa.wms.hly.R;

/**
 * author: 李一方(<a href="mailto:leergo@dingtalk.com">leergo@dingtalk.com</a>)<br/>
 * version: 1.0.0<br/>
 * since: 2021-04-22 2:07 PM
 *
 * <p>
 * 控件：属性条目
 * </p>
 */
public class WidgetPropsItem extends LinearLayoutCompat {
    private TextView tvKey;
    private TextView tvValue;

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
        tvKey = findViewById(R.id.tv_key);
        tvValue = findViewById(R.id.tv_value);
    }

    private void initAttrs(Context context, AttributeSet attrs) {
        int defaultTextSize = context.getResources().getDimensionPixelSize(R.dimen.dp_14);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.WidgetPropsItem);
        setPropsKey(typedArray.getString(R.styleable.WidgetPropsItem_propsKey));
        setPropsValue(typedArray.getString(R.styleable.WidgetPropsItem_propsValue));
        setPropsValueGravity(typedArray.getInt(R.styleable.WidgetPropsItem_propsValueGravity, -1));
        setPropsKeyColor(typedArray.getColor(R.styleable.WidgetPropsItem_propsKeyColor, context.getResources().getColor(R.color.grey_96a0b9)));
        setPropsValueColor(typedArray.getColor(R.styleable.WidgetPropsItem_propsValueColor, context.getResources().getColor(R.color.color_434c67)));
        int propsKeySize = typedArray.getDimensionPixelSize(R.styleable.WidgetPropsItem_propsKeySize, defaultTextSize);
        int valueKeySize = typedArray.getDimensionPixelSize(R.styleable.WidgetPropsItem_propsValueSize, defaultTextSize);
        setPropsKeySize(propsKeySize);
        setPropsValueSize(valueKeySize);
        typedArray.recycle();
    }

    private void setPropsValueSize(int valueKeySize) {
        tvValue.setTextSize(TypedValue.COMPLEX_UNIT_PX, valueKeySize);
    }

    private void setPropsKeySize(int propsKeySize) {
        tvKey.setTextSize(TypedValue.COMPLEX_UNIT_PX, propsKeySize);
    }

    public void setPropsKey(String key) {
        tvKey.setText(key);
    }

    public void setPropsValue(String desc) {
        tvValue.setText(desc);
    }

    public void setPropsValueGravity(int gravity) {
        if (gravity == -1) tvValue.setGravity(Gravity.START);
        else tvValue.setGravity(gravity);
    }

    private void setPropsKeyColor(int color) {
        tvKey.setTextColor(color);
    }

    private void setPropsValueColor(int color) {
        tvValue.setTextColor(color);
    }

    public void setPropsValue(int value) {
        setPropsValue(String.valueOf(value));
    }

    public void setValueSpan(SpannableStringBuilder span){
        tvValue.setText(span);
    }
}
