package com.arpa.wms.hly.bean.req;

import com.arpa.wms.hly.BR;
import com.arpa.wms.hly.bean.GoodsItemVO;

import java.util.ArrayList;
import java.util.List;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

/**
 * author: 李一方(<a href="mailto:leergo@dingtalk.com">leergo@dingtalk.com</a>)<br/>
 * version: 1.0.0<br/>
 * since: 2021-05-20 14:32
 */
public class ReqGoodTakeConfirm extends BaseObservable {
    private String code;
    private String receiveCode;
    private List<GoodsItemVO> receiveItemWithRegisterVOList;
    private Integer extendThree; // 扫码比例
    private Integer scan; // 是否扫码 0：不扫码  1：扫码
    private String remarks; // 备注

    public ReqGoodTakeConfirm() {
        this.receiveItemWithRegisterVOList = new ArrayList<>();
    }

    public String getReceiveCode() {
        return receiveCode;
    }

    public void setReceiveCode(String receiveCode) {
        this.receiveCode = receiveCode;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<GoodsItemVO> getReceiveItemWithRegisterVOList() {
        return receiveItemWithRegisterVOList;
    }

    public void setReceiveItemWithRegisterVOList(List<GoodsItemVO> receiveItemWithRegisterVOList) {
        this.receiveItemWithRegisterVOList = receiveItemWithRegisterVOList;
    }

    @Bindable
    public Integer getExtendThree() {
        return extendThree;
    }

    public void setExtendThree(Integer extendThree) {
        this.extendThree = extendThree;
        notifyPropertyChanged(BR.extendThree);
    }

    @Bindable
    public Integer getScan() {
        return scan;
    }

    public void setScan(Integer scan) {
        this.scan = scan;
        notifyPropertyChanged(BR.scan);
    }

    @Bindable
    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
        notifyPropertyChanged(BR.remarks);
    }
}
