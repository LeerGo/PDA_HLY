package com.arpa.wms.hly.ui.dialog.vm;

import android.app.Application;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableInt;

import com.arpa.and.arch.base.BaseModel;
import com.arpa.wms.hly.BR;
import com.arpa.wms.hly.R;
import com.arpa.wms.hly.base.viewmodel.WrapDataViewModel;
import com.arpa.wms.hly.bean.SNCutRule;
import com.arpa.wms.hly.ui.listener.ViewListener;
import com.arpa.wms.hly.utils.Const;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import me.tatarka.bindingcollectionadapter2.ItemBinding;

/**
 * author: 李一方(<a href="mailto:leergo@dingtalk.com">leergo@dingtalk.com</a>)<br/>
 * version: 1.0.0<br/>
 * since: 2023-05-06 10:34
 *
 * <p>
 * vmd: 多规则选择
 * </p>
 */
@HiltViewModel
public class VMDialogMultiRule extends WrapDataViewModel {
    public ObservableArrayList<SNCutRule> items = new ObservableArrayList<>();
    public ObservableArrayList<Integer> indexItems = new ObservableArrayList<>();
    private final ObservableInt indexSel = new ObservableInt(-1);
    public HashMap<Integer, SNCutRule> dataSel;
    private HashMap<Integer, List<SNCutRule>> data;

    @Inject
    public VMDialogMultiRule(@NonNull Application application, BaseModel model) {
        super(application, model);
    }

    public void initParams(Bundle bundle) {
        dataSel = (HashMap<Integer, SNCutRule>) bundle.getSerializable(Const.IntentKey.RECORD);
        data = (HashMap<Integer, List<SNCutRule>>) bundle.getSerializable(Const.IntentKey.DATA);
        indexItems.addAll(data.keySet().stream().sorted().collect(Collectors.toList()));
        indexSel.set(indexItems.get(0));
        items.addAll(data.get(indexSel.get()));
    }

    public ItemBinding<Integer> indexItemBinding() {
        ItemBinding<Integer> itemBinding = ItemBinding.of(BR.data, R.layout.item_multi_rule_index);
        itemBinding.bindExtra(BR.dataSel, indexSel)
                .bindExtra(BR.listener, (ViewListener.DataTransCallback<Integer>) it -> {
                    if (it != indexSel.get()) {
                        indexSel.set(it);
                        items.clear();
                        items.addAll(data.get(it));
                    }
                });
        return itemBinding;
    }

    public ItemBinding<SNCutRule> itemBinding() {
        ItemBinding<SNCutRule> itemBinding = ItemBinding.of(BR.data, R.layout.item_multi_rule);
        itemBinding.bindExtra(BR.listener, (ViewListener.DataTransCallback<SNCutRule>) data -> {
            dataSel.remove(indexSel.get());
            items.forEach(it -> it.setSelect(false));
            data.setSelect(true);
            dataSel.put(indexSel.get(), data);
        });
        return itemBinding;
    }
}
