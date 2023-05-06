package com.arpa.wms.hly.bean;

import java.io.Serializable;
import java.util.Objects;

/**
 * author: 李一方(<a href="mailto:leergo@dingtalk.com">leergo@dingtalk.com</a>)<br/>
 * version: 1.0.0<br/>
 * since: 2023-04-26 14:00
 */
public class SNCutRule extends SelectItem implements Serializable {
    // 编码
    private String code;

    // 规则名称
    private String ruleName;

    // 商品名称
    // private String goodsName;

    // 商品id
    private String goodsId;

    // 商品编码
    private String goodsCode;

    // 条码长度
    private Integer snLength;

    // 生产日期存在标识：0不存在，1存在
    private Integer productionDateFlag;

    // 生产日期开始
    private Integer productionDateStart;

    // 生产日期结束
    private Integer productionDateEnd;

    // 生产时间存在标识：0不存在，1存在
    private Integer productionTimeFlag;

    // 生产时间开始
    private Integer productionTimeStart;

    // 生产时间结束
    private Integer productionTimeEnd;

    // 产地存在标识：0不存在，1存在
    private Integer productionLocationFlag;

    // 产地开始
    private Integer productionLocationStart;

    // 产地结束
    private Integer productionLocationEnd;

    // 机台号存在标识：0不存在，1存在
    private Integer machineNumFlag;

    // 机台号开始
    private Integer machineNumStart;

    // 机台号结束
    private Integer machineNumEnd;

    // 过期日期存在标识：0不存在，1存在
    private Integer expirationDateFlag;

    // 过期日期开始
    private Integer expirationDateStart;

    // 过期日期结束
    private Integer expirationDateEnd;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SNCutRule)) return false;
        SNCutRule snCutRule = (SNCutRule) o;
        return code.equals(snCutRule.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getRuleName() {
        return ruleName;
    }

    public void setRuleName(String ruleName) {
        this.ruleName = ruleName;
    }

    // public String getGoodsName() {
    //     return goodsName;
    // }

    // public void setGoodsName(String goodsName) {
    //     this.goodsName = goodsName;
    // }

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

    public Integer getSnLength() {
        return snLength;
    }

    public void setSnLength(Integer snLength) {
        this.snLength = snLength;
    }

    public Integer getProductionDateFlag() {
        return productionDateFlag;
    }

    public void setProductionDateFlag(Integer productionDateFlag) {
        this.productionDateFlag = productionDateFlag;
    }

    public Integer getProductionDateStart() {
        return productionDateStart;
    }

    public void setProductionDateStart(Integer productionDateStart) {
        this.productionDateStart = productionDateStart;
    }

    public Integer getProductionDateEnd() {
        return productionDateEnd;
    }

    public void setProductionDateEnd(Integer productionDateEnd) {
        this.productionDateEnd = productionDateEnd;
    }

    public Integer getProductionTimeFlag() {
        return productionTimeFlag;
    }

    public void setProductionTimeFlag(Integer productionTimeFlag) {
        this.productionTimeFlag = productionTimeFlag;
    }

    public Integer getProductionTimeStart() {
        return productionTimeStart;
    }

    public void setProductionTimeStart(Integer productionTimeStart) {
        this.productionTimeStart = productionTimeStart;
    }

    public Integer getProductionTimeEnd() {
        return productionTimeEnd;
    }

    public void setProductionTimeEnd(Integer productionTimeEnd) {
        this.productionTimeEnd = productionTimeEnd;
    }

    public Integer getProductionLocationFlag() {
        return productionLocationFlag;
    }

    public void setProductionLocationFlag(Integer productionLocationFlag) {
        this.productionLocationFlag = productionLocationFlag;
    }

    public Integer getProductionLocationStart() {
        return productionLocationStart;
    }

    public void setProductionLocationStart(Integer productionLocationStart) {
        this.productionLocationStart = productionLocationStart;
    }

    public Integer getProductionLocationEnd() {
        return productionLocationEnd;
    }

    public void setProductionLocationEnd(Integer productionLocationEnd) {
        this.productionLocationEnd = productionLocationEnd;
    }

    public Integer getMachineNumFlag() {
        return machineNumFlag;
    }

    public void setMachineNumFlag(Integer machineNumFlag) {
        this.machineNumFlag = machineNumFlag;
    }

    public Integer getMachineNumStart() {
        return machineNumStart;
    }

    public void setMachineNumStart(Integer machineNumStart) {
        this.machineNumStart = machineNumStart;
    }

    public Integer getMachineNumEnd() {
        return machineNumEnd;
    }

    public void setMachineNumEnd(Integer machineNumEnd) {
        this.machineNumEnd = machineNumEnd;
    }

    public Integer getExpirationDateFlag() {
        return expirationDateFlag;
    }

    public void setExpirationDateFlag(Integer expirationDateFlag) {
        this.expirationDateFlag = expirationDateFlag;
    }

    public Integer getExpirationDateStart() {
        return expirationDateStart;
    }

    public void setExpirationDateStart(Integer expirationDateStart) {
        this.expirationDateStart = expirationDateStart;
    }

    public Integer getExpirationDateEnd() {
        return expirationDateEnd;
    }

    public void setExpirationDateEnd(Integer expirationDateEnd) {
        this.expirationDateEnd = expirationDateEnd;
    }
}
