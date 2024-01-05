package com.example.jetpack.net

import com.google.gson.JsonArray
import com.google.gson.JsonObject
import com.yang.ktbase.net.ResponseResult
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("banner/json")
    suspend fun getBanner(): ResponseResult<JsonArray>

    @GET("user/lg/private_articles/{page}/json")
    suspend fun getData(@Path("page") pageId:Int): ResponseResult<JsonArray>

    @GET("article/list/{page}/json")
    suspend fun getAnt(@Path("page") pageNo: Int): ResponseResult<JsonObject>


}