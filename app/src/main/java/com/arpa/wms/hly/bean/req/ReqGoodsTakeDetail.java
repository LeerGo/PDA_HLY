package com.arpa.wms.hly.bean.req;

import com.arpa.wms.hly.bean.base.ReqPage;
import com.arpa.wms.hly.utils.Const.SPKEY;
import com.arpa.wms.hly.utils.SPUtils;

/**
 * author: 李一方(<a href="mailto:leergo@dingtalk.com">leergo@dingtalk.com</a>)<br/>
 * version: 1.0.0<br/>
 * since: 2021-05-06 15:15
 *
 * <p>
 * 内容描述区域
 * </p>
 */
public class ReqGoodsTakeDetail extends ReqPage {
    private final String warehouseCode;
    private final String operatorBy;
    private String code;
    private String receiveStatus;
    private String goodsBarCode;

    public ReqGoodsTakeDetail(Integer pageSize) {
        super(pageSize);

        operatorBy = SPUtils.getInstance().getString(SPKEY.OPERATOR_CODE);
        warehouseCode = SPUtils.getInstance().getString(SPKEY.WAREHOUSE_CODE);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getReceiveStatus() {
        return receiveStatus;
    }

    public void setReceiveStatus(String receiveStatus) {
        this.receiveStatus = receiveStatus;
    }

    public String getGoodsBarCode() {
        return goodsBarCode;
    }

    public void setGoodsBarCode(String goodsBarCode) {
        this.goodsBarCode = goodsBarCode;
    }

    public String getOperatorBy() {
        return operatorBy;
    }

    public String getWarehouseCode() {
        return warehouseCode;
    }
}
