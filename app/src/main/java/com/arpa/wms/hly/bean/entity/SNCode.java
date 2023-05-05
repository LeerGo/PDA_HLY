package com.arpa.wms.hly.bean.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import com.arpa.wms.hly.bean.SNCutRule;

/**
 * author: 李一方(<a href="mailto:leergo@dingtalk.com">leergo@dingtalk.com</a>)<br/>
 * version: 1.0.0<br/>
 * since: 2023-04-26 15:17
 */
@Entity(indices = {@Index(value = "taskCode")})
public class SNCode {
    @PrimaryKey(autoGenerate = true)
    private long id;
    private String taskCode; // 任务 ID
    private String taskItemCode; // 任务明细 ID
    private String goodsId; // 商品 ID
    private String goodsCode; // 商品编码
    private String snCode; // 原批次号码
    private String productionDate; // 生产日期
    private String productionTime; // 生产时间
    private String productionLocation; // 产地
    private String machineNum; // 机台号
    private String expirationDate; // 过期日期
    @ColumnInfo(defaultValue = "1")
    private Integer scanRatio; // 换箱比例

    public SNCode() {
    }

    /*public SNCode(String taskCode, String taskItemCode) {
        this.taskCode = taskCode;
        this.taskItemCode = taskItemCode;
    }*/

    public void convertRule(SNCutRule rule, String snCode) {
        setSnCode(snCode);
        setGoodsCode(rule.getGoodsCode());
        setGoodsId(rule.getGoodsId());
        if (1 == rule.getMachineNumFlag()) {
            setMachineNum(snCode.substring(rule.getMachineNumStart() - 1, rule.getMachineNumEnd()));
        }
        if (1 == rule.getExpirationDateFlag()) {
            setExpirationDate(snCode.substring(rule.getExpirationDateStart() - 1, rule.getExpirationDateEnd()));
        }
        if (1 == rule.getProductionDateFlag()) {
            setProductionDate(snCode.substring(rule.getProductionDateStart() - 1, rule.getProductionDateEnd()));
        }
        if (1 == rule.getProductionTimeFlag()) {
            setProductionTime(snCode.substring(rule.getProductionTimeStart() - 1, rule.getProductionTimeEnd()));
        }
        if (1 == rule.getProductionLocationFlag()) {
            setProductionLocation(snCode.substring(rule.getProductionLocationStart() - 1, rule.getProductionLocationEnd()));
        }
    }

    /**
     * 校验序列号有效性，如：保质期必须晚于生产日期、日期必须是八位
     */
    public boolean valid() {
        // TODO: 待实现 add by 李一方 2023-04-26 16:15:12
        return true;
    }

    /**
     * 校验序列号之后，返回校验提示
     */
    public String tips() {
        // TODO: 待实现 add by 李一方 2023-04-26 16:16:02
        return null;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTaskCode() {
        return taskCode;
    }

    public void setTaskCode(String taskCode) {
        this.taskCode = taskCode;
    }

    public String getTaskItemCode() {
        return taskItemCode;
    }

    public void setTaskItemCode(String taskItemCode) {
        this.taskItemCode = taskItemCode;
    }

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    public String getGoodsCode() {
        return goodsCode;
    }

    public void setGoodsCode(String goodsCode) {
        this.goodsCode = goodsCode;
    }

    public String getSnCode() {
        return snCode;
    }

    public void setSnCode(String snCode) {
        this.snCode = snCode;
    }

    public String getProductionDate() {
        return productionDate;
    }

    public void setProductionDate(String productionDate) {
        this.productionDate = productionDate;
    }

    public String getProductionTime() {
        return productionTime;
    }

    public void setProductionTime(String productionTime) {
        this.productionTime = productionTime;
    }

    public String getProductionLocation() {
        return productionLocation;
    }

    public void setProductionLocation(String productionLocation) {
        this.productionLocation = productionLocation;
    }

    public String getMachineNum() {
        return machineNum;
    }

    public void setMachineNum(String machineNum) {
        this.machineNum = machineNum;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    public Integer getScanRatio() {
        return scanRatio;
    }

    public void setScanRatio(Integer scanRatio) {
        this.scanRatio = scanRatio;
    }

    @Override
    public String toString() {
        return "SNCode{" +
                "id=" + id +
                ", taskCode='" + taskCode + '\'' +
                ", taskItemCode='" + taskItemCode + '\'' +
                ", goodsId='" + goodsId + '\'' +
                ", goodsCode='" + goodsCode + '\'' +
                ", snCode='" + snCode + '\'' +
                ", productionDate='" + productionDate + '\'' +
                ", productionTime='" + productionTime + '\'' +
                ", productionLocation='" + productionLocation + '\'' +
                ", machineNum='" + machineNum + '\'' +
                ", expirationDate='" + expirationDate + '\'' +
                ", scanRatio=" + scanRatio +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SNCode)) return false;

        SNCode code = (SNCode) o;

        if (!taskCode.equals(code.taskCode)) return false;
        if (!taskItemCode.equals(code.taskItemCode)) return false;
        return snCode.equals(code.snCode);
    }

    @Override
    public int hashCode() {
        int result = taskCode.hashCode();
        result = 31 * result + taskItemCode.hashCode();
        result = 31 * result + snCode.hashCode();
        return result;
    }
}
