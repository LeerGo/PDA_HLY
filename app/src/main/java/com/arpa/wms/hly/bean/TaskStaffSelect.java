package com.arpa.wms.hly.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * author: 李一方(<a href="mailto:leergo@dingtalk.com">leergo@dingtalk.com</a>)<br/>
 * version: 1.0.0<br/>
 * since: 2021-06-15 14:10
 */
public class TaskStaffSelect {
    private List<TaskStaff> staffs;
    private TaskJobType jobType;

    public TaskStaffSelect() {
        staffs = new ArrayList<>();
    }

    public void addStaff(TaskStaff staff) {
        staffs.add(staff);
    }

    public void removeStaff(TaskStaff staff) {
        staffs.remove(staff);
    }

    public List<TaskStaff> getStaffs() {
        return staffs;
    }

    public void setStaffs(List<TaskStaff> staffs) {
        this.staffs = staffs;
    }

    public TaskJobType getJobType() {
        return jobType;
    }

    public void setJobType(TaskJobType jobType) {
        this.jobType = jobType;
    }
}
