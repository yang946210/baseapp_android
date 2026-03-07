package com.yang.ktbase.vm

import com.yang.ktbase.net.NetException
import com.yang.ktbase.net.ResponseData

typealias RequestBlock<T> = suspend () -> ResponseData<T>
typealias SuccessBlock<T> = suspend (T) -> Unit
typealias ErrorBlock = suspend (NetException) -> Unit

class RequestBuilder<T> {
    var request: RequestBlock<T>? = null
    var onSuccess: SuccessBlock<T> = {}
    var onError: ErrorBlock = {}
    var nullDefault: (() -> T)? = null


    /**
     * 请求体
     */
    fun request(block: RequestBlock<T>): RequestBuilder<T> = apply { request = block }

    /**
     * 请求成功
     */
    fun success(block: SuccessBlock<T>): RequestBuilder<T> = apply { onSuccess = block }

    /**
     * 请求失败
     */
    fun error(block: ErrorBlock): RequestBuilder<T> = apply { onError = block }

    /**
     * 请求成功 result=null时特殊处理
     */
    fun nullDefault(block: () -> T): RequestBuilder<T> = apply { nullDefault = block }
}