package com.arpa.wms.hly.logic.home.goods.pick;

import com.arpa.wms.hly.BR;
import com.arpa.wms.hly.base.WrapBindingRVAdapter;

import androidx.annotation.NonNull;
import androidx.databinding.ViewDataBinding;

/**
 * author: 李一方(<a href="mailto:leergo@dingtalk.com">leergo@dingtalk.com</a>)<br/>
 * version: 1.0.0<br/>
 * since: 2021-05-14 16:07
 *
 * <p>
 * ViewModel: 拣货
 * </p>
 */
public class GoodsPickTaskAdapter <T> extends WrapBindingRVAdapter<T> {
    private int positionSel = -1;

    @Override
    public void onBindBinding(@NonNull ViewDataBinding binding, int variableId, int layoutRes, int position, T item) {
        super.onBindBinding(binding, variableId, layoutRes, position, item);
        binding.setVariable(BR.positionSel, positionSel);
    }

    public int getPositionSel() {
        return positionSel;
    }

    public void setPositionSel(int positionSel) {
        this.positionSel = positionSel;
    }

    /**
     * 重置选中条目索引
     */
    public void resetPositionSel() {
        this.positionSel = -1;
    }

    public T getItemSel() {
        if (positionSel != -1)
            return getAdapterItem(positionSel);
        return null;
    }
}
