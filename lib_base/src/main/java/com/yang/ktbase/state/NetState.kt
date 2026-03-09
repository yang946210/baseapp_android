package com.yang.ktbase.state

/**
 * NET状态
 */
sealed class NetState<out T> {

    object Idle : NetState<Nothing>()

    object Prepare: NetState<Nothing>()

    data class Success<T>(val data: T) : NetState<T>()

    data class Error(val throwable: Throwable) : NetState<Nothing>()
}


/**
 * NET DLS
 */
class NetStateDsl<T> {

    var onPrepare: (() -> Unit)? = null

    var onSuccess: ((T) -> Unit) = { }

    var onError: ((Throwable) -> Unit)? = null

    fun prepare(block: () -> Unit) { onPrepare = block }

    fun success(block: (T) -> Unit) { onSuccess = block }

    fun error(block: (Throwable) -> Unit) { onError = block }
}
