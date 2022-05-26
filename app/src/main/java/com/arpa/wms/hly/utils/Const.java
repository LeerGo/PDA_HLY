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
     * 日志 TAG
     */
    String LOG_TAG = "Arpa-Logger";

    /**
     * Bugly ID
     */
    String BUGLY_ID = "3c20ee1d4c";

    /**
     * 全局 Header 常量
     */
    interface Header {
        /**
         * SSO 令牌
         */
        String TOKEN = "token";
        /**
         * 登录渠道：android = 2，单点登录使用：9
         */
        String SOURCE_ID = "source-id";
        String SOURCE_ANDROID = "9";
        /**
         * 设备 ID
         */
        String EQUIPMENT_CODE = "equipment-code";
    }

    /**
     * Intent 常量
     */
    interface IntentKey {
        String LOT = "lot";
        String DATA = "data";
        String CODE = "code";
        String INDEX = "index";
        String STATUS = "status";
        String GOODS_BAR_CODE = "goodsBarCode";
        String CONTAINER_CODE = "containerCode";
        String LOCATION_NAME = "locationName";
        String WAREHOUSE_CODE = "warehouseCode";
        String SOURCE_CODE = "sourceCode";
        String OUTBOUND_CODE = "outboundCode";
        String OUTBOUND_ITEM_CODE = "outboundItemCode";
        String RECEIVE_CODE = "receiveCode";
        String RECEIVE_ITEM_CODE = "receiveItemCode";
        String GOODS_NAME = "goodName";
        String GOODS_UNIT_NAME = "goodUnitName";
        String GOODS_COUNT = "goodsCount";
        String SUPPLIER = "supplier";
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
        /**
         * 装车
         */
        String LOADING = "LOADING";
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
     * SharedPreferences KEY
     */
    interface SPKEY {
        // 对应 wms2 里 CANGKU
        String WAREHOUSE_CODE = "warehouseCode";
        // 对应 wms2 里 cangName
        String WAREHOUSE_NAME = "warehouseName";
        // 是否为新用户，登陆后 false
        String IS_NEW_USER = "isNewUser";
        // 登录账号
        String USER_NAME = "userName";
        // 登录token
        String TOKEN_SSO = "tokenSSO";
        // 服务 IP
        String TEST_SERVER = "test_server";
    }

    /**
     * 任务状态
     */
    interface TASK_STATUS {
        /**
         * 收货 - 未收货
         */
        int TAKE_WAIT = 0;
        /**
         * 收货 - 已收货
         */
        int TAKE_YET = 2;
        /**
         * 复核 - 未复核
         */
        int RECHECK_WAIT = 0;
        /**
         * 复核 - 已复核
         */
        int RECHECK_YET = 2;
    }

    interface ASSIGN_WORK {
        // 指派状态：0未指派，1已指派，2指派中
        int ASSIGN_NOT = 0;
        int ASSIGN_YET = 1;
        int ASSIGN_IN = 2;

        // 保管员
        int CUSTODIAN = 1;
        // 叉车工
        int FORKLIFT = 2;
        // 装卸工
        int STEVEDORE = 3;

        String[] WORKER_TYPE = new String[]{null, "CUSTODIAN", "FORKLIFT", "STEVEDORE"};
        String[] WORKER_TYPE_NAME = new String[]{null, "保管员", "叉车工", "装卸工"};

        String GOODS_TAKE = "收货";
        String GOODS_PICK = "拣货";
        String GOODS_RECHECK = "复核";
        String GOODS_LOADING = "装车";
    }

    interface DateType {
        int gmtManufacture = 0; // 生产日期
        int gmtExpire = 1;// 过期日期
        int gmtStock = 2;//存货日期
        int extendFive = 3; // 拓展 5
        int extendSix = 4; // 拓展 6
    }

    interface Message {
        int MSG_DIALOG = 0x1;
    }
}
