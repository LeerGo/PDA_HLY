package com.arpa.wms.hly.logic.home.goods.take;

import android.app.Application;

import com.arpa.and.wms.arch.base.BaseModel;
import com.arpa.wms.hly.BR;
import com.arpa.wms.hly.R;
import com.arpa.wms.hly.bean.res.ResPdaTask;
import com.arpa.wms.hly.logic.common.VMPdaTask;
import com.arpa.wms.hly.utils.Const.TASK_TYPE;

import androidx.annotation.NonNull;
import androidx.hilt.lifecycle.ViewModelInject;
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
public class VMGoodsTake extends VMPdaTask {

    @ViewModelInject
    public VMGoodsTake(@NonNull Application application, BaseModel model) {
        super(application, model);
    }

    @Override
    protected String getTaskType() {
        return TASK_TYPE.RECEIVE;
    }

    @Override
    public ItemBinding<ResPdaTask> getItemBinding() {
        return ItemBinding.of(BR.data, R.layout.item_goods_take);
    }
}
