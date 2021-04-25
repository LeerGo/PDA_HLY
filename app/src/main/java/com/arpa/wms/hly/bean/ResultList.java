package com.arpa.wms.hly.bean;

import java.util.List;

/**
 * author: 李一方(<a href="mailto:leergo@dingtalk.com">leergo@dingtalk.com</a>)<br/>
 * version: 1.0.0<br/>
 * since: 2021-04-25 1:41 PM
 *
 * <p>
 * 基础列表响应
 * </p>
 */
public class ResultList <T> {
    private int status;
    private String msg;
    private DataBean<T> data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public DataBean<T> getData() {
        return data;
    }

    public void setData(DataBean<T> data) {
        this.data = data;
    }

    public static class DataBean <T> {
        private int total;
        private int pageNum;
        private int pageSize;
        private List<T> records;
        private SumObjectBean sumObject;

        public int getTotal() {
            return total;
        }

        public int getPageNum() {
            return pageNum;
        }

        public int getPageSize() {
            return pageSize;
        }

        public List<T> getRecords() {
            return records;
        }

        public SumObjectBean getSumObject() {
            return sumObject;
        }

        public static class SumObjectBean {
            private int total;

            public int getTotal() {
                return total;
            }

            public void setTotal(int total) {
                this.total = total;
            }
        }
    }
}
