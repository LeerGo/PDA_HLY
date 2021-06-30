package com.arpa.wms.hly.logic.home.goods.pick;

import android.app.Application;

import com.arpa.and.arch.base.BaseModel;
import com.arpa.and.arch.base.livedata.StatusEvent.Status;
import com.arpa.wms.hly.BR;
import com.arpa.wms.hly.R;
import com.arpa.wms.hly.base.viewmodel.VMBaseRefreshList;
import com.arpa.wms.hly.bean.GoodsItemVO;
import com.arpa.wms.hly.bean.base.ReqPage;
import com.arpa.wms.hly.bean.base.ResultPage;
import com.arpa.wms.hly.bean.req.ReqTaskList;
import com.arpa.wms.hly.bean.res.ResTaskAssign;
import com.arpa.wms.hly.net.callback.ResultCallback;
import com.arpa.wms.hly.net.exception.ResultError;
import com.arpa.wms.hly.ui.listener.ViewListener;
import com.arpa.wms.hly.utils.Const.JOB_STATUS;
import com.arpa.wms.hly.utils.Const.TASK_TYPE;
import com.arpa.wms.hly.utils.ToastUtils;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableBoolean;
import dagger.hilt.android.lifecycle.HiltViewModel;
import me.tatarka.bindingcollectionadapter2.ItemBinding;
import retrofit2.Call;

/**
 * author: 李一方(<a href="mailto:leergo@dingtalk.com">leergo@dingtalk.com</a>)<br/>
 * version: 1.0.0<br/>
 * since: 2021-05-14 16:07
 *
 * <p>
 * ViewModel: 拣货
 * </p>
 */
@HiltViewModel
public class VMGoodsPick extends VMBaseRefreshList<ResTaskAssign> {
    // 拣货单
    private final GoodsPickTaskAdapter<ResTaskAssign> adapter = new GoodsPickTaskAdapter<>();
    private final ReqTaskList taskRequest = new ReqTaskList(PAGE_SIZE);
    private final ItemBinding<ResTaskAssign> taskBinding = ItemBinding.of(BR.data, R.layout.item_pick_list);

    // 拣货单详情
    private final ItemBinding<GoodsItemVO> taskDetailBinding = ItemBinding.of(BR.data, R.layout.item_pick_detail);
    public ObservableBoolean detailRefreshing = new ObservableBoolean();
    public ObservableArrayList<GoodsItemVO> taskItems = new ObservableArrayList<>();
    public GoodsPickTaskAdapter<GoodsItemVO> taskDetailAdapter = new GoodsPickTaskAdapter<>();
    private String sourceCode;

    @Inject
    public VMGoodsPick(@NonNull Application application, BaseModel model) {
        super(application, model);
    }

    @Override
    protected void refreshComplete(boolean isRefresh) {
        super.refreshComplete(isRefresh);

        // 任务单刷新完成后，自动定位到第一个条目，然后刷新任务详情
        if (isRefresh && !getItems().isEmpty()) {
            adapter.setPositionSel(0);
            getItems().get(0).setSelect(true);
            sourceCode = getItems().get(0).getSourceCode();
            requestDetail();
        }
    }

    @Override
    public Call<ResultPage<ResTaskAssign>> getCall(Map<String, Object> params) {
        return apiService.pdaTasks(params);
    }

    @Override
    public ReqPage getParams() {
        taskRequest.setJobStatus(JOB_STATUS.UNFINISHED);
        taskRequest.setTaskType(TASK_TYPE.PICKING);
        return taskRequest;
    }

    @Override
    public void configAdapter() {
        setAdapter(adapter);
    }

    @Override
    public ItemBinding<ResTaskAssign> getItemBinding() {
        taskBinding.bindExtra(BR.onTaskClick, (ViewListener.OnItemClickListener<ResTaskAssign>) (view, position, data) -> {
            // 方式重复点击导致多次详情请求
            if (position != adapter.getPositionSel()) {
                getItems().get(position).setSelect(true);
                getItems().get(adapter.getPositionSel()).setSelect(false);
                adapter.setPositionSel(position);
                adapter.notifyDataSetChanged();
                sourceCode = data.getSourceCode();
                taskDetailAdapter.resetPositionSel();
                requestDetail();
            }
        });
        return taskBinding;
    }

    /**
     * 获取拣货单详情
     */
    public void requestDetail() {
        updateStatus(Status.LOADING);
        detailRefreshing.set(true);
        apiService.pickingDetail(sourceCode)
                .enqueue(new ResultCallback<List<GoodsItemVO>>() {
                    @Override
                    public void onSuccess(List<GoodsItemVO> data) {
                        taskItems.clear();
                        taskItems.addAll(data);
                        updateStatus(Status.SUCCESS);
                    }

                    @Override
                    public void onFinish() {
                        super.onFinish();
                        detailRefreshing.set(false);
                    }

                    @Override
                    public void onFailed(ResultError error) {
                        taskItems.clear();
                        updateStatus(Status.ERROR);
                        sendMessage(error.getMessage());
                    }
                });
    }

    public ItemBinding<GoodsItemVO> getTaskDetailBinding() {
        taskDetailBinding.bindExtra(BR.onOperate, (ViewListener.OnItemClickListener<GoodsItemVO>) (view, position, data) -> {
            // TODO: 拣货数量同步服务器 @lyf 2021-05-24 03:39:14
            taskDetailAdapter.setPositionSel(position);
            taskDetailAdapter.notifyDataSetChanged();
            pickConfirm(position);
        });
        return taskDetailBinding;
    }

    /**
     * 拣货确认
     */
    private void pickConfirm(int position) {
        updateStatus(Status.LOADING);
        apiService.pickingConfirm(taskItems.get(position).getCode())
                .enqueue(new ResultCallback<Object>() {
                    @Override
                    public void onSuccess(Object data) {
                        updateStatus(Status.SUCCESS);
                        ToastUtils.showShort(R.string.request_success);
                        // TODO: 现阶段数据不足，数据联通需要测试交互 @lyf 2021-05-25 01:50:59
                        if (isAllPickFinish()) {// 如果全都拣货完成，刷新拣货单列表
                            taskDetailAdapter.resetPositionSel();
                            autoRefresh();
                        } else {// 否则刷新当前任务详情列表
                            // 如果当前选中的条目已经拣货完成，重置选中索引
                            if (taskDetailAdapter.getItemSel() != null && taskDetailAdapter.getItemSel().isPickFinish())
                                taskDetailAdapter.resetPositionSel();
                            requestDetail();
                        }
                    }

                    /**
                     * 拣货单下单据是否全部拣货完成
                     * @return true - 完成
                     */
                    private boolean isAllPickFinish() {
                        boolean result = true;
                        for (GoodsItemVO taskItem : taskItems) {
                            if (!taskItem.isPickFinish()) {
                                result = false;
                                break;
                            }
                        }
                        return result;
                    }

                    @Override
                    public void onFailed(ResultError error) {
                        updateStatus(Status.ERROR);
                        sendMessage(error.getMessage());
                    }
                });
    }
}
