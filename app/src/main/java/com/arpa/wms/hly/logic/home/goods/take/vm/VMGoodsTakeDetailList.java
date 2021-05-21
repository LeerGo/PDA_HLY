package com.arpa.wms.hly.logic.home.goods.take.vm;

import android.app.Application;
import android.os.Bundle;

import com.arpa.and.wms.arch.base.BaseModel;
import com.arpa.wms.hly.BR;
import com.arpa.wms.hly.R;
import com.arpa.wms.hly.base.viewmodel.VMBaseList;
import com.arpa.wms.hly.bean.GoodsItemVO;
import com.arpa.wms.hly.bean.base.ReqBase;
import com.arpa.wms.hly.bean.base.Result;
import com.arpa.wms.hly.bean.req.ReqGoodTakeDetail;
import com.arpa.wms.hly.logic.home.goods.take.GoodsTakeConfirmActivity;
import com.arpa.wms.hly.ui.listener.ViewListener;
import com.arpa.wms.hly.utils.Const.IntentKey;
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
 * since: 2021-05-06 15:08
 *
 * <p>
 * 内容描述区域
 * </p>
 */
@HiltViewModel
public class VMGoodsTakeDetailList extends VMBaseList<GoodsItemVO> {
    public ReqGoodTakeDetail request = new ReqGoodTakeDetail();

    @Inject
    public VMGoodsTakeDetailList(@NonNull Application application, BaseModel model) {
        super(application, model);
    }

    @Override
    public Call<Result<List<GoodsItemVO>>> getCall(Map<String, Object> params) {
        return apiService.receiveDetailsBelow(params);
    }

    @Override
    public ReqBase getParams() {
        return request;
    }

    @Override
    public ItemBinding<GoodsItemVO> getItemBinding() {
        ItemBinding<GoodsItemVO> itemBinding;
        if (request.getReceiveStatus() == TASK_STATUS.TAKE_WAIT) {
            itemBinding = ItemBinding.of(BR.data, R.layout.item_goods_take_detail_wait);
            itemBinding.bindExtra(BR.listener, (ViewListener.DataTransCallback<GoodsItemVO>) data -> {
                Bundle bundle = new Bundle();
                bundle.putString(IntentKey.RECEIVE_CODE, data.getReceiveCode());
                bundle.putString(IntentKey.RECEIVE_ITEM_CODE, data.getCode());
                startActivity(GoodsTakeConfirmActivity.class, bundle);
            });
        } else {
            itemBinding = ItemBinding.of(BR.data, R.layout.item_goods_take_detail_yet);
        }

        return itemBinding;
    }
}
