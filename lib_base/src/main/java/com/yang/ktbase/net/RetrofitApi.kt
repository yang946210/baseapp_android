package com.yang.ktbase.net

import com.yang.base.BuildConfig
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit

object RetrofitApi {

    /**
     * 创建OkHttpClient
     */
    private val okHttpClient: OkHttpClient  by lazy{
        val builder = OkHttpClient().newBuilder()
            .callTimeout(5, TimeUnit.SECONDS)  //完整请求超时时间
            .connectTimeout(5, TimeUnit.SECONDS)  //与服务器建立连接时长
            .readTimeout(5, TimeUnit.SECONDS)  //读取服务器返回数据时长
            .writeTimeout(5, TimeUnit.SECONDS)  //像服务器写入数据时长
            .retryOnConnectionFailure(false)  //允许超时重连
            .followRedirects(true)  //允许重定向
            .cache(Cache(File("sdcard/cache", "okhttp"), 1024))
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = when (BuildConfig.DEBUG) {
                    true  ->HttpLoggingInterceptor.Level.BODY
                    false ->HttpLoggingInterceptor.Level.BASIC
                }
            })
        builder.build()
    }

    /**
     * 创建Retrofit
     */
    private val retrofit:Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }


    /**
     * 获取service实例
     */
    val api: ApiService get() =  retrofit.create(ApiService::class.java)

}