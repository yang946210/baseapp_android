package com.yang.base

/***
 * @desc 网络请求统一接口
 * @time 2022/5/11
 * @author yangguoq
 */

interface HttpApi {

    /**
     * get异步请求
     */
    fun get(params:Map<String,Any>,path:String,callback:IHttpCallback)

    /**
     * get同步请求
     */
    fun getSync(params:Map<String,Any>,path:String):Any?= Any()

    /**
     * post请求
     */
    fun post(params:Map<String,Any>,path:String,callback:IHttpCallback)

    /**
     * post同步请求
     */
    fun postSync(params:Map<String,Any>,path:String):Any?=Any()
}