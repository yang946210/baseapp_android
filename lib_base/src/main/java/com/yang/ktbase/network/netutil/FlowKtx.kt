package com.yang.ktbase.network.netutil

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.yang.ktbase.network.entity.ApiResponse
import com.yang.ktbase.network.ResultListener
import com.yang.ktbase.network.`interface`.UiView
import com.yang.ktbase.network.parseData
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch


/**
 * 标准请求
 */
fun UiView.launchIn(showLoading: Boolean = false,requestBlock: suspend () -> Unit) {
    lifecycleScope.launch {
        delay(2000)
        flow {
            emit(requestBlock())
        }.onStart {
            if (showLoading)showLoading()
        }.onCompletion {
            if (showLoading)hideLoading()
        }.collect()
    }
}

/**
 * 标准返回
 */
fun <T> Flow<ApiResponse<T>>.collectIn(
    lifecycleOwner: LifecycleOwner,
    minActiveState: Lifecycle.State = Lifecycle.State.STARTED,
    listenerBuilder: ResultListener<T>.() -> Unit
): Job = lifecycleOwner.lifecycleScope.launch {
    flowWithLifecycle(
        lifecycleOwner.lifecycle,
        minActiveState
    ).collect { apiResponse: ApiResponse<T> ->
        apiResponse.parseData(listenerBuilder)
    }
}


/**
 * 简易请求，返回链式调用
 */
fun <T> UiView.launchAndCollect(
    requestBlock: suspend () -> ApiResponse<T>,
    showLoading: Boolean = false,
    listenerBuilder: ResultListener<T>.() -> Unit,
) {
    lifecycleScope.launch {
        flow {
            emit(requestBlock())
        }.onStart {
            if (showLoading) showLoading()
        }.onCompletion {
            if (showLoading) hideLoading()
        }.collect { response ->
            response.parseData(listenerBuilder)
        }
    }
}







