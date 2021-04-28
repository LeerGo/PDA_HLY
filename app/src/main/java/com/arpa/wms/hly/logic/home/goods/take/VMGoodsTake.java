package com.arpa.wms.hly.logic.home.goods.take;

import android.app.Application;
import android.util.Log;

import com.arpa.and.wms.arch.base.BaseModel;
import com.arpa.and.wms.arch.base.DataViewModel;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;
import androidx.hilt.lifecycle.ViewModelInject;

/**
 * author: 李一方(<a href="mailto:leergo@dingtalk.com">leergo@dingtalk.com</a>)<br/>
 * version: 1.0.0<br/>
 * since: 2021-04-25 2:03 PM
 *
 * <p>
 * ViewModel: 商品待收货类别
 * </p>
 */
public class VMGoodsTake extends DataViewModel {
    private final ObservableField<String> searHint = new ObservableField<>();

    @ViewModelInject
    public VMGoodsTake(@NonNull Application application, BaseModel model) {
        super(application, model);
    }

    @Override
    public void onCreate() {
        super.onCreate();

        searHint.set("请扫描/输入任务号");
    }

    /**
     * 检索列表数据
     *
     * @param keyWord
     *         关键词
     */
    public void search(String keyWord) {
        Log.e("@@@@ L33", ":search() -> keyWord = " + keyWord);
    }

    public ObservableField<String> getSearHint() {
        return searHint;
    }
}
