package com.yang.appkt

import android.app.Application
import com.yang.ktbase.BaseHelper

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        BaseHelper.init(this)
    }
}