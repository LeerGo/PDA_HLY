package com.arpa.wms.hly.bean;

/**
 * author: 李一方(<a href="mailto:leergo@dingtalk.com">leergo@dingtalk.com</a>)<br/>
 * version: 1.0.0<br/>
 * since: 2021-05-13 14:25
 *
 * <p>
 * 内容描述区域
 * </p>
 */
public class GoodsTakeBatchItem {
    private String receivedState;
    private String receivedCount;
    private String productDate;

    @Override
    public String toString() {
        return "GoodsTakeBatchItem{" +
                "receivedState='" + receivedState + '\'' +
                ", receivedCount='" + receivedCount + '\'' +
                ", productDate='" + productDate + '\'' +
                '}';
    }

    public String getReceivedState() {
        return receivedState;
    }

    public void setReceivedState(String receivedState) {
        this.receivedState = receivedState;
    }

    public String getReceivedCount() {
        return receivedCount;
    }

    public void setReceivedCount(String receivedCount) {
        this.receivedCount = receivedCount;
    }

    public String getProductDate() {
        return productDate;
    }

    public void setProductDate(String productDate) {
        this.productDate = productDate;
    }
}
