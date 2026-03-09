package com.yang.ktbase.state

/**
 * 页面loading状态
 */
sealed class LoadingState<out T> {

    object Idle : LoadingState<Nothing>()

    data class Loading(val show: Boolean = true, val msg: String = "加载中...") : LoadingState<Nothing>()
}
