package com.yang.natives

object NativeBean {

    init {
        System.loadLibrary("ndkdemo")
    }

    external fun stringFromJNI():String
}




