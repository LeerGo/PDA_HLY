package com.arpa.wms.hly.logic.home.truckload.adapter;

import com.arpa.wms.hly.BR;
import com.arpa.wms.hly.bean.res.ResTruckLoad;

import androidx.annotation.NonNull;
import androidx.databinding.ViewDataBinding;
import me.tatarka.bindingcollectionadapter2.BindingRecyclerViewAdapter;

/**
 * author: 李一方(<a href="mailto:leergo@dingtalk.com">leergo@dingtalk.com</a>)<br/>
 * version: 1.0.0<br/>
 * since: 2021-05-06 14:27
 *
 * <p>
 * Adapter: PDA Task
 * </p>
 */
public class TruckLoadAdapter extends BindingRecyclerViewAdapter<ResTruckLoad> {
    @Override
    public void onBindBinding(@NonNull ViewDataBinding binding, int variableId, int layoutRes, int position, ResTruckLoad item) {
        super.onBindBinding(binding, variableId, layoutRes, position, item);
        binding.setVariable(BR.pos, position + 1);
    }
}
