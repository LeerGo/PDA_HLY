package com.arpa.wms.hly.bean;

import com.google.gson.annotations.SerializedName;

import com.arpa.wms.hly.utils.Const;
import com.arpa.wms.hly.utils.SPUtils;

/**
 * author: 李一方(<a href="mailto:leergo@dingtalk.com">leergo@dingtalk.com</a>)<br/>
 * version: 1.0.0<br/>
 * since: 2021-04-23 2:40 PM
 *
 * <p>
 * 内容描述区域
 * </p>
 */
/*
params.put("client_id", "1e069e240a163e9d2a18f4111ewecpda");
params.put("client_secret", "e7957728041b11ea97dffa163e9d2pda");
params.put("grant_type", "password");
params.put("response_type", "token");
params.put("device_id", MyPreferenceManager.getString("deviceid", ""));
params.put("username", userName);
params.put("password", Md5Utils.getBase64(passWord + "_arpa_" + time));
params.put("time", time);
params.put("authorizeDataCode", MyPreferenceManager.getString("CANGKU", ""));
*/
public class ReqLogin {
    @SerializedName("client_id")
    public String clientID;
    @SerializedName("client_secret")
    public String clientSecret;
    @SerializedName("grant_type")
    public String grantType;
    @SerializedName("response_type")
    public String responseType;
    public String authorizeDataCode;

    @SerializedName("device_id")
    private String deviceID;
    private String username;
    private String password;
    private String time;

    public ReqLogin() {
        clientID = Const.AppConfig.clientID;
        clientSecret = Const.AppConfig.clientSecret;
        grantType = "password";
        responseType = "token";
        authorizeDataCode = SPUtils.getInstance().getString(Const.SPKEY.WAREHOUSE_CODE);
    }

    public String getDeviceID() {
        return deviceID;
    }

    public void setDeviceID(String deviceID) {
        this.deviceID = deviceID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
