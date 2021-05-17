package com.arpa.wms.hly.net.callback;

import com.arpa.wms.hly.bean.base.ResultPage;

import java.util.List;

public abstract class ResultPageCallback <T> extends WrapApiCallback<ResultPage<T>> {

    @Override
    public void onResponse(ResultPage<T> data) {
        onSuccess(data.getData().getRecords());
    }

    public abstract void onSuccess(List<T> data);
}
