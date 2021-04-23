package com.arpa.wms.hly.aop;

/**
 * @author : Jarry Leo
 */
public class SingleClickManager {
    static int clickInterval = 500;

    private SingleClickManager() {
    }

    /**
     * 设置全局点击事件防重间隔
     *
     * @param clickIntervalMillis
     *         间隔毫秒值
     */
    public static void setClickInterval(int clickIntervalMillis) {
        clickInterval = clickIntervalMillis;
    }

}