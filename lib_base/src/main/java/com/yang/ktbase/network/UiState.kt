package com.yang.ktbase.network

/**
 * NET状态
 */
sealed class UiState<out T> {

    //空闲
    object Idle : UiState<Nothing>()
    //请求前
    object Prepare: UiState<Nothing>()
    //请求中
    data class Loading(val show: Boolean = true, val msg: String = "加载中...") : UiState<Nothing>()
    //请求成功
    data class Success<T>(val data: T) : UiState<T>()
    //请求失败
    data class Error(val throwable: Throwable) : UiState<Nothing>()
}


/**
 * NET DLS
 */
class UiStateDsl<T> {

    var onPrepare: (() -> Unit)? = null
    var onSuccess: ((T) -> Unit) = { }
    var onError: ((Throwable) -> Unit)? = null

    fun prepare(block: () -> Unit) { onPrepare = block }
    fun success(block: (T) -> Unit) { onSuccess = block }
    fun error(block: (Throwable) -> Unit) { onError = block }
}



