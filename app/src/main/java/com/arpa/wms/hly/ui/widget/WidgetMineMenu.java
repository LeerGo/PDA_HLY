package com.arpa.wms.hly.ui.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.TextView;

import com.arpa.wms.hly.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.LinearLayoutCompat;

/**
 * author: 李一方(<a href="mailto:leergo@dingtalk.com">leergo@dingtalk.com</a>)<br/>
 * version: 1.0.0<br/>
 * since: 2021-04-22 9:06 AM
 *
 * <p>
 * 内容描述区域
 * </p>
 */
public class WidgetMineMenu extends LinearLayoutCompat {
    private ImageView ivIcon;
    private TextView tvTitle;
    private TextView tvContent;

    public WidgetMineMenu(@NonNull Context context) {
        super(context);
    }

    public WidgetMineMenu(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
        initAttrs(context, attrs);
    }

    private void init(Context context) {
        LayoutInflater.from(context).inflate(R.layout.widget_mine_menu, this, true);
        ivIcon = findViewById(R.id.iv_icon);
        tvTitle = findViewById(R.id.tv_title);
        tvContent = findViewById(R.id.tv_content);
    }

    private void initAttrs(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.WidgetMineMenu);
        setTitle(typedArray.getString(R.styleable.WidgetMineMenu_title));
        setDesc(typedArray.getString(R.styleable.WidgetMineMenu_desc));
        setIcon(typedArray.getDrawable(R.styleable.WidgetMineMenu_icon));
        typedArray.recycle();
    }

    public void setIcon(Drawable drawable) {
        ivIcon.setImageDrawable(drawable);
    }

    public void setDesc(String string) {
        tvContent.setText(string);
    }

    public void setTitle(String string) {
        tvTitle.setText(string);
    }
}
