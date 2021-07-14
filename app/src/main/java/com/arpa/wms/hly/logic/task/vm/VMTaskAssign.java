package com.arpa.wms.hly.logic.task.vm;

import android.annotation.SuppressLint;
import android.app.Application;
import android.os.Bundle;

import com.arpa.and.arch.base.BaseModel;
import com.arpa.and.arch.base.livedata.StatusEvent;
import com.arpa.wms.hly.BR;
import com.arpa.wms.hly.R;
import com.arpa.wms.hly.base.viewmodel.VMBaseRefreshList;
import com.arpa.wms.hly.bean.PartyCodeList;
import com.arpa.wms.hly.bean.TaskStaff;
import com.arpa.wms.hly.bean.TaskStaffSelect;
import com.arpa.wms.hly.bean.base.ReqPage;
import com.arpa.wms.hly.bean.base.ResultPage;
import com.arpa.wms.hly.bean.req.ReqTaskAssign;
import com.arpa.wms.hly.bean.req.ReqTaskList;
import com.arpa.wms.hly.bean.req.ReqWorkStaff;
import com.arpa.wms.hly.bean.res.ResTaskAssign;
import com.arpa.wms.hly.bean.res.ResTaskWorker;
import com.arpa.wms.hly.logic.task.TaskGoodsPickActivity;
import com.arpa.wms.hly.logic.task.TaskGoodsTakeActivity;
import com.arpa.wms.hly.net.callback.ResultCallback;
import com.arpa.wms.hly.net.exception.ResultError;
import com.arpa.wms.hly.ui.listener.ViewListener.DataTransCallback;
import com.arpa.wms.hly.utils.Const;
import com.arpa.wms.hly.utils.ToastUtils;

import java.util.Map;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableBoolean;
import androidx.databinding.ObservableInt;
import androidx.lifecycle.MutableLiveData;
import dagger.hilt.android.lifecycle.HiltViewModel;
import me.tatarka.bindingcollectionadapter2.ItemBinding;
import retrofit2.Call;

import static com.arpa.wms.hly.utils.Const.ASSIGN_WORK.GOODS_PICK;
import static com.arpa.wms.hly.utils.Const.ASSIGN_WORK.GOODS_TAKE;
import static com.arpa.wms.hly.utils.Const.IntentKey.DATA;

@HiltViewModel
public class VMTaskAssign extends VMBaseRefreshList<ResTaskAssign> {
    public final ObservableInt type = new ObservableInt();
    public final MutableLiveData<ResTaskWorker> resTaskWorker = new MutableLiveData<>();

    private final ReqTaskAssign reqTaskAssign = new ReqTaskAssign();
    private final ReqTaskList reqTaskList = new ReqTaskList(PAGE_SIZE);
    private final ObservableBoolean isSelectAll = new ObservableBoolean();
    private final ItemBinding<ResTaskAssign> itemBinding = ItemBinding.of(BR.data, R.layout.item_task_list);

    public int workerType = -1;

    @Inject
    public VMTaskAssign(@NonNull Application application, BaseModel model) {
        super(application, model);
    }

    @Override
    protected boolean setAutoRefresh() {
        return false;
    }

    @Override
    protected void refreshComplete(boolean isRefresh) {
        super.refreshComplete(isRefresh);
        isSelectAll.set(false);
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
        itemBinding.bindExtra(BR.listener, (DataTransCallback<ResTaskAssign>) data -> {
            Bundle bundle = new Bundle();
            data.toTaskGoodDetail();
            bundle.putParcelable(DATA, data);

            if (GOODS_TAKE.equals(data.getTaskTypeDes())) { // 跳转收货详情 @lyf 2021-05-06 10:09:04
                startActivity(TaskGoodsTakeActivity.class, bundle);
            } else if (GOODS_PICK.equals(data.getTaskTypeDes())) { // 跳转拣货详情 @lyf 2021-05-06 10:09:04
                startActivity(TaskGoodsPickActivity.class, bundle);
            }
        }).bindExtra(BR.select, (DataTransCallback<ResTaskAssign>) data -> { // 多选
            data.setSelect(!data.isSelect());
            inverseSelectAll();
            getAdapter().notifyDataSetChanged();
        });
        return itemBinding;
    }

    /**
     * 反向全选（每一条目选中后，反向判断是否全选）
     */
    private void inverseSelectAll() {
        boolean isItemAllSelect = true;
        for (ResTaskAssign item : getItems()) {
            if (!item.isSelect()) {
                isItemAllSelect = false;
                break;
            }
        }
        isSelectAll.set(isItemAllSelect);
    }

    /**
     * 正向全选（全选后，选中每一条）
     */
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

    /**
     * 分配人员
     */
    public void taskAssign(TaskStaffSelect data) {
        updateStatus(StatusEvent.Status.LOADING);
        buildReqParams(Const.ASSIGN_WORK.WORKER_TYPE[workerType], data);
        apiService.pdaTasksAssign(reqTaskAssign)
                .enqueue(new ResultCallback<Object>() {
                    @Override
                    public void onSuccess(Object data) {
                        sendMessage("分配成功");
                        updateStatus(StatusEvent.Status.SUCCESS, true);
                        autoRefresh();
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
     */
    private void buildReqParams(String workerType, TaskStaffSelect data) {
        // 清理参数
        reqTaskAssign.getOrderCodes().clear();
        reqTaskAssign.getPartyCodeList().clear();

        // 拼接参数
        reqTaskAssign.setWorkerType(workerType);
        for (ResTaskAssign item : getItems()) {
            if (item.isSelect()) reqTaskAssign.getOrderCodes().add(item.getCode());
        }

        if (null != data) {
            for (TaskStaff staff : data.getStaffs()) {
                reqTaskAssign.getPartyCodeList().add(new PartyCodeList(staff.getCode(), staff.getName()));
            }

            if (null != data.getJobType())
                reqTaskAssign.setWorkType(data.getJobType().getValue());
        }
    }

    /**
     * 取消分配人员
     */
    public void taskAssignCancel(String workerType) {
        updateStatus(StatusEvent.Status.LOADING);
        buildReqParams(workerType, null);
        apiService.pdaTasksCancelAssign(reqTaskAssign)
                .enqueue(new ResultCallback<Object>() {
                    @Override
                    public void onSuccess(Object data) {
                        sendMessage("取消分配成功");
                        updateStatus(StatusEvent.Status.SUCCESS, true);
                        autoRefresh();
                    }

                    @Override
                    public void onFinish() {
                        super.onFinish();
                        //                        isSelectAll.set(false);
                        hideLoading();
                    }

                    @Override
                    public void onFailed(ResultError error) {
                        sendMessage(error.getMessage(), true);
                        updateStatus(StatusEvent.Status.ERROR, true);
                    }
                });
    }

    /**
     * 获取仓库作业员列表
     */
    public void getWorkStaff(int assignType) {
        workerType = assignType;
        showLoading();
        ReqWorkStaff reqWorkStaff = new ReqWorkStaff(assignType);
        apiService.getWorkStaff(reqWorkStaff.toParams())
                .enqueue(new ResultCallback<ResTaskWorker>() {
                    @Override
                    public void onSuccess(ResTaskWorker data) {
                        if (data.getStaff().isEmpty()) {
                            ToastUtils.showShortSafe("未获取到可用作业人员");
                        } else {
                            resTaskWorker.postValue(data);
                        }
                        updateStatus(StatusEvent.Status.SUCCESS, true);
                    }

                    @Override
                    public void onFinish() {
                        super.onFinish();
                        //                        isSelectAll.set(false);
                        hideLoading();
                    }

                    @Override
                    public void onFailed(ResultError error) {
                        sendMessage(error.getMessage(), true);
                        updateStatus(StatusEvent.Status.ERROR, true);
                    }
                });
    }
}