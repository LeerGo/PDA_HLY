package com.arpa.wms.hly.base;

import android.util.SparseArray;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

/**
 * ViewHolder基类
 */
@Deprecated
public class BaseViewHolder extends RecyclerView.ViewHolder {

    private SparseArray<View> views;

    public BaseViewHolder(View itemView) {
        super(itemView);
        this.views = new SparseArray<>();
    }

    @SuppressWarnings("unchecked")
    public <T extends View> T getView(int viewId) {
        View view = views.get(viewId);
        if (view == null) {
            view = itemView.findViewById(viewId);
            views.put(viewId, view);
        }
        return (T) view;
    }
}
