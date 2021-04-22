package com.arpa.wms.hly.logic.home.inventory.move;

/**
 * author: 李一方(<a href="mailto:leergo@dingtalk.com">leergo@dingtalk.com</a>)<br/>
 * version: 1.0.0<br/>
 * since: 2021-04-22 3:30 PM
 *
 * <p>
 * 内容描述区域
 * </p>
 */
public class ScanGoodsActivity extends InventoryScanActivity {

    @Override
    protected void requestData(String scanCode) {

    }

    @Override
    protected String getTitleBar() {
        return "扫描移位商品";
    }
}
