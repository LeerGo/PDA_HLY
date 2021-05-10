package com.arpa.wms.hly.net;

import android.content.Intent;

import com.arpa.and.wms.arch.http.callback.ApiCallback;
import com.arpa.wms.hly.bean.base.Result;
import com.arpa.wms.hly.logic.LoginActivity;
import com.arpa.wms.hly.utils.Utils;

import retrofit2.Call;

import static com.arpa.wms.hly.net.HttpCode.ERROR_CODE;
import static com.arpa.wms.hly.net.HttpCode.LOGIN_FAILED;

// TODO: 在 status = 21332（登录验证失败,请重新登录） 情况下需要统一跳转登录 @lyf 2021-04-27 05:27:28
public abstract class ResultCallback <T> extends ApiCallback<Result<T>> {

    @Override
    public void onResponse(Call<Result<T>> call, Result<T> result) {
        if (result.isSuccess())
            onSuccess(result.getData());
        else
            innerFailed(new ResultError(result.getStatus(), result.getMsg()));
    }

    private void innerFailed(ResultError error) {
        // 在 status = 21332（登录验证失败,请重新登录） 情况下统一跳转登录
        if (error.getCode() == LOGIN_FAILED) {
            Intent intent = new Intent(Utils.getContext(), LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            Utils.getContext().startActivity(intent);
        } else {
            onFailed(error);
        }
    }

    public abstract void onFailed(ResultError error);

    @Override
    public void onError(Call<Result<T>> call, Throwable t) {
        onFailed(new ResultError(ERROR_CODE, t.getMessage()));
    }

    public abstract void onSuccess(T data);
}
