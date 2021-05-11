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
public class ReqPage extends ReqBase{
    public Integer pageNum;
    public Integer pageSize;

    public ReqPage(Integer pageSize) {
        this.pageNum = 1;
        this.pageSize = pageSize;
    }


    public void pageIncrease() {
        pageNum++;
    }

    public void pageReset() {
        pageNum = 1;
    }
}
