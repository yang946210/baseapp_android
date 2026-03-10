package com.yang.app

import android.webkit.WebView
import com.yang.app.network.RetrofitApi
import com.yang.ktbase.base.BaseApp
import com.yang.app.util.FlutterEngineManager

class App : BaseApp() {
    override fun onCreate() {
        super.onCreate()
        RetrofitApi.init(
            baseUrl = AppConfig.BASE_URL,
            cacheDir = cacheDir
        )
        WebView.setWebContentsDebuggingEnabled(true)
        // 初始化 Flutter 引擎
        FlutterEngineManager.init(this)
    }
}
