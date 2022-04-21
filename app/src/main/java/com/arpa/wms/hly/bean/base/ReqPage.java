package com.arpa.wms.hly.bean.base;

/**
 * author: 李一方(<a href="mailto:leergo@dingtalk.com">leergo@dingtalk.com</a>)<br/>
 * version: 1.0.0<br/>
 * since: 2021-04-29 09:25
 *
 * <p>
 * 内容描述区域
 * </p>
 */
public class ReqPage extends ReqBase {
    public int pageNum;
    public int pageSize;
    public String queryValue;

    public ReqPage(int pageSize) {
        this.pageNum = 1;
        this.pageSize = pageSize;
    }

    public String getQueryValue() {
        return queryValue;
    }

    public void setQueryValue(String queryValue) {
        this.queryValue = queryValue;
    }

    public void pageIncrease() {
        pageNum++;
    }

    public void pageReset() {
        pageNum = 1;
    }
}
