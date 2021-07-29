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
 * 请求：库存
 * </p>
 */
public class ReqInventory extends ReqPage {
    private final String warehouseCode;
    private String locationName;
    private String goodsBarCode;

    public ReqInventory(Integer pageSize) {
        super(pageSize);

        warehouseCode = SPUtils.getInstance().getString(SPKEY.WAREHOUSE_CODE);
    }

    public String getWarehouseCode() {
        return warehouseCode;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public String getGoodsBarCode() {
        return goodsBarCode;
    }

    public void setGoodsBarCode(String goodsBarCode) {
        this.goodsBarCode = goodsBarCode;
    }
}
