package com.arpa.wms.hly.bean.req;

/**
 * author: 李一方(<a href="mailto:leergo@dingtalk.com">leergo@dingtalk.com</a>)<br/>
 * version: 1.0.0<br/>
 * since: 2021-05-14 13:57
 */
public class ReqModifyPass {
    private String oldPass;
    private String newPass;
    private String newPassRepeat;

    @Override
    public String toString() {
        return "ReqModifyPass{" +
                "oldPass='" + oldPass + '\'' +
                ", newPass='" + newPass + '\'' +
                ", newPassRepeat='" + newPassRepeat + '\'' +
                '}';
    }

    public String getOldPass() {
        return oldPass;
    }

    public void setOldPass(String oldPass) {
        this.oldPass = oldPass;
    }

    public String getNewPass() {
        return newPass;
    }

    public void setNewPass(String newPass) {
        this.newPass = newPass;
    }

    public String getNewPassRepeat() {
        return newPassRepeat;
    }

    public void setNewPassRepeat(String newPassRepeat) {
        this.newPassRepeat = newPassRepeat;
    }
}
