package com.yang.appkt

import android.app.Application
import android.content.Context
import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.ViewModelStoreOwner

object App : Application() , ViewModelStoreOwner {


    var string="shi shi shi"

    private val appViewModelStore: ViewModelStore by lazy {
         ViewModelStore()
    }

    override fun getViewModelStore(): ViewModelStore {
        return appViewModelStore
    }


}