package com.arpa.wms.hly.logic.home.goods.recheck.vm;

import android.app.Application;

import androidx.annotation.NonNull;

import com.arpa.and.arch.base.BaseModel;
import com.arpa.wms.hly.bean.GoodsItemVO;
import com.arpa.wms.hly.bean.RecheckItemVO;
import com.arpa.wms.hly.bean.SNCutRule;
import com.arpa.wms.hly.bean.req.ReqSNRule;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

/**
 * author: 李一方(<a href="mailto:leergo@dingtalk.com">leergo@dingtalk.com</a>)<br/>
 * version: 1.0.0<br/>
 * since: 2023-04-26 13:48
 *
 * <p>
 * vm: 详情界面扫码
 * </p>
 */
@HiltViewModel
public class VMSerialDetail extends AbsVMSerial {
    protected List<RecheckItemVO> items;

    @Inject
    public VMSerialDetail(@NonNull Application application, BaseModel model) {
        super(application, model);
    }

    @Override
    protected void handleMultiRule(List<SNCutRule> ruleGroup) {
        sendMessage("无法匹配唯一规则，请进入批次登记页面扫码");
    }

    @Override
    protected Integer obtainScanRadio(SNCutRule rule) {
        return items.stream()
                .filter(it -> it.getGoodCode().equals(rule.getGoodsCode()))
                .findFirst()
                .map(RecheckItemVO::getScanRatio)
                .orElse(1);
    }

    @Override
    protected String obtainItemCode(SNCutRule rule) {
        return items.stream()
                .filter(it -> it.getGoodCode().equals(rule.getGoodsCode()))
                .findFirst()
                .map(GoodsItemVO::getCode)
                .orElse(null);
    }

    @Override
    protected String obtainTarget(String snCode) {
        return snCode.substring(snCode.length() - 3);
    }

    @Override
    protected void handleReq(ReqSNRule reqSNRule, String target) {
        reqSNRule.setGoodsId(target);
    }

    public void setItems(List<RecheckItemVO> items) {
        this.items = items;
    }
}
