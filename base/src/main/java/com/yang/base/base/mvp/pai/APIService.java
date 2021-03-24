package com.yang.base.base.mvp.pai;

import io.reactivex.Flowable;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface APIService {
    /**
     * 我的评价
     */
    @GET("toutiao/index")
    Flowable<ResponseBody> loadUs(@Query("type")  String type,
                                  @Query("key")String key);
}
