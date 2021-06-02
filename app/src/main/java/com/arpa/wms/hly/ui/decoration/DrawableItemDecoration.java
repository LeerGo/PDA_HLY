package com.arpa.wms.hly.ui.decoration;

import android.content.Context;
import android.graphics.drawable.Drawable;

import com.arpa.wms.hly.R;

import java.util.Objects;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DividerItemDecoration;

/**
 * author: 李一方(<a href="mailto:leergo@dingtalk.com">leergo@dingtalk.com</a>)<br/>
 * version: 1.0.0<br/>
 * since: 2021-04-21 4:35 PM
 */
public class DrawableItemDecoration {
    public static DividerItemDecoration getDivider(Context context) {
        DividerItemDecoration mDividerItemDecoration = new DividerItemDecoration(context, DividerItemDecoration.VERTICAL);
        Drawable mDrawable = ContextCompat.getDrawable(context, R.drawable.divider_line_horizontal);
        mDividerItemDecoration.setDrawable(Objects.requireNonNull(mDrawable));
        return  mDividerItemDecoration;
    }
}
