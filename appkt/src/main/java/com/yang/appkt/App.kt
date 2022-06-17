package com.yang.appkt

import android.app.Application
import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.ViewModelStoreOwner
import okio.ByteString
import okio.ByteString.Companion.decodeBase64
import okio.ByteString.Companion.encode
import java.util.*

class App : Application() , ViewModelStoreOwner {

    var string="shi shi shi"

    private val appViewModelStore: ViewModelStore by lazy {
         ViewModelStore()
    }

    override fun getViewModelStore(): ViewModelStore {
        return appViewModelStore
    }


}