package com.arpa.and.wms.arch.binding.viewadapter;

import android.os.SystemClock;
import android.view.View;

import com.arpa.and.wms.arch.binding.command.BindingCommand;

import androidx.annotation.NonNull;
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
public class CommonViewBindingAdapter {
    private static final int INTERVAL = 700;

    @BindingAdapter(value = {"select"})
    public static void bindSelect(@NonNull View v, Boolean select) {
        v.setSelected(select);
    }

    @BindingAdapter(value = {"visible"})
    public static void bindVisible(View v, Boolean visible) {
        if (null == visible || !visible) {
            v.setVisibility(View.GONE);
        } else {
            v.setVisibility(View.VISIBLE);
        }
    }

    @BindingAdapter({"android:onClick", "android:clickable"})
    public static void setOnClick(View view, View.OnClickListener clickListener, boolean clickable) {
        setOnClick(view, clickListener);
        view.setClickable(clickable);
    }

    @BindingAdapter({"android:onClick"})
    public static void setOnClick(@NonNull View view, final View.OnClickListener clickListener) {
        final long[] mHits = new long[2];
        view.setOnClickListener(v -> {
            System.arraycopy(mHits, 1, mHits, 0, mHits.length - 1);
            mHits[mHits.length - 1] = SystemClock.uptimeMillis();
            if (mHits[0] < (SystemClock.uptimeMillis() - INTERVAL)) {
                clickListener.onClick(v);
            }
        });
    }

    /**
     * view的焦点发生变化的事件绑定
     */
    @BindingAdapter({"onFocusChangeCommand"})
    public static void onFocusChangeCommand(View view, final BindingCommand<Boolean> onFocusChangeCommand) {
        view.setOnFocusChangeListener((v, hasFocus) -> {
            if (onFocusChangeCommand != null) {
                onFocusChangeCommand.execute(hasFocus);
            }
        });
    }
}
