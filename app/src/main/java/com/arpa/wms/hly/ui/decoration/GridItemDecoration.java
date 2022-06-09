package com.arpa.wms.hly.ui.decoration;

import android.graphics.Rect;
import android.view.View;
import com.arpa.wms.hly.utils.DensityUtils;

import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
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
        GridLayoutManager manager = (GridLayoutManager) parent.getLayoutManager();
        int spanCount = Objects.requireNonNull(manager).getSpanCount();
        int position = parent.getChildAdapterPosition(view);

        if (position < spanCount) {
            outRect.top = interval;
        }
        outRect.bottom = interval;
        if (position % spanCount == 0) {
            outRect.left = interval;
            outRect.right = interval / 2;
        } else if (position % spanCount == 1) {
            outRect.left = interval / 2;
            outRect.right = interval;
        }
    }
}
