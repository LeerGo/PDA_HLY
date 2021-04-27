package com.arpa.wms.hly.bean;

import com.google.gson.annotations.SerializedName;

/**
 * author: 李一方(<a href="mailto:leergo@dingtalk.com">leergo@dingtalk.com</a>)<br/>
 * version: 1.0.0<br/>
 * since: 2021-04-23 2:40 PM
 *
 * <p>
 * 内容描述区域
 * </p>
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

    public static class PartyBean {
        private String id;
        private String name;
        private String code;
        private String partyType;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getPartyType() {
            return partyType;
        }

        public void setPartyType(String partyType) {
            this.partyType = partyType;
        }
    }

}
