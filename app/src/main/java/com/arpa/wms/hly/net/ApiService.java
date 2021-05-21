package com.arpa.wms.hly.net;

import com.arpa.wms.hly.bean.GoodsItemVO;
import com.arpa.wms.hly.bean.base.Result;
import com.arpa.wms.hly.bean.base.ResultPage;
import com.arpa.wms.hly.bean.req.ReqGoodTakeConfirm;
import com.arpa.wms.hly.bean.req.ReqMoveSure;
import com.arpa.wms.hly.bean.req.ReqTaskAssign;
import com.arpa.wms.hly.bean.req.ReqTruckLoadConfirm;
import com.arpa.wms.hly.bean.req.ReqTruckLoadDetail;
import com.arpa.wms.hly.bean.res.ResGoodTakeConfirm;
import com.arpa.wms.hly.bean.res.ResInventory;
import com.arpa.wms.hly.bean.res.ResLogin;
import com.arpa.wms.hly.bean.res.ResMoveGoods;
import com.arpa.wms.hly.bean.res.ResMoveGoodsSure;
import com.arpa.wms.hly.bean.res.ResMoveLocation;
import com.arpa.wms.hly.bean.res.ResTaskAssign;
import com.arpa.wms.hly.bean.res.ResTruckLoad;
import com.arpa.wms.hly.bean.res.ResTruckLoadConfirm;
import com.arpa.wms.hly.bean.res.ResWarehouse;
import com.arpa.wms.hly.utils.Const.AppConfig;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface ApiService {

    /**
     * 获取仓库
     */
    @Headers({"client_id: " + AppConfig.clientID, "client_secret: " + AppConfig.clientSecret})
    @GET(API.API_WAREHOUSE_AUTHORIZATION)
    Call<Result<List<ResWarehouse>>> getWarehouseWithoutAuth(@Query("userLoginId") String loginID);

    /**
     * 获取认证
     */
    // @DomainName(API.URL_KEY)
    @POST(API.API_AUTHORIZATION)
    @FormUrlEncoded
    Call<Result<ResLogin>> authorize(@FieldMap Map<String, Object> data);

    /**
     * 切换登录信息
     */
    @POST("arpa-basic-api/changeLogin")
    @FormUrlEncoded
    Call<Result<Object>> changeLogin(@FieldMap Map<String, Object> data);

    /**
     * 切换登录信息
     */
    @POST("arpa-basic-api/party/updatePass")
    @FormUrlEncoded
    Call<Result<Object>> updatePass(@FieldMap Map<String, Object> data);

    /**
     * 获取首页的任务任务列表
     */
    @GET("wms/pda/tasks")
    Call<ResultPage<ResTaskAssign>> pdaTasks(@QueryMap Map<String, Object> data);

    /**
     * PDA分配装卸工、叉车工、保管员
     */
    @POST("wms/pda/tasks/assignPDA")
    Call<Result<Object>> pdaTasksAssign(@Body ReqTaskAssign reqTaskAssign);

    /**
     * PDA取消分配装卸工、叉车工、保管员
     */
    @POST("wms/pda/tasks/cancelAssign")
    Call<Result<Object>> pdaTasksCancelAssign(@Body ReqTaskAssign reqTaskAssign);

    /**
     * 收货列表接口
     */
    @GET("wms/pda/receive/list")
    Call<ResultPage<ResTaskAssign>> goodsReceiveList(@QueryMap Map<String, Object> data);

    /**
     * 收货详情接口上方数据
     */
    @GET("wms/pda/receive/receiveDetailsAbouve")
    Call<Result<ResTaskAssign>> receiveDetailsAbove(@Query("receiveCode") String receiveCode);

    /**
     * 收货详情接口下方数据
     */
    @GET("wms/pda/receive/receiveDetailsBelow")
    Call<Result<List<GoodsItemVO>>> receiveDetailsBelow(@QueryMap Map<String, Object> data);

    /**
     * 收货登记查询明细
     */
    @GET("wms/pda/receive/register")
    Call<Result<ResGoodTakeConfirm>> takeRegisterDetail(@QueryMap Map<String, Object> data);

    /**
     * PDA收货登记确认
     */
    @GET("wms/pda/receive/confirm")
    Call<Result<Object>> takeSingleConfirm(@Body ReqGoodTakeConfirm reqGoodTakeConfirm);

    /**
     * PDA收货登记整单确认
     */
    @GET("wms/pda/receive/wholeConfirm")
    Call<Result<Object>> takeWholeConfirm(@Body ReqGoodTakeConfirm reqGoodTakeConfirm);

    /**
     * 复核列表接口
     */
    @GET("wms/pda/outbound/recheckList")
    Call<ResultPage<ResTaskAssign>> goodsRecheckList(@QueryMap Map<String, Object> data);

    /**
     * 查询复核列表详情接口上方数据
     */
    @GET("wms/pda/outbound/recheckItemListAbouve")
    Call<Result<ResTaskAssign>> recheckItemList(@Query("outboundCode") String outboundCode);

    /**
     * 查询复核列表详情接口下方数据
     */
    @GET("wms/pda/outbound/recheckItemListBelow")
    Call<Result<List<GoodsItemVO>>> recheckItemListBelow(@QueryMap Map<String, Object> data);

    /**
     * 查询单个商品复核详情
     */
    @GET("wms/pda/outbound/recheckRegisterDetail")
    Call<Result<GoodsItemVO>> recheckRegisterDetail(@QueryMap Map<String, Object> data);

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
     * 无计划扫描商品 - 列表
     */
    @GET("wms/pda/moveTask/goodsListDetail")
    Call<Result<ResMoveGoods>> scanGoodsListDetail(@QueryMap Map<String, Object> data);

    /**
     * 无计划扫描商品 - 详情
     */
    @GET("wms/pda/moveTask/goodsDetail")
    Call<Result<ResMoveGoodsSure>> scanGoodsDetail(@QueryMap Map<String, Object> data);

    /**
     * 无计划扫描商品 - 详情
     */
    @POST("wms/pda/moveTask/moveConfirm")
    Call<Result<ResMoveGoods>> scanGoodsSure(@Body ReqMoveSure reqMoveSure);

    /**
     * 获取装车列表
     */
    @POST("wms/pda/outbound/loadingCarTaskList")
    Call<Result<ResTruckLoad>> getTruckLoadList(@Body ReqTruckLoadDetail reqTruckLoadDetail);

    /**
     * 获取装车确认详情
     */
    @GET("wms/pda/outbound/loadingCarDetail")
    Call<Result<ResTruckLoadConfirm>> getTruckLoadConfirmDetail(@Query("outboundCode") String outboundCode);

    /**
     * 获取装车提交确认
     */
    @POST("wms/pda/outbound/loadingCarConfirm")
    Call<Result<Object>> confirmTruckLoad(@Body ReqTruckLoadConfirm reqTruckLoadConfirm);

    /**
     * API 请求地址、一些参数
     */
    interface API {
        String URL_KEY = "API-AUTH";

        /**
         * 仓储服务 API 服务地址
         */
        // String URL_WMS = "http://192.168.31.166/"; // 夏宝新
        String URL_WMS = "http://192.168.30.186/"; // 邵朱尧
        //  String URL_WMS = "http://49.4.71.215/"; // 标准版开发测试
        //  String URL_WMS = "http://192.168.31.144/"; // 508 内部服务器（原徐杨）

        /**
         * API：获取仓库
         */
        String API_WAREHOUSE_AUTHORIZATION = "wms/warehouse/warehouseAuthorization";
        /**
         * API：获取认证
         */
        String API_AUTHORIZATION = "arpa-basic-api/authorize";
    }
}
