package com.arpa.wms.hly.bean.req;

import com.arpa.wms.hly.bean.base.ReqBase;

/**
 * author: 李一方(<a href="mailto:leergo@dingtalk.com">leergo@dingtalk.com</a>)<br/>
 * version: 1.0.0<br/>
 * since: 2021-05-17 14:09
 */
public class ReqChangeWarehouse extends ReqBase {
    private String dataType;
    private String authorizeDataCode;

    public ReqChangeWarehouse(String authorizeDataCode) {
        this.dataType = "WAREHOUSE";
        this.authorizeDataCode = authorizeDataCode;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public String getAuthorizeDataCode() {
        return authorizeDataCode;
    }

    public void setAuthorizeDataCode(String authorizeDataCode) {
        this.authorizeDataCode = authorizeDataCode;
    }
}
