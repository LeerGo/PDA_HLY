package com.arpa.and.wms.arch.binding.viewadapter;

import com.google.android.material.tabs.TabLayout;

import android.graphics.drawable.Drawable;
import android.widget.LinearLayout;

import androidx.databinding.BindingAdapter;

/**
 * author: 李一方(<a href="mailto:leergo@dingtalk.com">leergo@dingtalk.com</a>)<br/>
 * version: 1.0.0<br/>
 * since: 2021-04-26 9:25 AM
 *
 * <p>
 * 内容描述区域
 * </p>
 */
public class TabLayoutViewBindingAdapter {

    @BindingAdapter("tabDivider")
    public static void setTabDivider(TabLayout tabLayout, Drawable tabDivider) {
        if (null != tabDivider) {
            LinearLayout linearLayout = (LinearLayout) tabLayout.getChildAt(0);
            linearLayout.setShowDividers(LinearLayout.SHOW_DIVIDER_MIDDLE);
            linearLayout.setDividerDrawable(tabDivider);
            linearLayout.setDividerPadding(30);
        }
    }
}
