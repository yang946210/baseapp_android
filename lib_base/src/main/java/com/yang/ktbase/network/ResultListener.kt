package com.yang.ktbase.network

import com.yang.ktbase.util.logD

/**
 *
 */
fun <T> BaseResponse<T>.parseData(listenerBuilder: ResultListener<T>.() -> Unit) {
    val listener = ResultListener<T>().also(listenerBuilder)
    when (this) {
        is SuccessResponse -> listener.onSuccess(this.response)
        is FailedResponse -> listener.onFailed(this.errorCode, this.errorMsg)
        is ErrorResponse -> listener.onError(this.throwable)
    }
    listener.onComplete()
}

class ResultListener<T> {
    var onSuccess: (data: T) -> Unit = {}
    var onFailed: (errorCode: Int?, errorMsg: String?) -> Unit = { _, _ -> }
    var onError: (e: Throwable) -> Unit = { it.message?.logD(tag = "") }
    var onComplete: () -> Unit = {}
}