package com.arpa.wms.hly.logic.home.goods.take;

import android.app.Application;

import com.arpa.and.wms.arch.base.BaseModel;
import com.arpa.and.wms.arch.util.GsonUtils;
import com.arpa.wms.hly.BR;
import com.arpa.wms.hly.R;
import com.arpa.wms.hly.base.viewmodel.VMBaseRefreshList;
import com.arpa.wms.hly.bean.base.ResultPage;
import com.arpa.wms.hly.bean.req.ReqTaskList;
import com.arpa.wms.hly.bean.res.ResTaskList;
import com.arpa.wms.hly.utils.Const.JOB_STATUS;
import com.arpa.wms.hly.utils.Const.SPKEY;
import com.arpa.wms.hly.utils.Const.TASK_TYPE;

import java.util.Map;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;
import androidx.hilt.lifecycle.ViewModelInject;
import me.tatarka.bindingcollectionadapter2.BindingRecyclerViewAdapter;
import me.tatarka.bindingcollectionadapter2.ItemBinding;
import retrofit2.Call;

/**
 * author: 李一方(<a href="mailto:leergo@dingtalk.com">leergo@dingtalk.com</a>)<br/>
 * version: 1.0.0<br/>
 * since: 2021-04-25 2:03 PM
 *
 * <p>
 * ViewModel: 商品待收货类别
 * </p>
 */
public class VMGoodsTake extends VMBaseRefreshList<ResTaskList, BindingRecyclerViewAdapter<ResTaskList>> {
    private final ObservableField<String> searHint = new ObservableField<>();
    private ReqTaskList reqTaskList = new ReqTaskList(page, PAGE_SIZE);

    @ViewModelInject
    public VMGoodsTake(@NonNull Application application, BaseModel model) {
        super(application, model);
    }

    @Override
    public void onCreate() {
        super.onCreate();

        searHint.set("请扫描/输入任务号");
    }

    @Override
    public Call<ResultPage<ResTaskList>> getCall(Map params) {
        return apiService.pdaTasks(params);
    }

    @Override
    protected Map getParams() {
        reqTaskList.setJobStatus(JOB_STATUS.UNFINISHED);
        reqTaskList.setTaskType(TASK_TYPE.RECEIVE);
        reqTaskList.setWarehouseCode(spGetString(SPKEY.WAREHOUSE_CODE));
        return GsonUtils.getInstance().pojo2Map(reqTaskList);
    }

    @Override
    public ItemBinding<ResTaskList> getItemBinding() {
        return ItemBinding.of(BR.data, R.layout.item_task_type);
    }

    /**
     * 检索列表数据
     *
     * @param keyWord
     *         关键词
     */
    public void search(String keyWord) {
        reqTaskList.setCode(keyWord);
        refresh();
    }

    public ObservableField<String> getSearHint() {
        return searHint;
    }
}
