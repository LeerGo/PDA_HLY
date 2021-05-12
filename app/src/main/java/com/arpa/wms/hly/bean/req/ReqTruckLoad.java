package com.arpa.wms.hly.bean.req;

import com.arpa.wms.hly.bean.base.ReqPage;

/**
 * author: 李一方(<a href="mailto:leergo@dingtalk.com">leergo@dingtalk.com</a>)<br/>
 * version: 1.0.0<br/>
 * since: 2021-04-23 2:40 PM
 *
 * <p>
 * 请求：获取首页不同任务类型的列表
 * </p>
 */
public class ReqTruckLoad extends ReqPage {

    public ReqTruckLoad(Integer pageSize) {
        super(pageSize);
    }
}
