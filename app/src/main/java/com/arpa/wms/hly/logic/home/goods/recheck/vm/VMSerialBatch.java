package com.arpa.wms.hly.logic.home.goods.recheck.vm;

import android.app.Application;

import androidx.annotation.NonNull;

import com.arpa.and.arch.base.BaseModel;
import com.arpa.wms.hly.bean.GoodsItemVO;
import com.arpa.wms.hly.bean.SNCutRule;
import com.arpa.wms.hly.bean.req.ReqSNRule;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

/**
 * author: 李一方(<a href="mailto:leergo@dingtalk.com">leergo@dingtalk.com</a>)<br/>
 * version: 1.0.0<br/>
 * since: 2023-04-26 13:48
 *
 * <p>
 * vm: 批量登记界面扫码
 * </p>
 */
@HiltViewModel
public class VMSerialBatch extends AbsVMSerial {
    private final List<SNCutRule> usedRule = new ArrayList<>();
    private String goodsCode;
    private GoodsItemVO item;

    @Inject
    public VMSerialBatch(@NonNull Application application, BaseModel model) {
        super(application, model);
    }

    @Override
    protected void handleMultiRule(List<SNCutRule> ruleGroup) {
        // TODO: 弹窗选择批次规则 add by 李一方 2023-04-26 15:11:43
    }

    @Override
    protected void calcCountRadio(SNCutRule rule) {

    }

    @Override
    protected Integer obtainScanRadio(SNCutRule rule) {
        // TODO: 待实现 add by 李一方 2023-05-04 16:35:53
        return 1;
    }

    @Override
    protected String obtainItemCode(SNCutRule rule) {
        return item.getCode();
    }

    @Override
    protected String obtainTarget(String snCode) {
        return goodsCode;
    }

    @Override
    protected void handleReq(ReqSNRule reqSNRule, String target) {
        reqSNRule.setGoodsCode(goodsCode);
    }

    public void setItem(GoodsItemVO item) {
        this.item = item;
    }
}
