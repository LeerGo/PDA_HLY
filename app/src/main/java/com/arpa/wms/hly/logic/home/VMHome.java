package com.arpa.wms.hly.logic.home;

import android.app.Application;

import com.arpa.and.wms.arch.base.BaseModel;
import com.arpa.and.wms.arch.base.DataViewModel;
import com.arpa.wms.hly.R;
import com.arpa.wms.hly.bean.MenuBean;
import com.arpa.wms.hly.logic.mine.MineActivity;
import com.arpa.wms.hly.utils.Const;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.MutableLiveData;

/**
 * author: 李一方(<a href="mailto:leergo@dingtalk.com">leergo@dingtalk.com</a>)<br/>
 * version: 1.0.0<br/>
 * since: 2021-04-23 2:20 PM
 *
 * <p>
 * 内容描述区域
 * </p>
 */
// TODO: 参照 MVVMHabit 的写法，使用 ViewAdapter+BindingCollectionAdapter 实现自动绑定 @lyf 2021-04-23 04:25:21
public class VMHome extends DataViewModel {
    protected final MutableLiveData<List<MenuBean>> menuLiveData = new MutableLiveData<>();

    @ViewModelInject
    public VMHome(@NonNull Application application, BaseModel model) {
        super(application, model);
    }

    @Override
    public void onCreate() {
        super.onCreate();

        // TODO: 可能需要先获取权限 @lyf 2021-04-23 03:18:15
        createMenu();
    }

    private void createMenu() {
        // TODO: 首页的菜单是写死根据前端根据权限判断，还是直接后端下发，待定 与 @阎庆玉 后期沟通 @lyf 2021-04-22 10:27:14
        List<MenuBean> menuList = new ArrayList<>();
        menuList.add(new MenuBean(R.mipmap.ic_goods_take, "任务中心", Const.HOME_MENU.TASK_CENTER));
        menuList.add(new MenuBean(R.mipmap.ic_goods_take, "收货"));
        menuList.add(new MenuBean(R.mipmap.ic_goods_recheck, "复核"));
        menuList.add(new MenuBean(R.mipmap.ic_truck_load, "装车"));
        menuList.add(new MenuBean(R.mipmap.ic_inventory_move, "移位", Const.HOME_MENU.INVENTORY_MOVE));
        menuList.add(new MenuBean(R.mipmap.ic_inventory_query, "库存查询"));
        menuLiveData.postValue(menuList);
    }

    public void jumpMine() {
        startActivity(MineActivity.class);
    }
}
