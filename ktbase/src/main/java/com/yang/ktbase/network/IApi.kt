package com.yang.ktbase.network

import com.google.gson.JsonArray
import retrofit2.http.Field
import retrofit2.http.GET

interface IApi {

    @GET("article/list/{page}/json")
    suspend fun login(@Field("username") username: String,@Field("password") password: String): ResponseResult<JsonArray>

    @GET("banner/json")
    suspend fun getBanner(): ResponseResult<JsonArray>

}