package com.arpa.wms.hly.ui.dialog.vm;

import android.app.Application;

import com.arpa.and.arch.base.BaseModel;
import com.arpa.wms.hly.BR;
import com.arpa.wms.hly.R;
import com.arpa.wms.hly.base.viewmodel.WrapDataViewModel;
import com.arpa.wms.hly.bean.PickingItemVO;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableArrayList;
import dagger.hilt.android.lifecycle.HiltViewModel;
import me.tatarka.bindingcollectionadapter2.ItemBinding;

/**
 * author: 李一方(<a href="mailto:leergo@dingtalk.com">leergo@dingtalk.com</a>)<br/>
 * version: 1.0.0<br/>
 * since: 2022/5/26 16:12
 *
 * <p>
 * vm: 拣货修改弹窗
 * </p>
 */
@HiltViewModel
public class VMDialogPickEdit extends WrapDataViewModel {
    public ObservableArrayList<PickingItemVO> items = new ObservableArrayList<>();
    public ItemBinding<PickingItemVO> itemBinding = ItemBinding.of(BR.data, R.layout.item_pick_edit);

    @Inject
    public VMDialogPickEdit(@NonNull Application application, BaseModel model) {
        super(application, model);
    }

    @Override
    public void onCreate() {
        super.onCreate();

        addNew();
    }

    public void addNew() {
        if (items.isEmpty() || checkItemsValid()) items.add(new PickingItemVO());
    }

    public boolean checkItemsValid() {
        for (int i = 0; i < items.size(); i++) {
            if (!items.get(i).inputValid()) {
                sendMessage("请完善第" + (i + 1) + "条数据");
                return false;
            }
        }
        return true;
    }
}
