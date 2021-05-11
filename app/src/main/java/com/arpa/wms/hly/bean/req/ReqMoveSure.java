package com.arpa.wms.hly.bean.req;

import com.arpa.wms.hly.bean.base.ReqBase;
import com.arpa.wms.hly.utils.Const.SPKEY;
import com.arpa.wms.hly.utils.SPUtils;

/**
 * author: 李一方(<a href="mailto:leergo@dingtalk.com">leergo@dingtalk.com</a>)<br/>
 * version: 1.0.0<br/>
 * since: 2021-04-23 2:40 PM
 *
 * <p>
 * 请求：登录
 * </p>
 */
public class ReqMoveSure extends ReqBase {
    public String warehouseCode;

    private String code;
    private String moveQuantity;
    private String containerBarCode;
    private String moveLocation;

    public ReqMoveSure() {
        warehouseCode = SPUtils.getInstance().getString(SPKEY.WAREHOUSE_CODE);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMoveQuantity() {
        return moveQuantity;
    }

    public void setMoveQuantity(String moveQuantity) {
        this.moveQuantity = moveQuantity;
    }

    public String getContainerBarCode() {
        return containerBarCode;
    }

    public void setContainerBarCode(String containerBarCode) {
        this.containerBarCode = containerBarCode;
    }

    public String getMoveLocation() {
        return moveLocation;
    }

    public void setMoveLocation(String moveLocation) {
        this.moveLocation = moveLocation;
    }
}
