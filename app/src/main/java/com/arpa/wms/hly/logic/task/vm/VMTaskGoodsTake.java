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
import com.arpa.wms.hly.net.callback.ResultCallback;
import com.arpa.wms.hly.net.exception.ResultError;
import com.arpa.wms.hly.utils.ToastUtils;

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
    public ReqGoodTakeDetail request = new ReqGoodTakeDetail();
    public final ObservableField<ResTaskAssign> headerData = new ObservableField<>();

    @Inject
    public VMTaskGoodsTake(@NonNull Application application, BaseModel model) {
        super(application, model);
    }

    @Override
    public void onResume() {
        super.onResume();
        refreshHeader();
    }

    /**
     * 刷新头部
     */
    private void refreshHeader() {
        apiService.receiveDetailsAbove(request.getReceiveCode())
                .enqueue(new ResultCallback<ResTaskAssign>() {
                    @Override
                    public void onSuccess(ResTaskAssign data) {
                        headerData.set(data);
                    }

                    @Override
                    public void onFailed(ResultError error) {
                        ToastUtils.showShort(error.getMessage());
                    }
                });
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