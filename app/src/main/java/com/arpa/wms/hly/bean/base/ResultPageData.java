package com.arpa.wms.hly.bean.base;

import java.util.List;

public class ResultPageData <T> {
    private int total;
    private int pageNum;
    private int pageSize;
    private List<T> records;

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

    public boolean isFinish() {
        return (total <= (pageNum * pageSize))
                || (null != records && records.size() < pageSize);
    }

}
