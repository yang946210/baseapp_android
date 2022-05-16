package com.yang.ktbase.network

import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit

object RetrofitApi {

    private const val url = "https://www.wanandroid.com/"

    private val api by lazy {
        val okHttpClient: OkHttpClient = OkHttpClient().newBuilder()
            .callTimeout(10, TimeUnit.SECONDS)  //完整请求超时时间
            .connectTimeout(10, TimeUnit.SECONDS)  //与服务器建立连接时长
            .readTimeout(10, TimeUnit.SECONDS)  //读取服务器返回数据时长
            .writeTimeout(10, TimeUnit.SECONDS)  //像服务器写入数据时长
            .retryOnConnectionFailure(true)  //允许超时重连
            .followRedirects(false)  //允许重定向
            .cache(Cache(File("sdcard/cache", "okhttp"), 1024))
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl(url)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        retrofit.create(IApi::class.java)
    }

    val get: IApi get() = api


}