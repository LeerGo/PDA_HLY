package com.arpa.wms.hly.logic.home.goods.recheck.vm;

import android.app.Application;

import androidx.annotation.NonNull;

import com.arpa.and.arch.base.BaseModel;
import com.arpa.wms.hly.bean.GoodsItemVO;
import com.arpa.wms.hly.bean.RecheckItemVO;
import com.arpa.wms.hly.bean.SNCutRule;
import com.arpa.wms.hly.bean.req.ReqSNRule;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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
    protected void handleMultiRule(Map<Integer, List<SNCutRule>> ruleGroup) {
        keyWord.set(null);
        sendMessage("无法匹配唯一规则，请进入批次登记页面扫码");
    }

    @Override
    protected void calcCountRadio(SNCutRule rule) {
        var data = getItemVO(rule);
        data.ifPresent(vo -> {
            int count = snDao.countRadio(taskCode, vo.getCode());
            BigDecimal res;
            if (0 == count) {
                res = BigDecimal.ZERO;
            } else {
                res = new BigDecimal(count)
                        .divide(BigDecimal.valueOf(vo.getPlanQuantity()), 4, RoundingMode.HALF_UP)
                        .multiply(BigDecimal.valueOf(100))
                        .setScale(2, RoundingMode.HALF_UP);
            }
            vo.setRatio(res);
            taskDao.updateTaskRatio(taskCode, vo.getCode(), res);
        });
    }

    @Override
    protected Integer obtainScanRadio(SNCutRule rule) {
        var data = getItemVO(rule);
        if (data.isPresent()) {
            return taskDao.queryTaskRatio(taskCode, data.get().getCode());
        } else {
            return 1;
        }
    }

    @NonNull
    private Optional<RecheckItemVO> getItemVO(SNCutRule rule) {
        return items.stream()
                .filter(it -> it.getGoodCode().equals(rule.getGoodsCode()))
                .findFirst();
    }

    @Override
    protected String obtainItemCode(SNCutRule rule) {
        return getItemVO(rule)
                .map(GoodsItemVO::getCode)
                .orElse(null);
    }

    @Override
    protected String obtainItemProdDate(SNCutRule rule) {
        return getItemVO(rule).map(GoodsItemVO::getGmtManufacture).orElse(null);
    }

    @Override
    protected String obtainItemProdAddress(SNCutRule rule) {
        return getItemVO(rule).map(GoodsItemVO::getExtendOne).orElse(null);
    }

    @Override
    protected Integer obtainItemPlanQuantity(SNCutRule rule) {
        return getItemVO(rule).map(GoodsItemVO::getPlanQuantity).orElse(0);
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
