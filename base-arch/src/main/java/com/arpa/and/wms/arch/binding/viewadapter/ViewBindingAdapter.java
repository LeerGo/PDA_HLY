package com.arpa.and.wms.arch.binding.viewadapter;

import android.os.SystemClock;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.databinding.BindingAdapter;

public class ViewBindingAdapter {
    private static final int INTERVAL = 700;

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

}
