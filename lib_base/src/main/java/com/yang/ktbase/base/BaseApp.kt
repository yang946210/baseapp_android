package com.yang.ktbase.base

import android.app.Application
import android.content.Context

/**
 * App基类
 */
abstract class BaseApp :Application(){

    companion object{

        private lateinit var instance: BaseApp

        /**
         * 全局获取AppCtx
         */
        fun getAppCtx(): Context {
            return instance.applicationContext
        }

    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

}