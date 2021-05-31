package com.arpa.wms.hly.bean.res;

import com.google.gson.annotations.SerializedName;

import com.arpa.wms.hly.bean.PartyBean;

/**
 * author: 李一方(<a href="mailto:leergo@dingtalk.com">leergo@dingtalk.com</a>)<br/>
 * version: 1.0.0<br/>
 * since: 2021-04-23 2:40 PM
 */
public class ResLogin {
    @SerializedName("access_token")
    private String accessToken;
    private PartyBean party;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public PartyBean getParty() {
        return party;
    }

    public void setParty(PartyBean party) {
        this.party = party;
    }
}
