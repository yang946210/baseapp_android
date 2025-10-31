package com.yang.ktbase.vm

import com.yang.ktbase.net.NetException
import com.yang.ktbase.net.ResponseData

class RequestBuilder<T> {
    var request: (suspend () -> ResponseData<T>)? = null
    var onSuccess: (T) -> Unit = {}
    var onError: (NetException) -> Unit = {}

    fun request(block: suspend () -> ResponseData<T>) { request = block }
    fun onSuccess(block: (T) -> Unit) { onSuccess = block }
    fun onError(block: (NetException) -> Unit) { onError = block }
}

