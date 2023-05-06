package com.arpa.wms.hly.logic.home.goods.recheck.vm;

import android.app.Application;
import android.content.Intent;
import android.os.Message;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableArrayList;
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
import com.arpa.wms.hly.utils.NumberUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;
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
    public ObservableInt scanRatio = new ObservableInt();
    private String goodsCode;
    private String itemCode;
    public int goodsCount; // 商品数，用以计算扫码比例

    @Inject
    public VMSerialBatch(@NonNull Application application, BaseModel model) {
        super(application, model);
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
        Message msg = new Message();
        msg.what = Const.Message.MSG_MULTI_RULE;
        msg.obj = ruleGroup;
        sendSingleLiveEvent(msg);
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
        Integer count = snDao.countRadio(taskCode, itemCode);
        BigDecimal res;
        if (null == count || 0 == count) {
            count = 0;
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
        snDao.delete(snCode);
        calcCountRadio();
    }

    public void onScanRatioChange(boolean isFocus) {
        if (!isFocus) {
            taskDao.updateScanRatio(taskCode, itemCode, scanRatio.get());
        }
    }

    public void confirm() {

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
            handleMultiRule(ruleGroup);
        } else {
            sendMessage("暂无有效切分规则\n请联系管理员添加");
        }
    }

    @Override
    protected void afterObtainRule(String snCode) {
        obtainMultiRuleGroup();
    }

    public void removeHistory() {
        snDao.removeByTaskItem(taskCode, itemCode);
        taskDao.updateTaskRatio(taskCode, itemCode, BigDecimal.ZERO);
        calcCountRadio();
        obtainScanRadio(null);
    }
}
