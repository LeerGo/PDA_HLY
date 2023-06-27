package com.arpa.wms.hly.logic.home.goods.recheck.vm;

import android.app.Application;
import android.content.Intent;
import android.os.Message;
import android.text.TextUtils;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableBoolean;
import androidx.databinding.ObservableField;
import androidx.databinding.ObservableInt;

import com.arpa.and.arch.base.BaseModel;
import com.arpa.wms.hly.BR;
import com.arpa.wms.hly.R;
import com.arpa.wms.hly.bean.SNCutRule;
import com.arpa.wms.hly.bean.entity.SNCode;
import com.arpa.wms.hly.bean.req.ReqSNRule;
import com.arpa.wms.hly.ui.listener.ViewListener;
import com.arpa.wms.hly.utils.Const;
import com.arpa.wms.hly.utils.DateUtils;
import com.arpa.wms.hly.utils.NumberUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import me.tatarka.bindingcollectionadapter2.ItemBinding;

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
    public ObservableField<String> goodName = new ObservableField<>();
    public ObservableField<String> goodUnitName = new ObservableField<>();
    public ObservableField<String> gmtManufacture = new ObservableField<>(); // 生产日期
    public ObservableField<String> placeOrigin = new ObservableField<>(); // 产地
    public ObservableArrayList<SNCode> items = new ObservableArrayList<>();
    public ObservableField<String> ratio = new ObservableField<>();
    public ObservableInt scanCount = new ObservableInt();
    public ObservableField<Integer> scanRatio = new ObservableField<>();
    private String goodsCode;
    private String itemCode;
    public int goodsCount; // 商品数，用以计算扫码比例

    public ObservableBoolean isManually = new ObservableBoolean();

    @Inject
    public VMSerialBatch(@NonNull Application application, BaseModel model) {
        super(application, model);
    }

    /**
     * 开启手动模式
     */
    public void manuallyMode(boolean isManually) {
        this.isManually.set(isManually);
    }

    @Override
    public void onCreate() {
        super.onCreate();

        checkHistory();
    }

    private void checkHistory() {
        if (snDao.count(taskCode, itemCode) > 0) {
            sendSingleLiveEvent(Const.Message.MSG_DIALOG);
        } else {
            calcCountRadio();
            obtainScanRadio(null);
        }
    }

    public void init() {
        loadHistory();
        calcCountRadio();
        obtainScanRadio(null);
    }

    private void loadHistory() {
        items.addAll(snDao.getByTask(taskCode, itemCode));
    }

    public void initParams(Intent intent) {
        taskCode = intent.getStringExtra(Const.IntentKey.CODE);
        itemCode = intent.getStringExtra(Const.IntentKey.OUTBOUND_ITEM_CODE);
        goodsCode = intent.getStringExtra(Const.IntentKey.GOODS_CODE);
        goodName.set(intent.getStringExtra(Const.IntentKey.GOODS_NAME));
        goodUnitName.set(intent.getStringExtra(Const.IntentKey.GOODS_UNIT_NAME));
        goodsCount = intent.getIntExtra(Const.IntentKey.GOODS_COUNT, 0);
        gmtManufacture.set(intent.getStringExtra(Const.IntentKey.DATE_MANUFACTURE));
        placeOrigin.set(intent.getStringExtra(Const.IntentKey.PLACE_ORIGIN));
    }

    @Override
    protected void handleMultiRule(Map<Integer, List<SNCutRule>> ruleGroup) {
        if (null != ruleGroup && !ruleGroup.isEmpty()) {
            Message msg = new Message();
            msg.what = Const.Message.MSG_MULTI_RULE;
            msg.obj = ruleGroup;
            sendSingleLiveEvent(msg);
        }
    }

    @Override
    protected void afterSaveSNCode(SNCutRule rule, SNCode code) {
        super.afterSaveSNCode(rule, code);
        items.add(0, code);
    }

    private void calcCountRadio() {
        calcCountRadio(null);
    }

    @Override
    protected void calcCountRadio(SNCutRule rule) {
        int count = snDao.countRadio(taskCode, itemCode);
        BigDecimal res;
        if (0 == count) {
            res = BigDecimal.ZERO;
        } else {
            res = new BigDecimal(count)
                    .divide(BigDecimal.valueOf(goodsCount), 4, RoundingMode.HALF_UP)
                    .multiply(BigDecimal.valueOf(100))
                    .setScale(2, RoundingMode.HALF_UP);
        }
        scanCount.set(count);
        ratio.set(NumberUtils.parseDecimal(res) + "%");
        taskDao.updateTaskRatio(taskCode, itemCode, res);
    }

    @Override
    protected Integer obtainScanRadio(SNCutRule rule) {
        scanRatio.set(taskDao.queryTaskRatio(taskCode, itemCode));
        return scanRatio.get();
    }

    @Override
    protected String obtainItemCode(SNCutRule rule) {
        return itemCode;
    }

    @Override
    protected String obtainItemProdDate(SNCutRule rule) {
        if (TextUtils.isEmpty(gmtManufacture.get())) {
            return null;
        }
        return gmtManufacture.get();
    }

    @Override
    protected String obtainItemProdAddress(SNCutRule rule) {
        return placeOrigin.get();
    }

    @Override
    protected Integer obtainItemPlanQuantity(SNCutRule rule) {
        return goodsCount;
    }

    @Override
    protected String obtainTarget(String snCode) {
        return goodsCode;
    }

    @Override
    protected void handleReq(ReqSNRule reqSNRule, String target) {
        reqSNRule.setGoodsCode(goodsCode);
    }

    public ItemBinding<SNCode> getItemBinding() {
        ItemBinding<SNCode> itemBinding = ItemBinding.of(BR.data, R.layout.item_batch_code);
        itemBinding.bindExtra(BR.listener, (ViewListener.DataTransCallback<SNCode>) this::removeData);
        return itemBinding;
    }

    private void removeData(SNCode snCode) {
        items.remove(snCode);
        snDao.delete(snCode.getTaskCode(), snCode.getTaskItemCode(), snCode.getSnCode());
        calcCountRadio();
    }

    public void onScanRatioChange(boolean isFocus) {
        if (!isFocus) {
            taskDao.updateScanRatio(taskCode, itemCode, scanRatio.get());
        }
    }

    public void confirm() {
        if (items.size() < 2) {
            finish();
        } else {
            Message msg = new Message();
            msg.what = Const.Message.MSG_BATCH_CONFIRM;

            boolean isDateVerify = items.stream().allMatch(it -> it.getProductionDate().equals(obtainItemProdDate(null)));
            boolean isOriginVerify = items.stream().allMatch(it -> it.getProductionLocation().equals(obtainItemProdAddress(null)));
            ;

            SNCode oldest = Collections.max(items);
            SNCode latest = Collections.min(items);
            String diff = DateUtils.dateDiff(latest.getFullProdDate(), oldest.getFullProdDate());
            msg.obj = (isOriginVerify ? "1、产地校验正确" : "1、产地校验不正确") + "\n" +
                    (isDateVerify ? "2、生产日期校验正确" : "2、生产日期校验不正确") + "\n" +
                    "3、上下批次时间为" + diff;
            sendSingleLiveEvent(msg);
        }
    }

    public void chooseRule() {
        if (cacheRule.isEmpty()) {
            handleReq(reqSNRule, target);
            requestRule(null);
        } else {
            obtainMultiRuleGroup();
        }
    }

    private void obtainMultiRuleGroup() {
        var ruleAll = cacheRule.get(target);
        if (null != ruleAll) {
            var ruleGroup = ruleAll.entrySet().stream()
                    .filter(entry -> entry.getValue().size() > 1)
                    .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
            if (ruleGroup.isEmpty()) {
                sendMessage("切分规则已同步");
            } else {
                handleMultiRule(ruleGroup);
            }
        } else {
            sendMessage("暂无有效切分规则\n请联系管理员添加");
        }
    }

    @Override
    protected void afterObtainRule(String snCode) {
        if (TextUtils.isEmpty(snCode)) {
            obtainMultiRuleGroup();
        } else {
            super.afterObtainRule(snCode);
        }
    }

    public void removeHistory() {
        snDao.removeByTaskItem(taskCode, itemCode);
        taskDao.updateTaskRatio(taskCode, itemCode, BigDecimal.ZERO);
        calcCountRadio();
        obtainScanRadio(null);
    }
}
