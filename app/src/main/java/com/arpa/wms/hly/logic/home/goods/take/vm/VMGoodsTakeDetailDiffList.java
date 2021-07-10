package com.arpa.wms.hly.logic.home.goods.take.vm;

import android.app.Application;
import android.os.Bundle;

import com.arpa.and.arch.base.BaseModel;
import com.arpa.wms.hly.BR;
import com.arpa.wms.hly.R;
import com.arpa.wms.hly.base.viewmodel.VMBaseDiffList;
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
import androidx.recyclerview.widget.AsyncDifferConfig;
import androidx.recyclerview.widget.DiffUtil;
import dagger.hilt.android.lifecycle.HiltViewModel;
import me.tatarka.bindingcollectionadapter2.ItemBinding;
import retrofit2.Call;

/**
 * author: 李一方(<a href="mailto:leergo@dingtalk.com">leergo@dingtalk.com</a>)<br/>
 * version: 1.0.0<br/>
 * since: 2021-05-06 15:08
 *
 * <p>
 * ViewModel: 收货详情列表
 * </p>
 */
@HiltViewModel
public class VMGoodsTakeDetailDiffList extends VMBaseDiffList<GoodsItemVO> {
    public String supplierName;
    public ReqGoodTakeDetail request = new ReqGoodTakeDetail();

    @Inject
    public VMGoodsTakeDetailDiffList(@NonNull Application application, BaseModel model) {
        super(application, model);
    }

    @Override
    public void onResume() {
        super.onResume();
        refresh();
    }

    @Override
    protected boolean setAutoRefresh() {
        return false;
    }

    @Override
    protected AsyncDifferConfig<GoodsItemVO> getDiffConfig() {
        return new AsyncDifferConfig.Builder<>(new DiffUtil.ItemCallback<GoodsItemVO>() {
            @Override
            public boolean areItemsTheSame(@NonNull GoodsItemVO oldItem, @NonNull GoodsItemVO newItem) {
                return oldItem.getGoodCode().equals(newItem.getGoodCode());
            }

            @Override
            public boolean areContentsTheSame(@NonNull GoodsItemVO oldItem, @NonNull GoodsItemVO newItem) {
                return oldItem.getReceivedQuantity().equals(newItem.getReceivedQuantity());
            }
        }).build();
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
                bundle.putString(IntentKey.CODE, data.getCode());
                bundle.putString(IntentKey.RECEIVE_CODE, data.getReceiveCode());
                bundle.putString(IntentKey.SUPPLIER, supplierName);
                startActivity(GoodsTakeConfirmActivity.class, bundle);
            });
        } else {
            itemBinding = ItemBinding.of(BR.data, R.layout.item_goods_take_detail_yet);
        }

        return itemBinding;
    }
}
