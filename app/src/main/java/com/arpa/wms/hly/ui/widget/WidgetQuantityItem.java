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
 * 控件：计数控件
 * </p>
 */
public class WidgetQuantityItem extends LinearLayoutCompat {
    private TextView tvSum;
    private TextView tvName;
    private TextView tvSplit;
    private TextView tvCount;

    public WidgetQuantityItem(@NonNull Context context) {
        super(context);
    }

    public WidgetQuantityItem(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initViews(context);
        initAttrs(context, attrs);
    }

    private void initViews(Context context) {
        LayoutInflater.from(context).inflate(R.layout.widget_quantity_item, this, true);
        tvSum = findViewById(R.id.tv_sum);
        tvName = findViewById(R.id.tv_name);
        tvSplit = findViewById(R.id.tv_split);
        tvCount = findViewById(R.id.tv_count);
    }

    private void initAttrs(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.WidgetQuantityItem);
        setWqiSum(typedArray.getInt(R.styleable.WidgetQuantityItem_wqiSum, 0));
        setWqiName(typedArray.getString(R.styleable.WidgetQuantityItem_wqiName));
        setWqiCount(typedArray.getInt(R.styleable.WidgetQuantityItem_wqiCount, 0));
        setWqiShowCount(typedArray.getBoolean(R.styleable.WidgetQuantityItem_wqiShowCount, true));
        typedArray.recycle();
    }

    private void setWqiName(String text) {
        tvName.setText(text);
    }

    private void setWqiSum(int sum) {
        tvSum.setText(String.valueOf(sum));
    }

    private void setWqiCount(int count) {
        tvCount.setText(String.valueOf(count));
    }

    private void setWqiShowCount(boolean showCount) {
        tvSplit.setVisibility(showCount ? VISIBLE : GONE);
        tvCount.setVisibility(showCount ? VISIBLE : GONE);
    }
}
