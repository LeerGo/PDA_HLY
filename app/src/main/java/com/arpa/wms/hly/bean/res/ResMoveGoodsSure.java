package com.arpa.wms.hly.bean.res;

import com.arpa.wms.hly.bean.GoodsItemVO;

import java.util.List;

public class ResMoveGoodsSure {
    private String goodsName;
    private String goodsUnit;
    private String locationName;
    private List<GoodsItemVO> inventoryList;

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getGoodsUnit() {
        return goodsUnit;
    }

    public void setGoodsUnit(String goodsUnit) {
        this.goodsUnit = goodsUnit;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public List<GoodsItemVO> getInventoryList() {
        return inventoryList;
    }

    public void setInventoryList(List<GoodsItemVO> inventoryList) {
        this.inventoryList = inventoryList;
    }
}