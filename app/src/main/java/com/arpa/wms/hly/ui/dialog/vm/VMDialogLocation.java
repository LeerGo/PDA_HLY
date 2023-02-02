package com.arpa.wms.hly.ui.dialog.vm;

import android.app.Application;
import android.os.Message;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;

import com.arpa.and.arch.BR;
import com.arpa.and.arch.base.BaseModel;
import com.arpa.wms.hly.R;
import com.arpa.wms.hly.base.viewmodel.VMBaseRefreshList;
import com.arpa.wms.hly.bean.base.ReqPage;
import com.arpa.wms.hly.bean.base.ResultPage;
import com.arpa.wms.hly.bean.req.ReqLocSearch;
import com.arpa.wms.hly.ui.listener.ViewListener;
import com.arpa.wms.hly.utils.Const;

import java.util.Map;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import me.tatarka.bindingcollectionadapter2.ItemBinding;
import retrofit2.Call;

/**
 * author: 李一方(<a href="mailto:leergo@dingtalk.com">leergo@dingtalk.com</a>)<br/>
 * version: 1.0.0<br/>
 * since: 2023-02-02 13:57
 *
 * <p>
 * vm：库位选择
 * </p>
 */
@HiltViewModel
public class VMDialogLocation extends VMBaseRefreshList<String> {
    public ObservableField<String> keyWord = new ObservableField<>();
    private final ReqLocSearch req = new ReqLocSearch(PAGE_SIZE);

    @Inject
    public VMDialogLocation(@NonNull Application application, BaseModel model) {
        super(application, model);
    }

    @Override
    public Call<ResultPage<String>> getCall(Map<String, Object> params) {
        return apiService.findLocation(params);
    }

    @Override
    public ReqPage getParams() {
        return req;
    }

    @Override
    public ItemBinding<String> getItemBinding() {
        ItemBinding<String> binding = ItemBinding.of(BR.data, R.layout.item_location);
        binding.bindExtra(BR.listener, (ViewListener.DataTransCallback<String>) data -> {
            Message msg = new Message();
            msg.obj = data;
            msg.what = Const.Message.MSG_DIALOG_DISMISS;
            sendSingleLiveEvent(msg);
        });
        return binding;
    }

    public void onSearch() {
        req.setSerialNumber(keyWord.get());
        autoRefresh();
    }

    public void close() {
        sendSingleLiveEvent(Const.Message.MSG_DIALOG_DISMISS);
    }
}
