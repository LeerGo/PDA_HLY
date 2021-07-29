package com.arpa.wms.hly.base;

import com.arpa.wms.hly.BR;

import androidx.annotation.NonNull;
import androidx.databinding.ViewDataBinding;
import me.tatarka.bindingcollectionadapter2.BindingRecyclerViewAdapter;

/**
 * author: 李一方(<a href="mailto:leergo@dingtalk.com">leergo@dingtalk.com</a>)<br/>
 * version: 1.0.0<br/>
 * since: 2021-05-14 08:23
 *
 * <p>
 * base: 针对 RecyclerView 二次包装的适配器，把当前数据的 position 注册进去（图省事）
 * </p>
 */
public class WrapBindingRVAdapter <T> extends BindingRecyclerViewAdapter<T> {
    @Override
    public void onBindBinding(@NonNull ViewDataBinding binding, int variableId, int layoutRes, int position, T item) {
        super.onBindBinding(binding, variableId, layoutRes, position, item);
        binding.setVariable(BR.position, position);
    }
}