package com.arpa.wms.hly.bean.req;

import com.arpa.wms.hly.bean.base.ReqPage;
import com.arpa.wms.hly.utils.Const;
import com.arpa.wms.hly.utils.SPUtils;

/**
 * author: 李一方(<a href="mailto:leergo@dingtalk.com">leergo@dingtalk.com</a>)<br/>
 * version: 1.0.0<br/>
 * since: 2023-02-02 13:49
 *
 * <p>
 *
 * </p>
 */
public class ReqLocSearch extends ReqPage {
    private String warehouseCode;
    private String serialNumber;

    public ReqLocSearch(int pageSize) {
        super(pageSize);
        this.warehouseCode = SPUtils.getInstance().getString(Const.SPKEY.WAREHOUSE_CODE);
    }

    public String getWarehouseCode() {
        return warehouseCode;
    }

    public void setWarehouseCode(String warehouseCode) {
        this.warehouseCode = warehouseCode;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }
}
