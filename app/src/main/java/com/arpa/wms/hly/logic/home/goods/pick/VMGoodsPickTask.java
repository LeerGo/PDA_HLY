package com.arpa.wms.hly.logic.home.goods.pick;

import android.app.Application;

import com.arpa.and.arch.base.BaseModel;
import com.arpa.and.arch.base.livedata.StatusEvent.Status;
import com.arpa.wms.hly.BR;
import com.arpa.wms.hly.R;
import com.arpa.wms.hly.base.viewmodel.VMBaseRefreshList;
import com.arpa.wms.hly.bean.BatchRuleBean;
import com.arpa.wms.hly.bean.GoodsItemVO;
import com.arpa.wms.hly.bean.base.ReqPage;
import com.arpa.wms.hly.bean.base.ResultPage;
import com.arpa.wms.hly.bean.req.ReqTaskList;
import com.arpa.wms.hly.bean.res.ResPickDetail;
import com.arpa.wms.hly.bean.res.ResTaskAssign;
import com.arpa.wms.hly.net.callback.ResultCallback;
import com.arpa.wms.hly.net.exception.ResultError;
import com.arpa.wms.hly.ui.listener.ViewListener;
import com.arpa.wms.hly.utils.Const.JOB_STATUS;
import com.arpa.wms.hly.utils.Const.TASK_TYPE;
import com.arpa.wms.hly.utils.ToastUtils;

import java.util.Map;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableBoolean;
import androidx.databinding.ObservableField;
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
public class VMGoodsPickTask extends VMBaseRefreshList<ResTaskAssign> {
    private final ReqTaskList taskRequest = new ReqTaskList(PAGE_SIZE);
    // 拣货单
    private final GoodsPickTaskAdapter<ResTaskAssign> adapter = new GoodsPickTaskAdapter<>();
    // 任务列表
    private final ItemBinding<ResTaskAssign> taskBinding = ItemBinding.of(BR.data, R.layout.item_pick_list);
    // 拣货单详情
    private final ItemBinding<GoodsItemVO> taskDetailBinding = ItemBinding.of(BR.data, R.layout.item_pick_detail);
    // 拣货单详情 - 操作
    private final ItemBinding<GoodsItemVO> taskOperateBinding = ItemBinding.of(BR.data, R.layout.item_pick_operation);
    // 拣货单 - 详情
    public GoodsPickTaskAdapter<GoodsItemVO> taskDetailAdapter = new GoodsPickTaskAdapter<>();
    // 拣货单 - 操作
    public GoodsPickTaskAdapter<GoodsItemVO> taskOperateAdapter = new GoodsPickTaskAdapter<>();
    // 批次规则
    public BatchRuleBean batchRule = new BatchRuleBean();
    public ObservableField<BatchRuleBean> rule = new ObservableField<>();
    // 任务清单商品列表
    public ObservableArrayList<GoodsItemVO> taskDetailItems = new ObservableArrayList<>();

    public ObservableBoolean detailRefreshing = new ObservableBoolean();
    public ObservableBoolean detailEnable = new ObservableBoolean(true);
    private String sourceCode;

    @Inject
    public VMGoodsPickTask(@NonNull Application application, BaseModel model) {
        super(application, model);
    }

    @Override
    protected void refreshComplete(boolean isRefresh) {
        super.refreshComplete(isRefresh);

        // 任务单刷新完成后，自动定位到第一个条目，然后刷新任务详情
        if (isRefresh && !getItems().isEmpty()) {
            detailEnable.set(true);
            adapter.setPositionSel(0);
            getItems().get(0).setSelect(true);
            sourceCode = getItems().get(0).getSourceCode();
            requestDetail();
        }
        // 如果任务单没有，则详情也要清空
        // fix: http://112.6.75.17:801/zentao/bug-view-26075.html
        if (getItems().isEmpty()) {
            taskDetailItems.clear();
            sourceCode = null;
            detailEnable.set(false);
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
        apiService.pickingDetail(sourceCode).enqueue(new ResultCallback<>() {
            @Override
            public void onSuccess(ResPickDetail data) {
                batchRule = data.getBatchRule();
                rule.set(batchRule);
                if (taskDetailAdapter.getPositionSel() != -1)
                    data.getGoods().get(taskDetailAdapter.getPositionSel()).setSelect(true);
                taskDetailItems.clear();
                taskDetailItems.addAll(data.getGoods());
                updateStatus(Status.SUCCESS);
            }

            @Override
            public void onFinish() {
                super.onFinish();
                detailRefreshing.set(false);
            }

            @Override
            public void onFailed(ResultError error) {
                taskDetailItems.clear();
                updateStatus(Status.ERROR);
                sendMessage(error.getMessage());
            }
        });
    }

    public ItemBinding<GoodsItemVO> getTaskDetailBinding() {
        taskDetailBinding.bindExtra(BR.rule, batchRule);
        return taskDetailBinding;
    }

    public ItemBinding<GoodsItemVO> getTaskOperateBinding() {
        taskOperateBinding.bindExtra(BR.onAdd, (ViewListener.OnItemClickListener<GoodsItemVO>) (view, position, data) -> {
            if (position != taskDetailAdapter.getPositionSel()) {
                taskDetailItems.get(position).setSelect(true);
                if (null != taskDetailAdapter.getItemSel()) {
                    taskDetailAdapter.getItemSel().setSelect(false);
                    taskOperateAdapter.getItemSel().setSelect(false);
                }
                taskDetailAdapter.setPositionSel(position);
                taskOperateAdapter.setPositionSel(position);
                taskDetailAdapter.notifyDataSetChanged();
                taskOperateAdapter.notifyDataSetChanged();
            }
            if (data.isPickFinish()) ToastUtils.showShortSafe("当前订单已拣货完成");
            else pickConfirm(position);
        }).bindExtra(BR.onEdit, (ViewListener.OnItemClickListener<GoodsItemVO>) (view, position, data) -> {
            // TODO: 编辑功能待实现待实现 add by @lyf 2022-05-13 09:08
            // 发送通知前台弹编辑窗口
            // 拣货单编辑只允许叉车和仓库主管有修改权限
        });
        return taskOperateBinding;
    }

    /**
     * 拣货确认
     */
    private void pickConfirm(int position) {
        updateStatus(Status.LOADING);
        apiService.pickingConfirm(taskDetailItems.get(position).getCode())
                .enqueue(new ResultCallback<Object>() {
                    @Override
                    public void onSuccess(Object data) {
                        updateStatus(Status.SUCCESS);
                        ToastUtils.showShort(R.string.request_success);
                        taskDetailAdapter.getItemSel().increasePickingTraysNum();
                        taskDetailAdapter.notifyItemChanged(position);

                        if (isAllPickFinish()) {// 如果全都拣货完成，刷新拣货单列表
                            taskDetailAdapter.resetPositionSel();
                            autoRefresh();
                        } else {// 否则刷新当前任务详情列表
                            // 如果当前选中的条目已经拣货完成，重置选中索引
                            if (taskDetailAdapter.getItemSel() != null && taskDetailAdapter.getItemSel().isPickFinish()) {
                                taskDetailAdapter.getItemSel().setSelect(false);
                                taskDetailAdapter.notifyItemChanged(position);
                                taskDetailAdapter.resetPositionSel();
                            }
                            requestDetail();
                        }
                    }

                    /**
                     * 拣货单下单据是否全部拣货完成
                     * @return true - 完成
                     */
                    private boolean isAllPickFinish() {
                        boolean result = true;
                        for (GoodsItemVO taskItem : taskDetailItems) {
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
