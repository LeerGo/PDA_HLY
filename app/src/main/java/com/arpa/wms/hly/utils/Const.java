package com.arpa.wms.hly.utils;

/**
 * author: 李一方(<a href="mailto:leergo@dingtalk.com">leergo@dingtalk.com</a>)<br/>
 * version: 1.0.0<br/>
 * since: 2021-04-12 2:28 PM
 *
 * <p>
 * 全局常量
 * </p>
 */
public interface Const {

    /**
     * Intent 常量
     */
    interface IntentKey {
        String CODE = "CODE";
        String CONTAINER_CODE = "containerCode";
        String WAREHOUSE_CODE = "warehouseCode";
        String PICK_RECHECK_TYPE = "orderTag";
    }

    interface TASK_TYPE {
        // 收货
        String RECEIVE = "RECEIVE";
        // 上架
        String PUTAWAY = "PUTAWAY";
        // 拣货
        String PICKING = "PICKING";
        // 复核
        String CHECK = "CHECK";
        // 盘点
        String INVENTORY = "INVENTORY";
        // 移位
        String MOVE = "MOVE";
    }

    interface JOB_STATUS {
        // 待作业
        String WAITING = "WAITING";
        // 作业中
        String WORKING = "WORKING";
        // 作业完成
        String COMPLETED = "COMPLETED";
        // 作业未完成
        String UNFINISHED = "UNFINISHED";
    }
}
