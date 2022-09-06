package com.yang.natives

object NativeBean {v

    init {
        System.loadLibrary("ndkdemo")
    }

    external fun stringFromJNI():String
}




