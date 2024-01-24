package com.yang.ktbase.net

import java.io.Serializable


/**
 * 请求返回实体封装
 */
data class ResponseData<out T>(val data: T? = null,
                           val errorCode: Int = -1,
                           val errorMsg: String = "error") : Serializable {
    val isSuccess: Boolean
        //get() = code == 0
        get() = true //实际场景决定

}

/**
 * 异常处理类封装
 */
data class NetException(var code: Int, override var message:String):Throwable(){
    constructor(message: String):this(-200,message)
    constructor(error: Error) : this(error.code,error.msg)
}


/**
 * 常见异常类型
 */
enum class Error( val code: Int,  val msg: String) {

    //返回数据为null
    DATA_NULL(1001, "返回数据为null");

}