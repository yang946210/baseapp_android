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

    fun request(block: RequestBlock<T>): RequestBuilder<T> = apply { request = block }
    fun success(block: SuccessBlock<T>): RequestBuilder<T> = apply { onSuccess = block }
    fun error(block: ErrorBlock): RequestBuilder<T> = apply { onError = block }
}

