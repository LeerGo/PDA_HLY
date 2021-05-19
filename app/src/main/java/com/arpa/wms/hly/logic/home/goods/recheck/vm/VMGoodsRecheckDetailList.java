package com.arpa.wms.hly.logic.home.goods.recheck.vm;

import android.app.Application;

import com.arpa.and.wms.arch.base.BaseModel;
import com.arpa.wms.hly.BR;
import com.arpa.wms.hly.R;
import com.arpa.wms.hly.base.viewmodel.VMBaseList;
import com.arpa.wms.hly.bean.OutboundItemVOList;
import com.arpa.wms.hly.bean.base.ReqBase;
import com.arpa.wms.hly.bean.base.Result;
import com.arpa.wms.hly.bean.req.ReqGoodRecheckDetail;
import com.arpa.wms.hly.utils.Const.TASK_STATUS;

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
 * since: 2021-04-25 2:03 PM
 *
 * <p>
 * ViewModel: 商品待复核列表
 * </p>
 */
@HiltViewModel
public class VMGoodsRecheckDetailList extends VMBaseList<OutboundItemVOList> {
    public ReqGoodRecheckDetail request = new ReqGoodRecheckDetail();

    @Inject
    public VMGoodsRecheckDetailList(@NonNull Application application, BaseModel model) {
        super(application, model);
    }

    @Override
    public Call<Result<List<OutboundItemVOList>>> getCall(Map<String, Object> params) {
        return apiService.recheckItemListBelow(params);
    }

    @Override
    public ReqBase getParams() {
        return request;
    }

    @Override
    public ItemBinding<OutboundItemVOList> getItemBinding() {
        ItemBinding<OutboundItemVOList> itemBinding;
        if (request.getOutboundStatus() == TASK_STATUS.RECHECK_WAIT) {
            itemBinding = ItemBinding.of(BR.data, R.layout.item_goods_recheck_detail_wait);
        } else {
            itemBinding = ItemBinding.of(BR.data, R.layout.item_goods_recheck_detail_yet);
        }

        return itemBinding;
    }
}
