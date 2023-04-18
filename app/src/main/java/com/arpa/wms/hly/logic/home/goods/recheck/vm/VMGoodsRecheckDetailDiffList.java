package com.arpa.wms.hly.logic.home.goods.recheck.vm;

import android.app.Application;
import android.os.Bundle;

import androidx.annotation.NonNull;

import com.arpa.and.arch.base.BaseModel;
import com.arpa.wms.hly.BR;
import com.arpa.wms.hly.R;
import com.arpa.wms.hly.base.viewmodel.VMBaseDiffList;
import com.arpa.wms.hly.bean.GoodsItemVO;
import com.arpa.wms.hly.bean.base.ReqBase;
import com.arpa.wms.hly.bean.base.Result;
import com.arpa.wms.hly.bean.req.ReqGoodRecheckDetail;
import com.arpa.wms.hly.dao.AppDatabase;
import com.arpa.wms.hly.dao.SNCodeDao;
import com.arpa.wms.hly.dao.TaskItemDao;
import com.arpa.wms.hly.logic.home.goods.recheck.GoodsRecheckConfirmActivity;
import com.arpa.wms.hly.ui.listener.ViewListener;
import com.arpa.wms.hly.utils.Const.IntentKey;
import com.arpa.wms.hly.utils.Const.TASK_STATUS;

import java.util.List;
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
public class VMGoodsRecheckDetailDiffList extends VMBaseDiffList<GoodsItemVO> {
    public String supplierName; // TODO: 检查数据来源，看能否简化 add by 李一方 2023-04-18 16:55:53
    public ReqGoodRecheckDetail request = new ReqGoodRecheckDetail();
    private SNCodeDao snDao;
    private TaskItemDao taskDao;

    @Inject
    public VMGoodsRecheckDetailDiffList(@NonNull Application application, BaseModel model) {
        super(application, model);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        snDao = getRoomDatabase(AppDatabase.class).snCodeDao();
        taskDao = getRoomDatabase(AppDatabase.class).taskItemDao();
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
    public Call<Result<List<GoodsItemVO>>> getCall(Map<String, Object> params) {
        return apiService.recheckItemListBelow(params);
    }

    @Override
    public ReqBase getParams() {
        return request;
    }

    @Override
    public ItemBinding<GoodsItemVO> getItemBinding() {
        ItemBinding<GoodsItemVO> itemBinding;
        if (request.getRecheckStatus() == TASK_STATUS.RECHECK_WAIT) {
            itemBinding = ItemBinding.of(BR.data, R.layout.item_goods_recheck_detail_wait);
            itemBinding
                    .bindExtra(BR.listener, (ViewListener.DataTransCallback<GoodsItemVO>) data -> {
                        Bundle bundle = new Bundle();
                        bundle.putString(IntentKey.OUTBOUND_CODE, data.getOutboundCode());
                        bundle.putString(IntentKey.OUTBOUND_ITEM_CODE, data.getCode());
                        startActivity(GoodsRecheckConfirmActivity.class, bundle);
                    })
                    .bindExtra(BR.supplier, supplierName);
        } else {
            itemBinding = ItemBinding.of(BR.data, R.layout.item_goods_recheck_detail_yet);
            itemBinding.bindExtra(BR.supplier, supplierName);
        }

        return itemBinding;
    }
}
