package com.arpa.wms.hly.net;

import com.arpa.wms.hly.bean.ReqLogin;
import com.arpa.wms.hly.bean.ResLogin;
import com.arpa.wms.hly.bean.ResWarehouse;
import com.arpa.wms.hly.bean.Result;
import com.arpa.wms.hly.utils.Const.API;
import com.king.retrofit.retrofithelper.DomainName;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;


/**
 *
 */
public interface ApiService {

    /**
     * 查询国内油价
     */
    //    .headers("client_id", "1e069e240a163e9d2a18f4111ewecpda")
    //    .headers("client_secret", "e7957728041b11ea97dffa163e9d2pda")
    @Headers({"client_id: 1e069e240a163e9d2a18f4111ewecpda", "client_secret: e7957728041b11ea97dffa163e9d2pda",})
    @GET("wms/warehouse/warehouseAuthorization")
    Call<Result<List<ResWarehouse>>> getWarehouseWithoutAuth(@Query("userLoginId") String loginID);


    @DomainName(API.URL_KEY)
    @POST("authorize")
    Call<Result<ResLogin>> login(@Body ReqLogin data);

    /**
     * 获取字典信息- 新华字典
     */
    //    @DomainName(Constants.DOMAIN_DICTIONARY)
    //    @GET("xhzd/query")
    //    Call<Result<DictionaryInfo>> getDictionaryInfo(@Query("key") String key, @Query("word") String word);

    /**
     * 动态改变 BaseUrl 示例
     */
    //    @DomainName(Constants.DOMAIN_JENLY)
    //    @Timeout(connectTimeout = 14, readTimeout = 15, writeTimeout = 14)
    //    @GET("api/city/hotCities.json")
    //    Call<List<Map<String, Object>>> getHotCities();
}
