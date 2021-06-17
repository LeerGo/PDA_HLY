package com.arpa.wms.hly.logic.task.vm;

import android.app.Application;

import com.arpa.and.arch.base.BaseModel;
import com.arpa.wms.hly.BR;
import com.arpa.wms.hly.R;
import com.arpa.wms.hly.base.viewmodel.VMBaseList;
import com.arpa.wms.hly.bean.GoodsItemVO;
import com.arpa.wms.hly.bean.base.ReqBase;
import com.arpa.wms.hly.bean.base.Result;
import com.arpa.wms.hly.bean.req.ReqPickDetail;
import com.arpa.wms.hly.bean.res.ResTaskAssign;
import com.arpa.wms.hly.utils.Const;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;
import dagger.hilt.android.lifecycle.HiltViewModel;
import me.tatarka.bindingcollectionadapter2.ItemBinding;
import retrofit2.Call;

@HiltViewModel
public class VMTaskGoodsPick extends VMBaseList<GoodsItemVO> {
    public final ObservableField<ResTaskAssign> headerData = new ObservableField<>();
    public final ReqPickDetail reqPickDetail = new ReqPickDetail();

    @Inject
    public VMTaskGoodsPick(@NonNull Application application, BaseModel model) {
        super(application, model);
    }

    @Override
    public Call<Result<List<GoodsItemVO>>> getCall(Map<String, Object> params) {
        return apiService.pickingDetail((String) params.get(Const.IntentKey.SOURCE_CODE));
    }

    @Override
    public ReqBase getParams() {
        return reqPickDetail;
    }

    @Override
    public ItemBinding<GoodsItemVO> getItemBinding() {
        return ItemBinding.of(BR.data, R.layout.item_task_goods_pick);
    }
}