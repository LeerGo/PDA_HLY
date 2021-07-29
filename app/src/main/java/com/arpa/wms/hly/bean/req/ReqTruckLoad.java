package com.arpa.wms.hly.bean.req;

import com.arpa.wms.hly.bean.base.ReqPage;
import com.arpa.wms.hly.utils.SPUtils;

import static com.arpa.wms.hly.utils.Const.IntentKey.WAREHOUSE_CODE;
import static com.arpa.wms.hly.utils.Const.JOB_STATUS.UNFINISHED;
import static com.arpa.wms.hly.utils.Const.TASK_TYPE.LOADING;

/**
 * author: 李一方(<a href="mailto:leergo@dingtalk.com">leergo@dingtalk.com</a>)<br/>
 * version: 1.0.0<br/>
 * since: 2021-04-23 2:40 PM
 *
 * <p>
 * 请求：装车任务列表
 * </p>
 */
public class ReqTruckLoad extends ReqPage {
    private String taskType;
    private String jobStatus;
    private String warehouseCode;

    public ReqTruckLoad(Integer pageSize) {
        super(pageSize);

        warehouseCode = SPUtils.getInstance().getString(WAREHOUSE_CODE);
        taskType = LOADING;
        jobStatus = UNFINISHED;
    }

    public String getTaskType() {
        return taskType;
    }

    public void setTaskType(String taskType) {
        this.taskType = taskType;
    }

    public String getJobStatus() {
        return jobStatus;
    }

    public void setJobStatus(String jobStatus) {
        this.jobStatus = jobStatus;
    }

    public String getWarehouseCode() {
        return warehouseCode;
    }

    public void setWarehouseCode(String warehouseCode) {
        this.warehouseCode = warehouseCode;
    }
}
