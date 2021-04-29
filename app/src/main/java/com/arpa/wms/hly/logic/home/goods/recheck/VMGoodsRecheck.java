package com.arpa.wms.hly.logic.home.goods.recheck;

import android.app.Application;

import com.arpa.and.wms.arch.base.BaseModel;
import com.arpa.wms.hly.BR;
import com.arpa.wms.hly.R;
import com.arpa.wms.hly.bean.res.ResPdaTask;
import com.arpa.wms.hly.logic.common.VMPdaTask;
import com.arpa.wms.hly.utils.Const;

import androidx.annotation.NonNull;
import androidx.hilt.lifecycle.ViewModelInject;
import me.tatarka.bindingcollectionadapter2.ItemBinding;

/**
 * author: 李一方(<a href="mailto:leergo@dingtalk.com">leergo@dingtalk.com</a>)<br/>
 * version: 1.0.0<br/>
 * since: 2021-04-25 2:03 PM
 *
 * <p>
 * ViewModel: 商品待复核列表
 * </p>
 */
public class VMGoodsRecheck extends VMPdaTask {
    @ViewModelInject
    public VMGoodsRecheck(@NonNull Application application, BaseModel model) {
        super(application, model);
    }

    @Override
    protected String getTaskType() {
        return Const.TASK_TYPE.CHECK;
    }


    @Override
    public ItemBinding<ResPdaTask> getItemBinding() {
        return ItemBinding.of(BR.data, R.layout.item_goods_recheck);
    }
}
