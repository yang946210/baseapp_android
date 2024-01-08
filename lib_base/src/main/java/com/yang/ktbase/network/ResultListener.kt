package com.yang.ktbase.network

import com.aisier.network.entity.*

/**
 *
 */
fun <T> ApiResponse<T>.parseData(listenerBuilder: ResultListener<T>.() -> Unit) {
    val listener = ResultListener<T>().also(listenerBuilder)
    when (this) {
        is ApiSuccessResponse -> listener.onSuccess(this.response)
        is ApiEmptyResponse -> listener.onDataEmpty()
        is ApiFailedResponse -> listener.onFailed(this.errorCode, this.errorMsg)
        is ApiErrorResponse -> listener.onError(this.throwable)
    }
    listener.onComplete()
}

class ResultListener<T> {
    var onSuccess: (data: T?) -> Unit = {}
    var onDataEmpty: () -> Unit = {}
    var onFailed: (errorCode: Int?, errorMsg: String?) -> Unit = { _, _ -> }
    var onError: (e: Throwable) -> Unit = { _ -> }
    var onComplete: () -> Unit = {}
}