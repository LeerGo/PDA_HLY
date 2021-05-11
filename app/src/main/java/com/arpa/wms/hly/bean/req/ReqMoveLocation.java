package com.arpa.wms.hly.bean.req;

import com.arpa.wms.hly.bean.base.ReqBase;
import com.arpa.wms.hly.utils.Const;
import com.arpa.wms.hly.utils.SPUtils;

/**
 * author: 李一方(<a href="mailto:leergo@dingtalk.com">leergo@dingtalk.com</a>)<br/>
 * version: 1.0.0<br/>
 * since: 2021-04-23 2:40 PM
 *
 * <p>
 * 请求：位移库位
 * </p>
 */
public class ReqMoveLocation extends ReqBase {
    public String warehouseCode;
    private String location;

    public ReqMoveLocation(String location) {
        this.location = location;
        warehouseCode = SPUtils.getInstance().getString(Const.SPKEY.WAREHOUSE_CODE);
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
