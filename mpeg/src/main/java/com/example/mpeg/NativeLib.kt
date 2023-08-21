package com.example.mpeg

//adb shell getprop ro.product.cpu.abi 查看cpu架构

object NativeLib {

    init {
        System.loadLibrary("mpeg")
    }

    /**
     * A native method that is implemented by the 'mpeg' native library,
     * which is packaged with this application.
     */
    external fun stringFromJNI(): String

}