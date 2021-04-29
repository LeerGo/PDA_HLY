package com.arpa.and.wms.arch.http.callback;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.HttpException;
import retrofit2.Response;
import timber.log.Timber;

public abstract class ApiCallback <T> implements Callback<T> {
    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        // TODO: 在 status = 21332（登录验证失败,请重新登录） 情况下需要统一跳转登录 @lyf 2021-04-27 05:27:28
        if (response.isSuccessful()) {
            T result = response.body();
            // Timber.d("Response:" + result);
            onResponse(call, result);
        } else {
            onError(call, new HttpException(response));
        }
    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {
        Timber.w(t);
        onError(call, t);
    }

    public abstract void onResponse(Call<T> call, T result);

    public abstract void onError(Call<T> call, Throwable t);
}
