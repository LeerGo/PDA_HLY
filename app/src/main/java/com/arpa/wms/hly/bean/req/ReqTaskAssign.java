package com.arpa.wms.hly.bean.req;

import com.arpa.wms.hly.bean.PartyCodeList;

import java.util.ArrayList;
import java.util.List;

/**
 * author: 李一方(<a href="mailto:leergo@dingtalk.com">leergo@dingtalk.com</a>)<br/>
 * version: 1.0.0<br/>
 * since: 2021-05-17 14:34
 */
public class ReqTaskAssign {
    // 任务 ID 列表
    private List<String> orderCodes;
    // 人员分配类型
    private List<PartyCodeList> partyCodeList;
    // 作业类型
    private String workType;
    // 工种类型
    // custodian：保管员     stevedore：装卸工     forklift：叉车工
    private String workerType;

    public ReqTaskAssign() {
        this.orderCodes = new ArrayList<>();
        this.partyCodeList = new ArrayList<>();
    }

    public String getWorkerType() {
        return workerType;
    }

    public void setWorkerType(String workerType) {
        this.workerType = workerType;
    }

    public List<String> getOrderCodes() {
        return orderCodes;
    }

    public void setOrderCodes(List<String> orderCodes) {
        this.orderCodes = orderCodes;
    }

    public List<PartyCodeList> getPartyCodeList() {
        return partyCodeList;
    }

    public void setPartyCodeList(List<PartyCodeList> partyCodeList) {
        this.partyCodeList = partyCodeList;
    }

    public String getWorkType() {
        return workType;
    }

    public void setWorkType(String workType) {
        this.workType = workType;
    }
}
