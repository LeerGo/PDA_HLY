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
    private static final String TAG = "@@@@ AbsVMSerial";
    // 五分钟时间标记
    private final long diffTime = 5 * 60 * 1000;
    // 本地规则缓存
    // 复核详情 key 是 goods_id；复核批次登记 key 是 goods_code
    // value 是关联规则
    public ObservableField<String> keyWord = new ObservableField<>();
    public ObservableBoolean isFocus = new ObservableBoolean(true);
    protected final Map<String, Map<Integer, List<SNCutRule>>> cacheRule = new HashMap<>();
    public HashMap<Integer, SNCutRule> ruleSelect = new HashMap<>();
    protected final Map<String, Long> cacheTime = new HashMap<>();
    protected final ReqSNRule reqSNRule = new ReqSNRule();
    protected String target;
    protected String taskCode;
    protected String snCode;
    protected final SNCodeDao snDao;
    protected final TaskItemDao taskDao;
    private final SoundPlayer player;

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
                    var ruleMap = tmp.entrySet().stream()
                            .filter(entry -> entry.getValue().size() > 1)
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

    protected abstract void handleMultiRule(Map<Integer, List<SNCutRule>> ruleGroup);

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

    public void addSNCode(SNCutRule rule, SNCode code) {
        snDao.insert(code);
        afterSaveSNCode(rule, code);
    }

    private boolean check(SNCutRule rule, SNCode code) {
        int count = snDao.countRadio(taskCode, obtainItemCode(rule));
        if (count >= obtainItemPlanQuantity(rule)) {
            player.play(R.raw.scan_failed);
            sendMessage("批次号已录入最大数量");
            return false;
        }
        if (1 == rule.getProductionDateFlag() && !RexUtils.isYYMMDD(code.getProductionDate())) {
            player.play(R.raw.scan_failed);
            sendMessage("生产日期格式错误");
            return false;
        }
        if (1 == rule.getExpirationDateFlag() && !RexUtils.isYYMMDD(code.getExpirationDate())) {
            player.play(R.raw.scan_failed);
            sendMessage("过期日期格式错误");
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
        if (1 == rule.getProductionLocationFlag() && !RexUtils.isAddress(code.getProductionLocation())) {
            player.play(R.raw.scan_failed);
            sendMessage("产地格式错误");
            return false;
        }

        StringBuilder tips = new StringBuilder();
        if (!RexUtils.is24Hour(code.getProductionTime())) {
            tips.append("时分秒校验错误");
        }
        if (!code.getProductionDate().equals(obtainItemProdDate(rule))) {
            if (!TextUtils.isEmpty(tips)) tips.append("\n");
            tips.append("生产日期校验错误");
        }
        if (!code.getProductionLocation().equals(obtainItemProdAddress(rule))) {
            if (!TextUtils.isEmpty(tips)) tips.append("\n");
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

    protected void afterSaveSNCode(SNCutRule rule, SNCode code) {
        sendMessage("序列号录入成功");
        keyWord.set(null);
        isFocus.set(true);
        calcCountRadio(rule);
    }

    protected abstract void calcCountRadio(SNCutRule rule);

    protected abstract Integer obtainScanRadio(SNCutRule rule);

    protected abstract String obtainItemCode(SNCutRule rule);

    protected abstract String obtainItemProdDate(SNCutRule rule);

    protected abstract String obtainItemProdAddress(SNCutRule rule);

    protected abstract Integer obtainItemPlanQuantity(SNCutRule rule);

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
}
