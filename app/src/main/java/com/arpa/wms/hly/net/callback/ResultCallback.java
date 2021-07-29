package com.arpa.wms.hly.net.callback;

import com.arpa.wms.hly.bean.base.Result;

public abstract class ResultCallback <T> extends WrapApiCallback<Result<T>> {
    @Override
    public void onResponse(Result<T> data) {
        onSuccess(data.getData());
    }

    public abstract void onSuccess(T data);
}