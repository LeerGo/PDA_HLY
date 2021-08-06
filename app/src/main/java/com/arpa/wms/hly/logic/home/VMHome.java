package com.arpa.wms.hly.logic.home;

import android.app.Application;

import com.arpa.and.arch.base.BaseModel;
import com.arpa.wms.hly.BR;
import com.arpa.wms.hly.R;
import com.arpa.wms.hly.base.viewmodel.WrapDataViewModel;
import com.arpa.wms.hly.bean.MenuBean;
import com.arpa.wms.hly.bean.res.ResRole;
import com.arpa.wms.hly.logic.home.goods.pick.GoodsPickTaskActivity;
import com.arpa.wms.hly.logic.home.goods.recheck.GoodsRecheckActivity;
import com.arpa.wms.hly.logic.home.goods.take.GoodsTakeActivity;
import com.arpa.wms.hly.logic.home.inventory.move.ScanLocationActivity;
import com.arpa.wms.hly.logic.home.inventory.query.InventoryQueryActivity;
import com.arpa.wms.hly.logic.home.truckload.TruckLoadActivity;
import com.arpa.wms.hly.logic.mine.MineActivity;
import com.arpa.wms.hly.logic.task.TaskCenterActivity;
import com.arpa.wms.hly.net.callback.ResultCallback;
import com.arpa.wms.hly.net.exception.ResultError;
import com.arpa.wms.hly.utils.Const.SPKEY;

import java.util.List;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableField;
import dagger.hilt.android.lifecycle.HiltViewModel;
import me.tatarka.bindingcollectionadapter2.ItemBinding;

import static com.arpa.and.arch.base.livedata.StatusEvent.Status.ERROR;
import static com.arpa.and.arch.base.livedata.StatusEvent.Status.LOADING;
import static com.arpa.and.arch.base.livedata.StatusEvent.Status.SUCCESS;

/**
 * author: 李一方(<a href="mailto:leergo@dingtalk.com">leergo@dingtalk.com</a>)<br/>
 * version: 1.0.0<br/>
 * since: 2021-04-23 2:20 PM
 *
 * <p>
 * ViewModel：首页
 * </p>
 */
@HiltViewModel
public class VMHome extends WrapDataViewModel {
    private final ObservableField<String> account = new ObservableField<>();
    private final ObservableField<String> warehouse = new ObservableField<>();

    private final ObservableArrayList<MenuBean> items = new ObservableArrayList<>();
    private final ItemBinding<MenuBean> itemBinding = ItemBinding.of(BR.data, R.layout.item_home_menu);

    @Inject
    public VMHome(@NonNull Application application, BaseModel model) {
        super(application, model);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        getRole();
    }

    private void getRole() {
        updateStatus(LOADING);
        apiService.getRole().enqueue(new ResultCallback<List<ResRole>>() {
            @Override
            public void onSuccess(List<ResRole> data) {
                createMenu(checkRole(data));
                updateStatus(SUCCESS);
            }

            /**
             * 检查权限，如果角色集合中出现一个仅允许拣货的权限，则只显示拣货菜单
             */
            private boolean checkRole(List<ResRole> data) {
                boolean isOnlyPick = false;
                if (null != data && !data.isEmpty()) {
                    for (ResRole role : data) {
                        isOnlyPick = role.isOnlyPick();
                        if (isOnlyPick) break;
                    }
                }
                return isOnlyPick;
            }

            @Override
            public void onFailed(ResultError error) {
                updateStatus(ERROR);
                sendMessage(error.getMessage());
            }
        });
    }

    /**
     * 创建菜单
     */
    private void createMenu(boolean isOnlyPick) {
        // 切换横竖屏会重新执行 onCreate，懒得配置了，判空好了
        if (items.isEmpty()) {
            if (isOnlyPick) {
                items.add(new MenuBean(R.mipmap.ic_home_goods_pick, "拣货", "Picking Goods", GoodsPickTaskActivity.class));
            } else {
                items.add(new MenuBean(R.mipmap.ic_home_task_center, "任务中心", "Task Center", TaskCenterActivity.class));
                items.add(new MenuBean(R.mipmap.ic_home_goods_take, "收货", "Receiving Goods", GoodsTakeActivity.class));
                items.add(new MenuBean(R.mipmap.ic_home_goods_pick, "拣货", "Picking Goods", GoodsPickTaskActivity.class));
                items.add(new MenuBean(R.mipmap.ic_home_goods_recheck, "复核", "To Review", GoodsRecheckActivity.class));
                items.add(new MenuBean(R.mipmap.ic_home_truck_load, "装车", "Loading", TruckLoadActivity.class));
                items.add(new MenuBean(R.mipmap.ic_home_inventory_move, "移位", "Displacement", ScanLocationActivity.class));
                items.add(new MenuBean(R.mipmap.ic_home_inventory_query, "库存查询", "Inventory Query", InventoryQueryActivity.class));
            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        initHeader();
    }

    /**
     * 顶部信息
     */
    private void initHeader() {
        account.set("账号：" + spGetString(SPKEY.USER_NAME));
        warehouse.set("仓库：" + spGetString(SPKEY.WAREHOUSE_NAME));
    }

    public void jumpMine() {
        startActivity(MineActivity.class);
    }


    public ObservableField<String> getAccount() {
        return account;
    }

    public ObservableField<String> getWarehouse() {
        return warehouse;
    }

    public ObservableArrayList<MenuBean> getItems() {
        return items;
    }

    public ItemBinding<MenuBean> getItemBinding() {
        return itemBinding;
    }
}
