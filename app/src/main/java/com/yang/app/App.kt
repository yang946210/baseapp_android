package com.yang.app

import android.webkit.WebView
import com.yang.app.network.RetrofitApi
import com.yang.ktbase.base.BaseApp

class App : BaseApp() {
    override fun onCreate() {
        super.onCreate()
        RetrofitApi.init(
            baseUrl = AppConfig.BASE_URL,
            cacheDir = cacheDir
        )
        WebView.setWebContentsDebuggingEnabled(true)
    }
}
