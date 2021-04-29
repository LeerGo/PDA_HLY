package com.arpa.and.wms.arch.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

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
    private final Gson gson;

    /**
     * 构造方法私有化
     */
    private GsonUtils() {
        gson = new GsonBuilder()
                .registerTypeAdapter(new TypeToken<Map<String, Object>>() {
                }.getType(), new MapDeserializerDoubleAsIntFix())
                .create();
    }

    /**
     * 内部类方式获取单例
     */
    public static GsonUtils getInstance() {
        return SingleHolder.ins;
    }

    public Map<String, Object> pojo2Map(Object obj) {
        return gson.fromJson(gson.toJsonTree(obj), new TypeToken<Map<String, Object>>() {}.getType());
    }

    private static class SingleHolder {
        private static final GsonUtils ins = new GsonUtils();
    }
}

