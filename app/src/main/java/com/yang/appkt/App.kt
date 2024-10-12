package com.yang.appkt

import com.yang.appkt.util.FlutterEngineManager
import com.yang.ktbase.activity.BaseApp

class App : BaseApp(){
    override fun onCreate() {
        super.onCreate()
        FlutterEngineManager.registerEngine(this)
    }
}