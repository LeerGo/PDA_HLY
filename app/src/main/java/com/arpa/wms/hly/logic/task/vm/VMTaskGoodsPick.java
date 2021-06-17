package com.arpa.wms.hly.logic.task.vm;

import android.app.Application;

import com.arpa.and.arch.base.BaseModel;
import com.arpa.wms.hly.base.viewmodel.VMBaseList;
import com.arpa.wms.hly.bean.GoodsItemVO;
import com.arpa.wms.hly.bean.base.ReqBase;
import com.arpa.wms.hly.bean.base.Result;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import dagger.hilt.android.lifecycle.HiltViewModel;
import me.tatarka.bindingcollectionadapter2.ItemBinding;
import retrofit2.Call;

@HiltViewModel
public class VMTaskGoodsPick extends VMBaseList<GoodsItemVO> {

    @Inject
    public VMTaskGoodsPick(@NonNull Application application, BaseModel model) {
        super(application, model);
    }

    @Override
    public Call<Result<List<GoodsItemVO>>> getCall(Map<String, Object> params) {
        return null;
    }

    @Override
    public ReqBase getParams() {
        return null;
    }

    @Override
    public ItemBinding<GoodsItemVO> getItemBinding() {
        return null;
    }
}