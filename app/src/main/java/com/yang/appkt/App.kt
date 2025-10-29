package com.yang.appkt

import android.webkit.WebView
import com.yang.appkt.util.FlutterEngineManager
import com.yang.ktbase.base.BaseApp

class App : BaseApp(){
    override fun onCreate() {
        super.onCreate()
        WebView.setWebContentsDebuggingEnabled(true)
        FlutterEngineManager.registerEngine(this)
    }
}