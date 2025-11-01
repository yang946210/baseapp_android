package com.yang.ktbase.vm

import com.yang.ktbase.net.NetException
import com.yang.ktbase.net.ResponseData

typealias RequestBlock<T> = suspend () -> ResponseData<T>
typealias SuccessBlock<T> = (T) -> Unit
typealias ErrorBlock = (NetException) -> Unit

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
     * 成功
     */
    fun success(block: SuccessBlock<T>): RequestBuilder<T> = apply { onSuccess = block }

    /**
     * 失败
     */
    fun error(block: ErrorBlock): RequestBuilder<T> = apply { onError = block }

    /**
     * result=null时特殊处理
     */
    fun nullDefault(block: () -> T): RequestBuilder<T> = apply { nullDefault = block }
}

