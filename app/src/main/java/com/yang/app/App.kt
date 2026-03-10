package com.yang.app

import android.webkit.WebView
import com.yang.app.network.RetrofitApi
import com.yang.ktbase.base.BaseApp

class App : BaseApp() {
    override fun onCreate() {
        super.onCreate()
        RetrofitApi.init(
            baseUrl = "http://www.wanandroid.com",
            cacheDir = cacheDir
        )
        WebView.setWebContentsDebuggingEnabled(true)
    }
}
