package com.arpa.wms.hly.logic.home.goods.recheck.vm;

import android.app.Application;
import android.os.Message;
import android.text.TextUtils;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableBoolean;
import androidx.databinding.ObservableField;

import com.arpa.and.arch.base.BaseModel;
import com.arpa.and.arch.base.livedata.StatusEvent;
import com.arpa.wms.hly.R;
import com.arpa.wms.hly.base.viewmodel.WrapDataViewModel;
import com.arpa.wms.hly.bean.SNCodeTip;
import com.arpa.wms.hly.bean.SNCutRule;
import com.arpa.wms.hly.bean.entity.SNCode;
import com.arpa.wms.hly.bean.req.ReqSNRule;
import com.arpa.wms.hly.dao.AppDatabase;
import com.arpa.wms.hly.dao.SNCodeDao;
import com.arpa.wms.hly.dao.TaskItemDao;
import com.arpa.wms.hly.net.callback.ResultCallback;
import com.arpa.wms.hly.net.exception.ResultError;
import com.arpa.wms.hly.utils.Const;
import com.arpa.wms.hly.utils.DateUtils;
import com.arpa.wms.hly.utils.RexUtils;
import com.arpa.wms.hly.utils.SoundPlayer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * author: 李一方(<a href="mailto:leergo@dingtalk.com">leergo@dingtalk.com</a>)<br/>
 * version: 1.0.0<br/>
 * since: 2023-04-26 11:21
 *
 * <p>
 * vm：序列好相关业务
 * </p>
 */
public abstract class AbsVMSerial extends WrapDataViewModel {
    protected final Map<String, Map<Integer, List<SNCutRule>>> cacheRule = new HashMap<>();
    protected final Map<String, Long> cacheTime = new HashMap<>();
    protected final ReqSNRule reqSNRule = new ReqSNRule();
    protected final SNCodeDao snDao;
    protected final TaskItemDao taskDao;
    // 五分钟时间标记
    private final long diffTime = 5 * 60 * 1000;
    private final SoundPlayer player;
    // 本地规则缓存
    // 复核详情 key 是 goods_id；复核批次登记 key 是 goods_code
    // value 是关联规则
    public ObservableField<String> keyWord = new ObservableField<>();
    public ObservableBoolean isFocus = new ObservableBoolean(true);
    public HashMap<Integer, SNCutRule> ruleSelect = new HashMap<>();
    protected String target;
    protected String taskCode;
    protected String snCode;

    public AbsVMSerial(@NonNull Application application, BaseModel model) {
        super(application, model);

        snDao = getRoomDatabase(AppDatabase.class).snCodeDao();
        taskDao = getRoomDatabase(AppDatabase.class).taskItemDao();
        player = new SoundPlayer(getApplication());
    }

    public void release() {
        if (null != player) {
            player.release();
        }
    }

    public void onScan(String snCode) {
        isFocus.set(false);
        if (TextUtils.isEmpty(snCode) || snCode.length() < 3) {
            keyWord.set(null);
            isFocus.set(true);
            return;
        }
        snCode = snCode.replaceAll(RexUtils.REX_CLEAN, "");
        this.snCode = snCode;
        target = obtainTarget(snCode);
        Long current = System.currentTimeMillis();
        if (!cacheTime.containsKey(target) || (current - cacheTime.get(target)) >= diffTime) {
            handleReq(reqSNRule, target);
            requestRule(snCode);
        } else {
            handleSNCode(snCode);
        }
    }

    protected abstract String obtainTarget(String snCode);

    protected abstract void handleReq(ReqSNRule reqSNRule, String target);

    public void requestRule(String snCode) {
        updateStatus(StatusEvent.Status.LOADING);
        apiService.cutSnRule(reqSNRule.toParams()).enqueue(new ResultCallback<>() {
            @Override
            public void onSuccess(Map<Integer, List<SNCutRule>> data) {
                updateStatus(StatusEvent.Status.SUCCESS);
                handlerSNRule(data);
            }

            private void handlerSNRule(Map<Integer, List<SNCutRule>> data) {
                if (TextUtils.isEmpty(target)) {
                    target = obtainTarget(snCode);
                }
                cacheTime.put(target, System.currentTimeMillis());
                cacheRule.put(target, data);
                afterObtainRule(snCode);
            }

            @Override
            public void onFailed(ResultError error) {
                updateStatus(StatusEvent.Status.ERROR);
                sendMessage(error.getMessage());
            }
        });
    }

    protected void afterObtainRule(String snCode) {
        handleSNCode(snCode);
    }

    public void setTaskCode(String taskCode) {
        this.taskCode = taskCode;
    }

    public void multiRuleSel(HashMap<Integer, SNCutRule> data) {
        this.ruleSelect = data;
        if (null != data && !data.isEmpty() && !TextUtils.isEmpty(snCode)) {
            handleSNCode(snCode);
        }
    }

    protected void handleSNCode(String snCode) {
        var tmp = cacheRule.get(target);
        if (null != tmp && tmp.containsKey(snCode.length())) {
            List<SNCutRule> ruleGroup = tmp.get(snCode.length());
            if (null == ruleGroup) {
                keyWord.set(null);
                isFocus.set(true);
                sendMessage("未能匹配到有效切分规则");
            } else if (ruleGroup.size() == 1) {
                cutSNCode(ruleGroup.get(0), snCode);
            } else {
                var validRule = ruleSelect.get(snCode.length());
                if (null == validRule) {
                    var ruleMap = tmp.entrySet().stream().filter(entry -> entry.getValue().size() > 1)
                            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
                    handleMultiRule(ruleMap);
                } else {
                    cutSNCode(validRule, snCode);
                }
            }
        } else {
            keyWord.set(null);
            isFocus.set(true);
            sendMessage("未能匹配到有效切分规则");
        }
    }

    /**
     * 切分序列号
     */
    protected void cutSNCode(SNCutRule rule, String snCode) {
        var code = snDao.exists(snCode);
        if (null == code) {
            code = new SNCode();
            code.setTaskCode(taskCode);
            code.setTaskItemCode(obtainItemCode(rule));
            code.setScanRatio(obtainScanRadio(rule));
            code.convertRule(rule, snCode);
            if (check(rule, code)) {
                addSNCode(rule, code);
            }
        } else {
            player.play(R.raw.scan_failed);
            sendSingleLiveEvent(Const.Message.MSG_BATCH_REPEAT);
        }
        keyWord.set(null);
        isFocus.set(true);
    }

    protected abstract void handleMultiRule(Map<Integer, List<SNCutRule>> ruleGroup);

    protected abstract String obtainItemCode(SNCutRule rule);

    protected abstract Integer obtainScanRadio(SNCutRule rule);

    private boolean check(SNCutRule rule, SNCode code) {
        int count = snDao.countRadio(taskCode, obtainItemCode(rule));
        if (count >= obtainItemPlanQuantity(rule)) {
            player.play(R.raw.scan_failed);
            sendMessage("批次号已录入最大数量");
            return false;
        }
        if (1 == rule.getProductionLocationFlag() && TextUtils.isEmpty(code.getProductionLocation())) {
            player.play(R.raw.scan_failed);
            sendMessage("产地不可为空");
            return false;
        }
        if (1 == rule.getProductionLocationFlag() && !RexUtils.isAddress(obtainItemProdAddress(rule), code.getProductionLocation())) {
            player.play(R.raw.scan_failed);
            sendMessage("产地格式错误");
            return false;
        }
        if (1 == rule.getProductionDateFlag() && !RexUtils.isYYMMDD(code.getProductionDate())) {
            player.play(R.raw.scan_failed);
            sendMessage("生产日期格式错误");
            return false;
        }
        if (1 == rule.getProductionTimeFlag() && !RexUtils.is24Hour(code.getProductionTime())) {
            player.play(R.raw.scan_failed);
            sendMessage("生产时间格式错误");
            return false;
        }
        if (1 == rule.getExpirationDateFlag() && !RexUtils.isYYMMDD(code.getExpirationDate())) {
            player.play(R.raw.scan_failed);
            sendMessage("过期日期格式错误");
            return false;
        }
        if (1 == rule.getMachineNumFlag() && TextUtils.isEmpty(code.getMachineNum())) {
            player.play(R.raw.scan_failed);
            sendMessage("机台号格式错误");
            return false;
        }

        if (1 == rule.getProductionDateFlag() && DateUtils.isMoreToday(code.getFullProdDate())) {
            player.play(R.raw.scan_failed);
            sendMessage("批次号日期超出当天");
            return false;
        }
        if (1 == rule.getProductionDateFlag() && 1 == rule.getExpirationDateFlag() && code.getExpirationDate().compareTo(code.getProductionDate()) <= 0) {
            player.play(R.raw.scan_failed);
            sendMessage("过期日期早于生产日期");
            return false;
        }

        StringBuilder tips = new StringBuilder();
        if (1 == rule.getProductionTimeFlag() && !RexUtils.is24Hour(code.getProductionTime())) {
            tips.append("时分秒校验错误");
        }
        if (1 == rule.getProductionDateFlag() && !code.getProductionDate().equals(obtainItemProdDate(rule))) {
            if (!TextUtils.isEmpty(tips)) {tips.append("\n");}
            tips.append("生产日期校验错误");
        }
        if (1 == rule.getProductionLocationFlag() && !RexUtils.isAddressM(obtainItemProdAddress(rule), code.getProductionLocation()) && !code.getProductionLocation()
                .equals(obtainItemProdAddress(rule))) {
            if (!TextUtils.isEmpty(tips)) {tips.append("\n");}
            tips.append("产地校验错误");
        }
        if (!TextUtils.isEmpty(tips)) {
            player.play(R.raw.scan_error);
            Message msg = new Message();
            msg.what = Const.Message.MSG_BATCH_VERIFY;
            msg.obj = new SNCodeTip(tips.toString(), code, rule);
            sendSingleLiveEvent(msg);
            return false;
        }
        return true;
    }

    public void addSNCode(SNCutRule rule, SNCode code) {
        snDao.insert(code);
        afterSaveSNCode(rule, code);
    }

    protected abstract Integer obtainItemPlanQuantity(SNCutRule rule);

    protected abstract String obtainItemProdAddress(SNCutRule rule);

    protected abstract String obtainItemProdDate(SNCutRule rule);

    protected void afterSaveSNCode(SNCutRule rule, SNCode code) {
        sendMessage("序列号录入成功");
        keyWord.set(null);
        isFocus.set(true);
        calcCountRadio(rule);
    }

    protected abstract void calcCountRadio(SNCutRule rule);
}
