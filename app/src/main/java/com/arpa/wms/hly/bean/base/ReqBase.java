package com.arpa.wms.hly.bean.base;

import com.arpa.and.wms.arch.util.GsonUtils;

import java.util.Map;

/**
 * author: 李一方(<a href="mailto:leergo@dingtalk.com">leergo@dingtalk.com</a>)<br/>
 * version: 1.0.0<br/>
 * since: 2021-05-11 10:05
 *
 * <p>
 * 内容描述区域
 * </p>
 */
public class ReqBase {
    public Map<String, Object> toParams() {
        return GsonUtils.getInstance().pojo2Map(this);
    }
}
