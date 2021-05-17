package com.arpa.wms.hly.net.callback;

import android.content.Intent;

import com.arpa.and.wms.arch.http.callback.ApiCallback;
import com.arpa.wms.hly.bean.base.ResultPage;
import com.arpa.wms.hly.logic.LoginActivity;
import com.arpa.wms.hly.net.exception.ResultError;
import com.arpa.wms.hly.utils.ToastUtils;
import com.arpa.wms.hly.utils.Utils;

import java.util.List;

import retrofit2.Call;

import static com.arpa.wms.hly.net.exception.ErrorCode.ERROR_CODE;
import static com.arpa.wms.hly.net.exception.ErrorCode.LOGIN_FAILED;

public abstract class ResultPageCallback <T> extends ApiCallback<ResultPage<T>> {

    @Override
    public void onResponse(Call<ResultPage<T>> call, ResultPage<T> result) {
        if (result.isSuccess()) onSuccess(result.getData().getRecords());
        else innerFailed(new ResultError(result.getStatus(), result.getMsg()));
        onFinish();
    }

    private void innerFailed(ResultError error) {
        // 在 status = 21332（登录验证失败,请重新登录） 情况下统一跳转登录
        if (error.getCode() == LOGIN_FAILED) {
            ToastUtils.showShort(error.getMessage());
            Intent intent = new Intent(Utils.getContext(), LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            Utils.getContext().startActivity(intent);
        } else {
            onFailed(error);
        }
    }

    @Override
    public void onError(Call<ResultPage<T>> call, Throwable t) {
        onFailed(new ResultError(ERROR_CODE, t.getMessage()));
        onFinish();
    }

    public abstract void onSuccess(List<T> data);

    public abstract void onFailed(ResultError error);

    public void onFinish() {
    }
}
