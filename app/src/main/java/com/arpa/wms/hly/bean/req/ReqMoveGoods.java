package com.arpa.wms.hly.bean.req;

import android.os.Parcel;
import android.os.Parcelable;

import com.arpa.wms.hly.bean.base.ReqBase;
import com.arpa.wms.hly.utils.Const;
import com.arpa.wms.hly.utils.SPUtils;

/**
 * author: 李一方(<a href="mailto:leergo@dingtalk.com">leergo@dingtalk.com</a>)<br/>
 * version: 1.0.0<br/>
 * since: 2021-04-23 2:40 PM
 *
 * <p>
 * 请求：位移商品
 * </p>
 */
public class ReqMoveGoods extends ReqBase implements Parcelable {
    public static final Parcelable.Creator<ReqMoveGoods> CREATOR = new Parcelable.Creator<ReqMoveGoods>() {
        @Override
        public ReqMoveGoods createFromParcel(Parcel source) {
            return new ReqMoveGoods(source);
        }

        @Override
        public ReqMoveGoods[] newArray(int size) {
            return new ReqMoveGoods[size];
        }
    };
    public String warehouseCode;
    private String lot;
    private String code;
    private String location;
    private String goodsBarCode;
    private String containerBarCode;
    private String moveLocation;
    private String moveQuantity;

    public ReqMoveGoods() {
        warehouseCode = SPUtils.getInstance().getString(Const.SPKEY.WAREHOUSE_CODE);
    }

    protected ReqMoveGoods(Parcel in) {
        this.warehouseCode = in.readString();
        this.lot = in.readString();
        this.code = in.readString();
        this.location = in.readString();
        this.goodsBarCode = in.readString();
        this.containerBarCode = in.readString();
    }

    public String getLot() {
        return lot;
    }

    public void setLot(String lot) {
        this.lot = lot;
    }


    public String getMoveLocation() {
        return moveLocation;
    }

    public void setMoveLocation(String moveLocation) {
        this.moveLocation = moveLocation;
    }

    public String getMoveQuantity() {
        return moveQuantity;
    }

    public void setMoveQuantity(String moveQuantity) {
        this.moveQuantity = moveQuantity;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getContainerBarCode() {
        return containerBarCode;
    }

    public void setContainerBarCode(String containerBarCode) {
        this.containerBarCode = containerBarCode;
    }

    public String getGoodsBarCode() {
        return goodsBarCode;
    }

    public void setGoodsBarCode(String goodsBarCode) {
        this.goodsBarCode = goodsBarCode;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.warehouseCode);
        dest.writeString(this.lot);
        dest.writeString(this.code);
        dest.writeString(this.location);
        dest.writeString(this.goodsBarCode);
        dest.writeString(this.containerBarCode);
    }

    public void readFromParcel(Parcel source) {
        this.warehouseCode = source.readString();
        this.lot = source.readString();
        this.code = source.readString();
        this.location = source.readString();
        this.goodsBarCode = source.readString();
        this.containerBarCode = source.readString();
    }
}
