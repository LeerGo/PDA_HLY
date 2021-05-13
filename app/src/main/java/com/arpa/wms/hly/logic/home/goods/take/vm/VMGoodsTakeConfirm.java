package com.arpa.wms.hly.logic.home.goods.take.vm;

import android.annotation.SuppressLint;
import android.app.Application;
import android.util.Log;

import com.arpa.and.wms.arch.base.BaseModel;
import com.arpa.wms.hly.BR;
import com.arpa.wms.hly.R;
import com.arpa.wms.hly.base.viewmodel.WrapDataViewModel;
import com.arpa.wms.hly.bean.GoodsTakeBatchHeader;
import com.arpa.wms.hly.bean.GoodsTakeBatchItem;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableList;
import dagger.hilt.android.lifecycle.HiltViewModel;
import me.tatarka.bindingcollectionadapter2.OnItemBind;

/**
 * author: 李一方(<a href="mailto:leergo@dingtalk.com">leergo@dingtalk.com</a>)<br/>
 * version: 1.0.0<br/>
 * since: 2021-04-25 2:01 PM
 *
 * <p>
 * 页面：商品收货确认
 * </p>
 */
@HiltViewModel
public class VMGoodsTakeConfirm extends WrapDataViewModel {
    // adapter 相关
    public final ObservableList<Object> items = new ObservableArrayList<>();
    public final OnItemBind<Object> onItemBind =
            (itemBinding, position, data) -> {
                if (position == 0) {
                    itemBinding.set(BR.data, R.layout.header_goods_take_confirm);
                } else {
                    itemBinding.set(BR.data, R.layout.item_goods_take_confirm);

                }
            };

    @Inject
    public VMGoodsTakeConfirm(@NonNull Application application, BaseModel model) {
        super(application, model);
    }

    @Override
    public void onCreate() {
        super.onCreate();

        items.add(new GoodsTakeBatchHeader());
        GoodsTakeBatchItem item= new GoodsTakeBatchItem();
        item.setReceivedCount("1000");
        items.add(item);
    }

    public void addBatchItem() {
        items.add(new GoodsTakeBatchItem());
    }

    @SuppressLint("LogNotTimber")
    public void orderBatchConfirm() {
        Log.e("@@@@ L61", "VMGoodsTakeConfirm:orderBatchConfirm() -> \n" + items.toString());
    }
}
