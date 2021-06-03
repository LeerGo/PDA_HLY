package com.arpa.wms.hly.ui.decoration;

import android.graphics.Rect;
import android.view.View;

import com.arpa.wms.hly.utils.DensityUtils;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * author: 李一方(<a href="mailto:leergo@dingtalk.com">leergo@dingtalk.com</a>)<br/>
 * version: 1.0.0<br/>
 * since: 2021-04-21 4:35 PM
 */
public class BothItemDecoration extends RecyclerView.ItemDecoration {
    public static final int TOP = 0;
    public static final int BOTTOM = 1;

    private final int interval;
    private final boolean isBoth;
    private final int position;

    public BothItemDecoration() {
        this(10, TOP, false);
    }

    public BothItemDecoration(int interval, int position, boolean isBoth) {
        this.interval = DensityUtils.dip2px(interval);
        this.isBoth = isBoth;
        this.position = position;
    }

    public BothItemDecoration(int position, boolean isBoth) {
        this(10, position, isBoth);
    }

    public BothItemDecoration(boolean isBoth) {
        this(10, TOP, isBoth);
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        if (position == TOP) {
            outRect.top = interval;
            if (parent.getChildAdapterPosition(view) == state.getItemCount() - 1 && isBoth) {
                outRect.bottom = interval;
            }
        } else {
            outRect.bottom = interval;
            if (parent.getChildAdapterPosition(view) == 0 && isBoth) {
                outRect.top = interval;
            }
        }
    }
}
