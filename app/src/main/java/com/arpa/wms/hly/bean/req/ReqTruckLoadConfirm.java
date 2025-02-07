package com.arpa.wms.hly.bean.req;

import android.text.TextUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * author: 李一方(<a href="mailto:leergo@dingtalk.com">leergo@dingtalk.com</a>)<br/>
 * version: 1.0.0<br/>
 * since: 2021-05-18 10:49
 */
public class ReqTruckLoadConfirm {
    private String code;
     private Integer trayQuantity;
    private List<OutboundItemDTOS> outboundItemDTOS;

    public Integer getTrayQuantity() {
        return trayQuantity;
    }

    public void setTrayQuantity(Integer trayQuantity) {
        this.trayQuantity = trayQuantity;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<OutboundItemDTOS> getOutboundItemDTOS() {
        if (outboundItemDTOS == null) outboundItemDTOS = new ArrayList<>();
        return outboundItemDTOS;
    }

    public void setOutboundItemDTOS(List<OutboundItemDTOS> outboundItemDTOS) {
        this.outboundItemDTOS = outboundItemDTOS;
    }

    public static class OutboundItemDTOS {
        private String code;
        private int loadingCarQuantity;

        public OutboundItemDTOS(String code, String loadingCarQuantity) {
            this.code = code;
            // FIXME: loadingCarQuantity == null @lyf 2021-06-03 03:20:38
            this.loadingCarQuantity = TextUtils.isEmpty(loadingCarQuantity) ? 0 : Integer.parseInt(loadingCarQuantity);
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public int getLoadingCarQuantity() {
            return loadingCarQuantity;
        }

        public void setLoadingCarQuantity(int loadingCarQuantity) {
            this.loadingCarQuantity = loadingCarQuantity;
        }
    }
}
