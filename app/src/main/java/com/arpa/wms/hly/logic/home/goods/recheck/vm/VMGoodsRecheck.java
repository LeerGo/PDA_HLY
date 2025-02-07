package com.arpa.wms.hly.logic.home.goods.recheck.vm;

import android.app.Application;
import android.os.Bundle;

import androidx.annotation.NonNull;

import com.arpa.and.arch.base.BaseModel;
import com.arpa.wms.hly.BR;
import com.arpa.wms.hly.R;
import com.arpa.wms.hly.base.WrapBindingRVAdapter;
import com.arpa.wms.hly.base.viewmodel.VMBaseRefreshList;
import com.arpa.wms.hly.bean.base.ReqPage;
import com.arpa.wms.hly.bean.base.ResultPage;
import com.arpa.wms.hly.bean.req.ReqTaskList;
import com.arpa.wms.hly.bean.res.ResTaskAssign;
import com.arpa.wms.hly.logic.home.goods.recheck.GoodsRecheckDetailActivity;
import com.arpa.wms.hly.ui.listener.ViewListener;
import com.arpa.wms.hly.utils.Const.IntentKey;

import java.util.Map;

import javax.inject.Inject;

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
public class VMGoodsRecheck extends VMBaseRefreshList<ResTaskAssign> {
    private final ItemBinding<ResTaskAssign> itemBinding = ItemBinding.of(BR.data, R.layout.item_goods_recheck);
    private final ReqTaskList reqTaskList = new ReqTaskList(PAGE_SIZE);

    @Inject
    public VMGoodsRecheck(@NonNull Application application, BaseModel model) {
        super(application, model);
    }

    @Override
    public void onResume() {
        super.onResume();
        refresh();
    }

    @Override
    public Call<ResultPage<ResTaskAssign>> getCall(Map<String, Object> params) {
        return apiService.goodsRecheckList(params);
    }

    /**
     * 检索
     */
    public void search(String keyWord) {
        reqTaskList.setQueryValue(keyWord);
        refresh();
    }

    @Override
    public ReqPage getParams() {
        return reqTaskList;
    }

    @Override
    public void configAdapter() {
        setAdapter(new WrapBindingRVAdapter<>());
    }

    @Override
    public ItemBinding<ResTaskAssign> getItemBinding() {
        itemBinding.bindExtra(BR.showOrder, true);
        itemBinding.bindExtra(BR.listener, (ViewListener.DataTransCallback<ResTaskAssign>) data -> {
            Bundle bundle = new Bundle();
            data.toRecheckDetail();
            bundle.putParcelable(IntentKey.DATA, data);
            startActivity(GoodsRecheckDetailActivity.class, bundle);
        });
        return itemBinding;
    }
}
