package com.arpa.wms.hly.logic.home.goods.take.vm;

import android.app.Application;
import android.os.Bundle;

import com.arpa.and.arch.base.BaseModel;
import com.arpa.wms.hly.BR;
import com.arpa.wms.hly.R;
import com.arpa.wms.hly.base.WrapBindingRVAdapter;
import com.arpa.wms.hly.base.viewmodel.VMBaseRefreshList;
import com.arpa.wms.hly.bean.base.ReqPage;
import com.arpa.wms.hly.bean.base.ResultPage;
import com.arpa.wms.hly.bean.req.ReqTaskList;
import com.arpa.wms.hly.bean.res.ResTaskAssign;
import com.arpa.wms.hly.logic.home.goods.take.GoodsTakeDetailActivity;
import com.arpa.wms.hly.ui.listener.ViewListener;
import com.arpa.wms.hly.utils.Const;

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
 * ViewModel: 商品待收货类别
 * </p>
 */
@HiltViewModel
public class VMGoodsTake extends VMBaseRefreshList<ResTaskAssign> {
    private final ItemBinding<ResTaskAssign> itemBinding = ItemBinding.of(BR.data, R.layout.item_goods_take);
    private final ReqTaskList reqTaskList = new ReqTaskList(PAGE_SIZE);
    @Inject
    public VMGoodsTake(@NonNull Application application, BaseModel model) {
        super(application, model);
    }

    @Override
    public Call<ResultPage<ResTaskAssign>> getCall(Map<String, Object> params) {
        return apiService.goodsReceiveList(params);
    }

    @Override
    public ReqPage getParams() {
        return reqTaskList;
    }

    /**
     * 检索
     */
    public void search(String keyWord) {
        reqTaskList.setQueryValue(keyWord);
        refresh();
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
            bundle.putParcelable(Const.IntentKey.DATA, data);
            startActivity(GoodsTakeDetailActivity.class, bundle);
        });
        return itemBinding;
    }
}
