package com.arpa.wms.hly.logic.task.vm;

import android.app.Application;

import com.arpa.and.arch.base.BaseModel;
import com.arpa.wms.hly.BR;
import com.arpa.wms.hly.R;
import com.arpa.wms.hly.base.viewmodel.VMBaseList;
import com.arpa.wms.hly.bean.GoodsItemVO;
import com.arpa.wms.hly.bean.base.ReqBase;
import com.arpa.wms.hly.bean.base.Result;
import com.arpa.wms.hly.bean.req.ReqGoodTakeDetail;
import com.arpa.wms.hly.bean.res.ResTaskAssign;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;
import dagger.hilt.android.lifecycle.HiltViewModel;
import me.tatarka.bindingcollectionadapter2.ItemBinding;
import retrofit2.Call;

@HiltViewModel
public class VMTaskGoodsTake extends VMBaseList<GoodsItemVO> {
    public ObservableField<ResTaskAssign> headerData = new ObservableField<>();
    public ReqGoodTakeDetail request = new ReqGoodTakeDetail();

    @Inject
    public VMTaskGoodsTake(@NonNull Application application, BaseModel model) {
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
        return ItemBinding.of(BR.data, R.layout.item_goods_take_detail_wait);
    }
}