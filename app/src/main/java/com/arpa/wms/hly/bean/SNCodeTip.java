package com.arpa.wms.hly.bean;

import com.arpa.wms.hly.bean.entity.SNCode;

/**
 * author: 李一方(<a href="mailto:leergo@dingtalk.com">leergo@dingtalk.com</a>)<br/>
 * version: 1.0.0<br/>
 * since: 2023-05-06 16:21
 */
public class SNCodeTip {
    private String tip;
    private SNCode code;
    private SNCutRule rule;

    public SNCodeTip(String tip, SNCode code, SNCutRule rule) {
        this.tip = tip;
        this.code = code;
        this.rule = rule;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    public SNCode getCode() {
        return code;
    }

    public void setCode(SNCode code) {
        this.code = code;
    }

    public SNCutRule getRule() {
        return rule;
    }

    public void setRule(SNCutRule rule) {
        this.rule = rule;
    }
}
