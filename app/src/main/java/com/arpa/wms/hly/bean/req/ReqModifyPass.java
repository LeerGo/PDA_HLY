package com.arpa.wms.hly.bean.req;

import com.arpa.wms.hly.bean.base.ReqBase;

/**
 * author: 李一方(<a href="mailto:leergo@dingtalk.com">leergo@dingtalk.com</a>)<br/>
 * version: 1.0.0<br/>
 * since: 2021-05-14 13:57
 */
public class ReqModifyPass extends ReqBase {
    private String oldPwd;
    private String newPwd;
    private transient String newRepwd;

    @Override
    public String toString() {
        return "ReqModifyPass{" +
                "oldPass='" + oldPwd + '\'' +
                ", newPass='" + newPwd + '\'' +
                ", newPassRepeat='" + newRepwd + '\'' +
                '}';
    }

    public String getOldPwd() {
        return oldPwd;
    }

    public void setOldPwd(String oldPwd) {
        this.oldPwd = oldPwd;
    }

    public String getNewPwd() {
        return newPwd;
    }

    public void setNewPwd(String newPwd) {
        this.newPwd = newPwd;
    }

    public String getNewRepwd() {
        return newRepwd;
    }

    public void setNewRepwd(String newRepwd) {
        this.newRepwd = newRepwd;
    }
}
