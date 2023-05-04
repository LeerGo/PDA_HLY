package com.arpa.wms.hly.bean.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;

import com.arpa.wms.hly.bean.RecheckItemVO;

import java.math.BigDecimal;

/**
 * author: 李一方(<a href="mailto:leergo@dingtalk.com">leergo@dingtalk.com</a>)<br/>
 * version: 1.0.0<br/>
 * since: 2023-04-18 15:02
 */
@Entity(indices = @Index(value = "taskCode"), primaryKeys = {"taskCode", "itemCode"})
public class TaskItemEntity {
    // 主任务号
    @NonNull
    private String taskCode;
    // 任务明细号
    @NonNull
    private String itemCode;
    // 扫码率
    @ColumnInfo(defaultValue = "0")
    private BigDecimal radio;
    // 扫码比例
    @ColumnInfo(defaultValue = "1")
    private Integer scanRatio;

    public String getTaskCode() {
        return taskCode;
    }

    public void setTaskCode(String taskCode) {
        this.taskCode = taskCode;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public BigDecimal getRadio() {
        return radio;
    }

    public void setRadio(BigDecimal radio) {
        this.radio = radio;
    }

    public Integer getScanRatio() {
        return scanRatio;
    }

    public void setScanRatio(Integer scanRatio) {
        this.scanRatio = scanRatio;
    }

    public void convert(RecheckItemVO data) {
        setRadio(data.getRadio());
        setItemCode(data.getCode());
        setTaskCode(data.getOutboundCode());
        setScanRatio(data.getScanRatio());
    }
}
