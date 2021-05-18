package com.arpa.wms.hly.logic.task.vm;

import android.annotation.SuppressLint;
import android.app.Application;

import com.arpa.and.wms.arch.base.BaseModel;
import com.arpa.and.wms.arch.base.livedata.StatusEvent;
import com.arpa.wms.hly.BR;
import com.arpa.wms.hly.R;
import com.arpa.wms.hly.base.viewmodel.VMBaseRefreshList;
import com.arpa.wms.hly.bean.PartyCodeList;
import com.arpa.wms.hly.bean.base.ReqPage;
import com.arpa.wms.hly.bean.base.ResultPage;
import com.arpa.wms.hly.bean.req.ReqTaskAssign;
import com.arpa.wms.hly.bean.req.ReqTaskList;
import com.arpa.wms.hly.bean.res.ResTaskAssign;
import com.arpa.wms.hly.net.callback.ResultCallback;
import com.arpa.wms.hly.net.exception.ResultError;

import java.util.List;
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
    private final ReqTaskAssign reqTaskAssign = new ReqTaskAssign();
    private final ItemBinding<ResTaskAssign> itemBinding = ItemBinding.of(BR.data, R.layout.item_task_list);

    @Inject
    public VMTaskAssign(@NonNull Application application, BaseModel model) {
        super(application, model);
    }

    @Override
    public Call<ResultPage<ResTaskAssign>> getCall(Map<String, Object> params) {
        return apiService.pdaTasks(params);
    }

    @Override
    public ReqPage getParams() {
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

    // TODO: 分配人员，待完成 @lyf 2021-05-17 02:56:35
    public void taskAssign(String workType, String workerType, List<PartyCodeList> staffList) {
        updateStatus(StatusEvent.Status.LOADING);
        buildReqParams(workType, workerType);
        reqTaskAssign.setPartyCodeList(staffList);
        // TODO: 请求 @lyf 2021-05-17 02:47:03
        apiService.pdaTasksAssign(reqTaskAssign)
                .enqueue(new ResultCallback<Object>() {
                    @Override
                    public void onSuccess(Object data) {
                        sendMessage("分配成功");
                        updateStatus(StatusEvent.Status.SUCCESS, true);
                    }

                    @Override
                    public void onFailed(ResultError error) {
                        sendMessage(error.getMessage(), true);
                        updateStatus(StatusEvent.Status.ERROR, true);
                    }
                });
    }

    /**
     * 构建请求参数
     *
     * @param workType
     *         作业类型
     * @param workerType
     *         工种类型
     */
    private void buildReqParams(String workType, String workerType) {
        reqTaskAssign.getOrderCodes().clear();
        reqTaskAssign.getPartyCodeList().clear();

        reqTaskAssign.setWorkType(workType);
        reqTaskAssign.setWorkerType(workerType);
        for (ResTaskAssign item : getItems()) {
            if (item.isSelect()) reqTaskAssign.getOrderCodes().add(item.getCode());
        }
    }

    // TODO: 取消分配人员，待完成 @lyf 2021-05-17 02:56:35
    public void taskAssignCancel(String workType, String workerType) {
        updateStatus(StatusEvent.Status.LOADING);
        buildReqParams(workType, workerType);
        // TODO: 请求 @lyf 2021-05-17 02:47:03
        apiService.pdaTasksCancelAssign(reqTaskAssign)
                .enqueue(new ResultCallback<Object>() {
                    @Override
                    public void onSuccess(Object data) {
                        sendMessage("取消分配成功");
                        updateStatus(StatusEvent.Status.SUCCESS, true);
                    }

                    @Override
                    public void onFailed(ResultError error) {
                        sendMessage(error.getMessage(), true);
                        updateStatus(StatusEvent.Status.ERROR, true);
                    }
                });
    }
}