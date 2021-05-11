package com.arpa.wms.hly.net;

import com.arpa.wms.hly.bean.base.Result;
import com.arpa.wms.hly.bean.base.ResultPage;
import com.arpa.wms.hly.bean.res.ResGoodsTakeDetail;
import com.arpa.wms.hly.bean.res.ResInventory;
import com.arpa.wms.hly.bean.res.ResLogin;
import com.arpa.wms.hly.bean.res.ResMoveGoods;
import com.arpa.wms.hly.bean.res.ResMoveLocation;
import com.arpa.wms.hly.bean.res.ResPdaTask;
import com.arpa.wms.hly.bean.res.ResWarehouse;
import com.arpa.wms.hly.utils.Const.API;
import com.arpa.wms.hly.utils.Const.AppConfig;
import com.king.retrofit.retrofithelper.annotation.DomainName;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;


/**
 *
 */
public interface ApiService {

    /**
     * 查询国内油价
     */
    @Headers({"client_id: " + AppConfig.clientID, "client_secret: " + AppConfig.clientSecret})
    @GET(API.API_WAREHOUSE_AUTHORIZATION)
    Call<Result<List<ResWarehouse>>> getWarehouseWithoutAuth(@Query("userLoginId") String loginID);

    @DomainName(API.URL_KEY)
    @POST(API.API_AUTHORIZATION)
    @FormUrlEncoded
    Call<Result<ResLogin>> authorize(@FieldMap Map<String, Object> data);

    /**
     * 获取首页的任务任务列表
     */
    @GET("wms/pda/tasks")
    Call<ResultPage<ResPdaTask>> pdaTasks(@QueryMap Map<String, Object> data);

    /**
     * 获取收货任务详情列表
     */
    @GET("wms/pda/receive")
    Call<Result<List<ResGoodsTakeDetail>>> goodsTakeDetailList(@QueryMap Map<String, Object> data);

    /**
     * 库存查询
     */
    @GET("wms/inventory")
    Call<ResultPage<ResInventory>> inventoryQuery(@QueryMap Map<String, Object> data);

    /**
     * 无计划扫描库位
     */
    @GET("wms/pda/moveTask/scanLocation")
    Call<Result<ResMoveLocation>> scanLocation(@QueryMap Map<String, Object> data);

    /**
     * 无计划扫描商品
     */
    @GET("wms/pda/moveTask/scanGoods")
    Call<Result<ResMoveGoods>> scanGoods(@QueryMap Map<String, Object> data);

    /**
     * 无计划扫描商品 - 详情
     */
    @GET("wms/pda/moveTask/goodsListDetail")
    Call<Result<ResMoveGoods>> scanGoodsListDetail(@QueryMap Map<String, Object> data);
}
