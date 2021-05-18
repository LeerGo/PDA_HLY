package com.arpa.wms.hly.bean.req;

import java.util.List;

/**
 * author: 李一方(<a href="mailto:leergo@dingtalk.com">leergo@dingtalk.com</a>)<br/>
 * version: 1.0.0<br/>
 * since: 2021-05-18 10:49
 */
public class ReqTruckLoadConfirm {
    private String code;
    private int trayQuantity;
    private List<OutboundItemDTOS> outboundItemDTOS;

    public int getTrayQuantity() {
        return trayQuantity;
    }

    public void setTrayQuantity(int trayQuantity) {
        this.trayQuantity = trayQuantity;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<OutboundItemDTOS> getOutboundItemDTOS() {
        return outboundItemDTOS;
    }

    public void setOutboundItemDTOS(List<OutboundItemDTOS> outboundItemDTOS) {
        this.outboundItemDTOS = outboundItemDTOS;
    }

    public static class OutboundItemDTOS {
        private String code;
        private Integer loadingCarQuantity;

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public Integer getLoadingCarQuantity() {
            return loadingCarQuantity;
        }

        public void setLoadingCarQuantity(Integer loadingCarQuantity) {
            this.loadingCarQuantity = loadingCarQuantity;
        }
    }
}
