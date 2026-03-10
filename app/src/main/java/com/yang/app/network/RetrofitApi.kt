package com.yang.app.network

import com.yang.app.BuildConfig
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit

object RetrofitApi {

    private var baseUrl: String = ""

    private var cacheDir: File? = null

    fun init(baseUrl: String, cacheDir: File) {
        this.baseUrl = baseUrl
        this.cacheDir = cacheDir
    }

    private val okHttpClient: OkHttpClient by lazy {
        val dir = cacheDir ?: throw IllegalStateException("RetrofitApi not initialized, please call init() first")
        val cacheDirectory = File(dir, "okhttp").apply { mkdirs() }
        
        OkHttpClient().newBuilder()
            .callTimeout(5, TimeUnit.SECONDS)
            .connectTimeout(5, TimeUnit.SECONDS)
            .readTimeout(5, TimeUnit.SECONDS)
            .writeTimeout(5, TimeUnit.SECONDS)
            .retryOnConnectionFailure(false)
            .followRedirects(true)
            .cache(Cache(cacheDirectory, 10L * 1024 * 1024))
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = if (BuildConfig.DEBUG) {
                    HttpLoggingInterceptor.Level.BODY
                } else {
                    HttpLoggingInterceptor.Level.BASIC
                }
            })
            .build()
    }

    private val retrofit: Retrofit by lazy {
        if (baseUrl.isEmpty()) {
            throw IllegalStateException("RetrofitApi not initialized, please call init() first")
        }
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val api: ApiService get() = retrofit.create(ApiService::class.java)
}
