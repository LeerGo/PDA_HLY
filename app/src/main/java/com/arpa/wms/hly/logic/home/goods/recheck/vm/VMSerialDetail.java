package com.arpa.wms.hly.logic.home.goods.recheck.vm;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;

import com.arpa.and.arch.base.BaseModel;
import com.arpa.and.arch.base.DataViewModel;
import com.arpa.and.arch.base.livedata.MessageEvent;
import com.arpa.and.arch.base.livedata.StatusEvent;
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

    @Inject
    public VMSerialDetail(@NonNull Application application, BaseModel model) {
        super(application, model);
    }

    @Override
    protected void handleMultiRule(List<SNCutRule> ruleGroup) {
        sendMessage("无法匹配唯一规则，请进入批次登记页面扫码");
    }

    @Override
    protected String obtainTarget(String snCode) {
        return snCode.substring(snCode.length() - 3);
    }

    @Override
    protected void handleReq(ReqSNRule reqSNRule, String target) {
        reqSNRule.setGoodsId(target);
    }

    public void register(LifecycleOwner owner, DataViewModel model) {
        getSingleLiveEvent().observe(owner, model::sendSingleLiveEvent);
        getStatusEvent().observe(owner, (StatusEvent.StatusObserver) model::updateStatus);
        getMessageEvent().observe(owner, (MessageEvent.MessageObserver) model::sendMessage);
    }
}
