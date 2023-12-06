package com.yang.ktbase

import android.app.Application

object BaseHelper {

    private lateinit var application:Application

    fun init(application: Application){
        this.application=application
    }

    fun getApplicationContext():Application{
        return application;
    }

}