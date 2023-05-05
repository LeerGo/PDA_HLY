package com.arpa.wms.hly.logic.home.goods.recheck.vm;

import android.app.Application;
import android.text.TextUtils;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;

import com.arpa.and.arch.base.BaseModel;
import com.arpa.and.arch.base.livedata.StatusEvent;
import com.arpa.wms.hly.base.viewmodel.WrapDataViewModel;
import com.arpa.wms.hly.bean.SNCutRule;
import com.arpa.wms.hly.bean.entity.SNCode;
import com.arpa.wms.hly.bean.req.ReqSNRule;
import com.arpa.wms.hly.dao.AppDatabase;
import com.arpa.wms.hly.dao.SNCodeDao;
import com.arpa.wms.hly.dao.TaskItemDao;
import com.arpa.wms.hly.net.callback.ResultCallback;
import com.arpa.wms.hly.net.exception.ResultError;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    protected final Map<String, Map<Integer, List<SNCutRule>>> cacheRule = new HashMap<>();
    protected final Map<String, Long> cacheTime = new HashMap<>();
    private final ReqSNRule reqSNRule = new ReqSNRule();
    public ObservableField<String> keyWord = new ObservableField<>();
    protected String target;
    protected String taskCode;
    protected final SNCodeDao snDao;
    protected final TaskItemDao taskDao;

    public AbsVMSerial(@NonNull Application application, BaseModel model) {
        super(application, model);

        snDao = getRoomDatabase(AppDatabase.class).snCodeDao();
        taskDao = getRoomDatabase(AppDatabase.class).taskItemDao();
    }

    public void onScan(String snCode) {
        if (TextUtils.isEmpty(snCode) || snCode.length() < 3) return;
        target = obtainTarget(snCode);
        Long current = System.currentTimeMillis();
        if (!cacheTime.containsKey(target) || (current - cacheTime.get(target)) >= diffTime) {
            handleReq(reqSNRule, target);
            requestRule(snCode);
        } else {
            handleSNCode(snCode);
        }
    }

    private void handleSNCode(String snCode) {
        var map = cacheRule.get(target);
        if (map.containsKey(snCode.length())) {
            List<SNCutRule> ruleGroup = map.get(snCode.length());
            if (ruleGroup.size() == 1) {
                cutSNCode(ruleGroup.get(0), snCode);
            } else {
                handleMultiRule(ruleGroup);
            }
        } else {
            keyWord.set(null);
            sendMessage("未能匹配到有效切分规则");
        }
    }

    protected abstract void handleMultiRule(List<SNCutRule> ruleGroup);

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
            snDao.insert(code);
            afterSaveSNCode(rule, code);
        } else {
            sendMessage("序列号已存在");
        }
        keyWord.set(null);
    }

    protected void afterSaveSNCode(SNCutRule rule, SNCode code) {
        sendMessage("序列号录入成功");
        calcCountRadio(rule);
    }

    protected abstract void calcCountRadio(SNCutRule rule);

    protected abstract Integer obtainScanRadio(SNCutRule rule);

    protected abstract String obtainItemCode(SNCutRule rule);

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
                cacheTime.put(target, System.currentTimeMillis());
                cacheRule.put(target, data);
                handleSNCode(snCode);
            }

            @Override
            public void onFailed(ResultError error) {
                updateStatus(StatusEvent.Status.ERROR);
                sendMessage(error.getMessage());
            }
        });
    }

    public void setTaskCode(String taskCode) {
        this.taskCode = taskCode;
    }
}
