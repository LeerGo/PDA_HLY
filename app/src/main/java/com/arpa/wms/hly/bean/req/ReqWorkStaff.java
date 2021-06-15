package com.arpa.wms.hly.bean.req;

import com.arpa.wms.hly.bean.base.ReqBase;
import com.arpa.wms.hly.utils.SPUtils;

import static com.arpa.wms.hly.utils.Const.SPKEY.WAREHOUSE_CODE;

/**
 * author: 李一方(<a href="mailto:leergo@dingtalk.com">leergo@dingtalk.com</a>)<br/>
 * version: 1.0.0<br/>
 * since: 2021-06-15 13:33
 */
public class ReqWorkStaff extends ReqBase {
    private int type;
    private String warehouseCode;

    public ReqWorkStaff(int type) {
        this.type = type;
        this.warehouseCode = SPUtils.getInstance().getString(WAREHOUSE_CODE);
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getWarehouseCode() {
        return warehouseCode;
    }

    public void setWarehouseCode(String warehouseCode) {
        this.warehouseCode = warehouseCode;
    }
}
