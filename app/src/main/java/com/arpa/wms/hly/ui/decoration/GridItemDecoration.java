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
public class GridItemDecoration extends RecyclerView.ItemDecoration {
    private final int interval;

    public GridItemDecoration(int interval) {
        this.interval = DensityUtils.dip2px(interval);
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        outRect.top = interval;
        if (parent.getChildAdapterPosition(view) % 2 == 0) {
            outRect.left = interval;
            outRect.right = interval / 2;
        } else if (parent.getChildAdapterPosition(view) % 2 == 1) {
            outRect.left = interval / 2;
            outRect.right = interval;
        }
        if (parent.getChildAdapterPosition(view) == state.getItemCount() - 1 || parent.getChildAdapterPosition(view) == state.getItemCount() - 2) {
            outRect.bottom = interval;
        }
    }
}
