package com.arpa.wms.hly.logic.common;

import com.arpa.wms.hly.BR;
import com.arpa.wms.hly.bean.res.ResPdaTask;

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
public class
PdaTaskAdapter extends BindingRecyclerViewAdapter<ResPdaTask> {
    @Override
    public void onBindBinding(@NonNull ViewDataBinding binding, int variableId, int layoutRes, int position, ResPdaTask item) {
        super.onBindBinding(binding, variableId, layoutRes, position, item);
        binding.setVariable(BR.pos, position + 1);
        binding.setVariable(BR.showOrder, true);
    }
}
