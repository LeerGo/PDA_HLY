package com.arpa.wms.hly.bean.req;

import com.arpa.wms.hly.bean.base.ReqPage;
import com.arpa.wms.hly.utils.SPUtils;

import static com.arpa.wms.hly.utils.Const.IntentKey.WAREHOUSE_CODE;

/**
 * author: 李一方(<a href="mailto:leergo@dingtalk.com">leergo@dingtalk.com</a>)<br/>
 * version: 1.0.0<br/>
 * since: 2021-04-23 2:40 PM
 *
 * <p>
 * 请求：获取首页不同任务类型的列表(任务中心、装车还在用）
 * </p>
 */
public class ReqTaskList extends ReqPage {
    private String taskType;
    private String jobStatus;
    private String warehouseCode;
    private String code;
    private int assign;//指派状态：0未指派，1已指派，2指派中

    public ReqTaskList(int pageSize) {
        super(pageSize);
        warehouseCode = SPUtils.getInstance().getString(WAREHOUSE_CODE);
    }

    public int getAssign() {
        return assign;
    }

    public void setAssign(int assign) {
        this.assign = assign;
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
