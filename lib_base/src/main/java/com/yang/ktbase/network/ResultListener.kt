package com.yang.ktbase.network

import com.yang.ktbase.network.entity.ApiErrorResponse
import com.yang.ktbase.network.entity.ApiFailedResponse
import com.yang.ktbase.network.entity.ApiResponse
import com.yang.ktbase.network.entity.ApiSuccessResponse
import com.yang.ktbase.util.logD

/**
 *
 */
fun <T> ApiResponse<T>.parseData(listenerBuilder: ResultListener<T>.() -> Unit) {
    val listener = ResultListener<T>().also(listenerBuilder)
    when (this) {
        is ApiSuccessResponse -> listener.onSuccess(this.response)
        is ApiFailedResponse -> listener.onFailed(this.errorCode, this.errorMsg)
        is ApiErrorResponse -> listener.onError(this.throwable)
    }
    listener.onComplete()
}

class ResultListener<T> {
    var onSuccess: (data: T?) -> Unit = {}
    var onFailed: (errorCode: Int?, errorMsg: String?) -> Unit = { _, _ -> }
    var onError: (e: Throwable) -> Unit = { it.message?.logD(tag = "") }
    var onComplete: () -> Unit = {}
}