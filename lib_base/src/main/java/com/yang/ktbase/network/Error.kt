package com.yang.ktbase.network


/**
 * 异常处理类封装
 */
data class NetException(var code: Int, override var message:String):Throwable(){
    constructor(error: Error) : this(error.code,error.msg)

}



enum class Error( val code: Int,  val msg: String) {

    /**
     * 返回数据为null
     */
    DATA_NULL(1001, "返回数据为null");


}