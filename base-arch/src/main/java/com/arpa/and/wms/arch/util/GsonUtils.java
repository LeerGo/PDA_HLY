package com.arpa.and.wms.arch.util;

import com.google.gson.Gson;

import java.util.Map;

/**
 * author: 李一方(<a href="mailto:leergo@dingtalk.com">leergo@dingtalk.com</a>)<br/>
 * version: 1.0.0<br/>
 * since: 2021-04-27 3:11 PM
 *
 * <p>
 * 内容描述区域
 * </p>
 */
public class GsonUtils {
    private Gson gson;

    /**
     * 构造方法私有化
     */
    private GsonUtils() {
        gson = new Gson();
    }

    /**
     * 内部类方式获取单例
     */
    public static GsonUtils getInstance() {
        return SingleHolder.ins;
    }

    public Map pojo2Map(Object obj) {
        return gson.fromJson(gson.toJsonTree(obj), Map.class);
    }

    private static class SingleHolder {
        private static final GsonUtils ins = new GsonUtils();
    }
}
