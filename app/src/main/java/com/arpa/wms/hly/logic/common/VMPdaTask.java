package com.arpa.wms.hly.logic.common;

import android.app.Application;

import com.arpa.and.wms.arch.base.BaseModel;
import com.arpa.wms.hly.base.viewmodel.VMBaseRefreshList;
import com.arpa.wms.hly.bean.base.ReqPage;
import com.arpa.wms.hly.bean.base.ResultPage;
import com.arpa.wms.hly.bean.req.ReqTaskList;
import com.arpa.wms.hly.bean.res.ResPdaTask;
import com.arpa.wms.hly.utils.Const;

import java.util.Map;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;
import retrofit2.Call;

/**
 * author: 李一方(<a href="mailto:leergo@dingtalk.com">leergo@dingtalk.com</a>)<br/>
 * version: 1.0.0<br/>
 * since: 2021-04-29 17:13
 *
 * <p>
 * ViewModel：PDA 任务列表
 * </p>
 */
public abstract class VMPdaTask extends VMBaseRefreshList<ResPdaTask> {
    private final ObservableField<String> searchHint = new ObservableField<>();
    private final ReqTaskList reqTaskList = new ReqTaskList(PAGE_SIZE);

    public VMPdaTask(@NonNull Application application, BaseModel model) {
        super(application, model);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        searchHint.set("请扫描/输入任务号");
    }

    @Override
    public Call<ResultPage<ResPdaTask>> getCall(Map<String, Object> params) {
        return apiService.pdaTasks(params);
    }

    @Override
    public ReqPage getParams() {
        reqTaskList.setTaskType(getTaskType());
        reqTaskList.setJobStatus(Const.JOB_STATUS.UNFINISHED);
        reqTaskList.setWarehouseCode(spGetString(Const.SPKEY.WAREHOUSE_CODE));
        return reqTaskList;
    }

    /**
     * 获取任务类型
     */
    protected abstract String getTaskType();

    public ObservableField<String> getSearchHint() {
        return searchHint;
    }

    /**
     * 检索列表数据
     *
     * @param keyWord
     *         关键词
     */
    public void search(String keyWord) {
        reqTaskList.setCode(keyWord);
        refresh();
    }
}
