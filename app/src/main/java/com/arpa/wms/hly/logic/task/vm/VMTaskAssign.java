package com.arpa.wms.hly.logic.task.vm;

import android.annotation.SuppressLint;
import android.app.Application;

import com.arpa.and.wms.arch.base.BaseModel;
import com.arpa.wms.hly.BR;
import com.arpa.wms.hly.R;
import com.arpa.wms.hly.base.viewmodel.VMBaseRefreshList;
import com.arpa.wms.hly.bean.base.ReqPage;
import com.arpa.wms.hly.bean.base.ResultPage;
import com.arpa.wms.hly.bean.req.ReqTaskList;
import com.arpa.wms.hly.bean.res.ResTaskAssign;

import java.util.Map;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableBoolean;
import androidx.databinding.ObservableInt;
import dagger.hilt.android.lifecycle.HiltViewModel;
import me.tatarka.bindingcollectionadapter2.ItemBinding;
import retrofit2.Call;

@HiltViewModel
public class VMTaskAssign extends VMBaseRefreshList<ResTaskAssign> {
    public final ObservableInt type = new ObservableInt();
    private final ObservableBoolean isSelectAll = new ObservableBoolean();
    private final ReqTaskList reqTaskList = new ReqTaskList(PAGE_SIZE);
    private final ItemBinding<ResTaskAssign> itemBinding = ItemBinding.of(BR.data, R.layout.item_task_list);

    @Inject
    public VMTaskAssign(@NonNull Application application, BaseModel model) {
        super(application, model);
    }

    @Override
    public void configAdapter() {
        super.configAdapter();
    }

    @Override
    public Call<ResultPage<ResTaskAssign>> getCall(Map<String, Object> params) {
        return apiService.pdaTasks(params);
    }

    @Override
    public ReqPage getParams() {
//        reqTaskList.setTaskType(TASK_TYPE.RECEIVE);
//        reqTaskList.setJobStatus(Const.JOB_STATUS.UNFINISHED);
//        reqTaskList.setWarehouseCode(spGetString(Const.SPKEY.WAREHOUSE_CODE));
        reqTaskList.setAssign(type.get());
        return reqTaskList;
    }

    @Override
    public ItemBinding<ResTaskAssign> getItemBinding() {
        return itemBinding;
    }

    @SuppressLint("NotifyDataSetChanged")
    public void selectAll() {
        isSelectAll.set(!isSelectAll.get());
        for (ResTaskAssign item : getItems()) {
            item.setSelect(isSelectAll.get());
        }
        getAdapter().notifyDataSetChanged();
    }

    public ObservableBoolean getIsSelectAll() {
        return isSelectAll;
    }
}