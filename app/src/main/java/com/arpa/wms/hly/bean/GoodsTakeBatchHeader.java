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
public class GoodsTakeBatchHeader {
    private String carNumber;

    public GoodsTakeBatchHeader() {
        this.carNumber = "TextCarNum";
    }

    @Override
    public String toString() {
        return "GoodsTakeBatchHeader{" +
                "carNumber='" + carNumber + '\'' +
                '}';
    }

    public String getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }
}
