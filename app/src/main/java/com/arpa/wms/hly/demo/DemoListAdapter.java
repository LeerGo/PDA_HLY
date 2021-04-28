package com.arpa.wms.hly.demo;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.arpa.wms.hly.bean.ResWarehouse;
import com.arpa.wms.hly.ui.listener.ViewListener.OnItemClickListener;

import androidx.annotation.NonNull;
import androidx.databinding.ViewDataBinding;
import me.tatarka.bindingcollectionadapter2.BindingRecyclerViewAdapter;

/**
 * author: 李一方(<a href="mailto:leergo@dingtalk.com">leergo@dingtalk.com</a>)<br/>
 * version: 1.0.0<br/>
 * since: 2021-04-26 10:06 AM
 *
 * <p>
 * 内容描述区域
 * </p>
 */
public class DemoListAdapter extends BindingRecyclerViewAdapter<ResWarehouse> {
    private int curSelect = -1;
    private OnItemClickListener<ResWarehouse> onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener<ResWarehouse> onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public ViewDataBinding onCreateBinding(@NonNull LayoutInflater inflater, int layoutId, @NonNull ViewGroup viewGroup) {
        return super.onCreateBinding(inflater, layoutId, viewGroup);
    }

    @Override
    public void onBindBinding(@NonNull ViewDataBinding binding, int variableId, int layoutRes, int position, ResWarehouse item) {
        super.onBindBinding(binding, variableId, layoutRes, position, item);
        binding.getRoot().setSelected(curSelect == position);
        binding.getRoot().setOnClickListener(v -> {
            curSelect = position;
            if (null != onItemClickListener)
                onItemClickListener.onItemClick(binding.getRoot(), position, item);
            notifyDataSetChanged();
        });
    }
}
