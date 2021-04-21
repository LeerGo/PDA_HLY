package com.arpa.wms.hly.ui.decoration;

import android.graphics.Rect;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * author: 李一方(<a href="mailto:leergo@dingtalk.com">leergo@dingtalk.com</a>)<br/>
 * version: 1.0.0<br/>
 * since: 2021-04-21 4:35 PM
 *
 * <p>
 * 内容描述区域
 * </p>
 */
public class GridItemDecoration extends RecyclerView.ItemDecoration {
    private int interval;

    public GridItemDecoration(int interval) {
        this.interval = interval;
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        outRect.top = interval;
        if (parent.getChildAdapterPosition(view) % 2 == 0) {
            outRect.left = interval;
            outRect.right = interval;
        } else if (parent.getChildAdapterPosition(view) % 2 == 1) {
            outRect.left = 0;
            outRect.right = interval;
        }
    }
}
