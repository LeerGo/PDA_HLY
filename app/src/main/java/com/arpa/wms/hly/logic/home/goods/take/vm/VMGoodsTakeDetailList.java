package com.arpa.wms.hly.logic.home.goods.take.vm;

import android.app.Application;

import com.arpa.and.wms.arch.base.BaseModel;
import com.arpa.wms.hly.BR;
import com.arpa.wms.hly.R;
import com.arpa.wms.hly.base.viewmodel.VMBaseList;
import com.arpa.wms.hly.bean.OutboundItemVOList;
import com.arpa.wms.hly.bean.base.ReqBase;
import com.arpa.wms.hly.bean.base.Result;
import com.arpa.wms.hly.bean.req.ReqGoodTakeDetail;
import com.arpa.wms.hly.utils.Const;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import dagger.hilt.android.lifecycle.HiltViewModel;
import me.tatarka.bindingcollectionadapter2.ItemBinding;
import retrofit2.Call;

/**
 * author: 李一方(<a href="mailto:leergo@dingtalk.com">leergo@dingtalk.com</a>)<br/>
 * version: 1.0.0<br/>
 * since: 2021-05-06 15:08
 *
 * <p>
 * 内容描述区域
 * </p>
 */
@HiltViewModel
public class VMGoodsTakeDetailList extends VMBaseList<OutboundItemVOList> {
    public ReqGoodTakeDetail request = new ReqGoodTakeDetail();

    @Inject
    public VMGoodsTakeDetailList(@NonNull Application application, BaseModel model) {
        super(application, model);
    }

    @Override
    public Call<Result<List<OutboundItemVOList>>> getCall(Map<String, Object> params) {
        return apiService.receiveDetailsBelow(params);
    }

    @Override
    public ReqBase getParams() {
        return request;
    }

    @Override
    public ItemBinding<OutboundItemVOList> getItemBinding() {
        ItemBinding<OutboundItemVOList> itemBinding;
        if (request.getReceiveStatus() == Const.TASK_STATUS.TAKE_WAIT) {
            itemBinding = ItemBinding.of(BR.data, R.layout.item_goods_take_detail_wait);
        } else {
            itemBinding = ItemBinding.of(BR.data, R.layout.item_goods_take_detail_yet);
        }

        return itemBinding;
    }
}
