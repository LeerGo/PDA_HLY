package com.arpa.wms.hly.bean.req;

import com.arpa.wms.hly.bean.base.ReqBase;

/**
 * author: 李一方(<a href="mailto:leergo@dingtalk.com">leergo@dingtalk.com</a>)<br/>
 * version: 1.0.0<br/>
 * since: 2021-05-14 13:57
 */
public class ReqModifyPass extends ReqBase {
    private String oldPassword;
    private String newPassword;
    private transient String newRepwd;

    @Override
    public String toString() {
        return "ReqModifyPass{" +
                "oldPass='" + oldPassword + '\'' +
                ", newPass='" + newPassword + '\'' +
                ", newPassRepeat='" + newRepwd + '\'' +
                '}';
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getNewRepwd() {
        return newRepwd;
    }

    public void setNewRepwd(String newRepwd) {
        this.newRepwd = newRepwd;
    }
}
