package com.yang.ktbase.network

import com.google.gson.JsonArray
import com.google.gson.JsonObject
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("banner/json")
    suspend fun getBanner(): BaseResponse<JsonArray>

    @GET("user/lg/private_articles/{page}/json")
    suspend fun getData(@Path("page") pageId:Int): BaseResponse<JsonArray>

    @GET("article/list/{page}/json")
    suspend fun getAnt(@Path("page") pageNo: Int): BaseResponse<JsonObject>


}