package com.yang.natives

object NativeBean {

    var eventId:String="EV_2548736"

    /**
     * 初始化引用so
     */
    init {
        System.loadLibrary("ndkdemo")
    }


    external fun stringFromJNI():String

    external fun intFromJNI(name:String,age:Int,point:Float):String

    external fun floatFromJIN(age: Array<Int>):Array<Int>

    external fun classFromJin(bean:StudyBean):String
}

data class StudyBean(val name: String, val age: Int)




