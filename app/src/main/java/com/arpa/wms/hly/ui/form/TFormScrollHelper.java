package com.arpa.wms.hly.ui.form;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @author ousiyuan
 */
public class TFormScrollHelper {
    public final static int RIGHT_AREA = 0;
    public final static int LEFT_AREA = 1;

    private final Map<RecyclerView, Integer> mViewIntegerMap = new HashMap<>();
    private final List<ObservableScrollView> mScrollView = new ArrayList<>();

    private final RecyclerView.OnScrollListener mScrollListener = new RecyclerView.OnScrollListener() {
        @Override
        public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
            // 以下场景处理的是，滑动过程中手指松开，移到另外一个recyclerview进行滚动
            if (newState == RecyclerView.SCROLL_STATE_DRAGGING) {
                // 该recyclerview进入滚动状态的时候，其余四个要先停止滚动
                for (RecyclerView view : mViewIntegerMap.keySet()) {
                    if (view != recyclerView) {
                        view.stopScroll();
                    }
                }
            }
        }

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            if (recyclerView.getScrollState() != RecyclerView.SCROLL_STATE_IDLE) {
                // 当前手指操作的区域
                int currentArea = mViewIntegerMap.get(recyclerView);
                for (RecyclerView view : mViewIntegerMap.keySet()) {
                    if (view != recyclerView) {
                        int areaType = mViewIntegerMap.get(view);
                        view.scrollBy(areaType == currentArea ? dx : -dx, dy);
                    }
                }
            }
        }
    };

    private final ObservableScrollView.ScrollViewListener scrollViewListener =
            (scrollView, x, y, oldx, oldy) -> {
                for (ObservableScrollView view : mScrollView) {
                    if (view != scrollView) {
                        view.scrollTo(x, y);
                    }
                }
            };

    public void connectRecyclerView(RecyclerView recyclerView, int areaType) {
        recyclerView.addOnScrollListener(mScrollListener);
        mViewIntegerMap.put(recyclerView, areaType);
    }

    public void connectScrollView(ObservableScrollView scrollView) {
        scrollView.setScrollViewListener(scrollViewListener);
        mScrollView.add(scrollView);
    }
}
