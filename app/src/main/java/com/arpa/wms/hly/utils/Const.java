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

    /**
     * 任务类型
     */
    interface TASK_TYPE {
        /**
         * 收货
         */
        String RECEIVE = "RECEIVE";
        /**
         * 上架
         */
        String PUTAWAY = "PUTAWAY";
        /**
         * 拣货
         */
        String PICKING = "PICKING";
        /**
         * 复核
         */
        String CHECK = "CHECK";
        /**
         * 盘点
         */
        String INVENTORY = "INVENTORY";
        /**
         * 移位
         */
        String MOVE = "MOVE";
    }

    /**
     * 作业状态
     */
    interface JOB_STATUS {
        /**
         * 待作业
         */
        String WAITING = "WAITING";
        /**
         * 作业中
         */
        String WORKING = "WORKING";
        /**
         * 作业完成
         */
        String COMPLETED = "COMPLETED";
        /**
         * 作业未完成
         */
        String UNFINISHED = "UNFINISHED";
    }

    /**
     * 首页菜单
     */
    interface HOME_MENU {
        /**
         * 任务中心
         */
        String TASK_CENTER = "arpa.wms.TASK_CENTER";
        /**
         * 收货
         */
        String GOODS_TAKE = "arpa.wms.GOODS_TAKE";
        /**
         * 拣货
         */
        String GOODS_PICK = "arpa.wms.GOODS_PICK";
        /**
         * 复核
         */
        String GOODS_RECHECK = "arpa.wms.GOODS_RECHECK";
        /**
         * 装车
         */
        String TRUCK_LOAD = "arpa.wms.TRUCK_LOAD";
        /**
         * 库存移位
         */
        String INVENTORY_MOVE = "arpa.wms.INVENTORY_MOVE";
        /**
         * 库存查询
         */
        String INVENTORY_QUERY = "arpa.wms.INVENTORY_QUERY";
    }
}
