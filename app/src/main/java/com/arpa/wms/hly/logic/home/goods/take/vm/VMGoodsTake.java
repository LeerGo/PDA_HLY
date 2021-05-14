package com.arpa.wms.hly.logic.home.goods.take.vm;

import android.app.Application;

import com.arpa.and.wms.arch.base.BaseModel;
import com.arpa.wms.hly.BR;
import com.arpa.wms.hly.R;
import com.arpa.wms.hly.bean.res.ResPdaTask;
import com.arpa.wms.hly.logic.common.vm.VMPdaTask;
import com.arpa.wms.hly.utils.Const.TASK_TYPE;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import dagger.hilt.android.lifecycle.HiltViewModel;
import me.tatarka.bindingcollectionadapter2.ItemBinding;

/**
 * author: 李一方(<a href="mailto:leergo@dingtalk.com">leergo@dingtalk.com</a>)<br/>
 * version: 1.0.0<br/>
 * since: 2021-04-25 2:03 PM
 *
 * <p>
 * ViewModel: 商品待收货类别
 * </p>
 */
@HiltViewModel
public class VMGoodsTake extends VMPdaTask {
    private final ItemBinding<ResPdaTask> itemBinding = ItemBinding.of(BR.data, R.layout.item_goods_take);

    @Inject
    public VMGoodsTake(@NonNull Application application, BaseModel model) {
        super(application, model);
    }

    @Override
    protected String getTaskType() {
        return TASK_TYPE.RECEIVE;
    }

    @Override
    public ItemBinding<ResPdaTask> getItemBinding() {
        itemBinding.bindExtra(BR.showOrder, true);
        return itemBinding;
    }
}
