package com.arpa.wms.hly.logic.home.inventory.move.vm;

import android.app.Application;
import android.text.TextUtils;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableField;

import com.arpa.and.arch.BR;
import com.arpa.and.arch.base.BaseModel;
import com.arpa.and.arch.base.livedata.StatusEvent;
import com.arpa.wms.hly.R;
import com.arpa.wms.hly.base.viewmodel.WrapDataViewModel;
import com.arpa.wms.hly.bean.GoodsInfo;
import com.arpa.wms.hly.bean.req.ReqMoveSure;
import com.arpa.wms.hly.net.callback.ResultCallback;
import com.arpa.wms.hly.net.exception.ResultError;
import com.arpa.wms.hly.ui.listener.ViewListener;
import com.arpa.wms.hly.utils.NumberUtils;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import me.tatarka.bindingcollectionadapter2.ItemBinding;

/**
 * author: 李一方(<a href="mailto:leergo@dingtalk.com">leergo@dingtalk.com</a>)<br/>
 * version: 1.0.0<br/>
 * since: 2021-05-11 13:45
 *
 * <p>
 * ViewModel: 移位商品详情
 * </p>
 */
@HiltViewModel
public class VMScanGoodsSure extends WrapDataViewModel {
    public ObservableField<Integer> moveQuantity = new ObservableField<>();
    public ObservableField<String> moveLocation = new ObservableField<>();
    public ObservableField<String> outLocation = new ObservableField<>();
    public ObservableArrayList<GoodsInfo> items = new ObservableArrayList<>();
    private Integer maxMoveQuantity = 0;
    private int selectIndex = -1;

    @Inject
    public VMScanGoodsSure(@NonNull Application application, BaseModel model) {
        super(application, model);
    }

    /**
     * 移位确认
     */
    public void moveConfirm() {
        if (-1 == selectIndex) {
            sendMessage("请选中移位商品");
            return;
        }
        if (null == moveQuantity.get()) {
            sendMessage("请输入移位数量");
            return;
        }
        if (TextUtils.isEmpty(moveLocation.get())) {
            sendMessage("请输入移入库位");
            return;
        }
        if (NumberUtils.isLarger(moveQuantity.get(), maxMoveQuantity)) {
            sendMessage("填写数量不能大于移位数量");
            return;
        }

        updateStatus(StatusEvent.Status.LOADING);
        GoodsInfo data = items.get(selectIndex);
        ReqMoveSure reqMoveSure = new ReqMoveSure();
        reqMoveSure.setGoodsCode(data.getGoodsCode());
        reqMoveSure.setLocation(outLocation.get());
        reqMoveSure.setMoveLocation(moveLocation.get());
        reqMoveSure.setMoveQuantity(moveQuantity.get());
        apiService.scanGoodsSure(reqMoveSure).enqueue(new ResultCallback<>() {

            @Override
            public void onSuccess(Object data) {
                updateStatus(StatusEvent.Status.SUCCESS);
                sendMessage(R.string.request_success);
                searchGoodsOnLocation(outLocation.get());
                moveLocation.set(null);
                moveQuantity.set(null);
            }

            @Override
            public void onFailed(ResultError error) {
                sendMessage(error.getMessage(), true);
                updateStatus(StatusEvent.Status.ERROR);
            }
        });
    }

    /**
     * 检索库位上的货物
     */
    public void searchGoodsOnLocation(String location) {
        if (TextUtils.isEmpty(location)) {
            sendMessage("库位不得为空");
            return;
        }
        outLocation.set(location);
        updateStatus(StatusEvent.Status.LOADING);
        /*KW002*/
        apiService.scanGoods(location).enqueue(new ResultCallback<>() {
            @Override
            public void onSuccess(List<GoodsInfo> data) {
                updateStatus(StatusEvent.Status.SUCCESS);
                items.clear();
                items.addAll(data);
            }

            @Override
            public void onFailed(ResultError error) {
                updateStatus(StatusEvent.Status.ERROR);
                sendMessage(error.getMessage());
            }
        });
    }

    public ItemBinding<GoodsInfo> getItemBinding() {
        ItemBinding<GoodsInfo> itemBinding = ItemBinding.of(BR.data, R.layout.item_move_goods);
        itemBinding.bindExtra(BR.listener, (ViewListener.DataTransCallback<GoodsInfo>) data -> {
            int currentIndex = items.indexOf(data);
            if (currentIndex == selectIndex) {
                data.setSelect(false);
                selectIndex = -1;
            } else {
                if (selectIndex != -1) {
                    items.get(selectIndex).setSelect(false);
                }
                data.setSelect(true);
                selectIndex = currentIndex;
            }
            if (data.isSelect()) maxMoveQuantity = data.getQuantity();
        });
        return itemBinding;
    }
}
