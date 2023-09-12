package com.yang.appkt

import android.app.Application
import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.ViewModelStoreOwner
import com.tencent.mmkv.MMKV

class App : Application() {


    override fun onCreate() {
        super.onCreate()
        MMKV.initialize(this)
    }


}