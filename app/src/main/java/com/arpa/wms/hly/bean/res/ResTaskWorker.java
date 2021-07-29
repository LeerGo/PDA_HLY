package com.arpa.wms.hly.bean.res;

import com.arpa.wms.hly.bean.TaskJobType;
import com.arpa.wms.hly.bean.TaskStaff;

import java.util.List;

/**
 * author: 李一方(<a href="mailto:leergo@dingtalk.com">leergo@dingtalk.com</a>)<br/>
 * version: 1.0.0<br/>
 * since: 2021-06-15 13:47
 */
public class ResTaskWorker {
    private List<TaskStaff> staff;
    private List<TaskJobType> jobType;

    public List<TaskStaff> getStaff() {
        return staff;
    }

    public void setStaff(List<TaskStaff> staff) {
        this.staff = staff;
    }

    public List<TaskJobType> getJobType() {
        return jobType;
    }

    public void setJobType(List<TaskJobType> jobType) {
        this.jobType = jobType;
    }
}
