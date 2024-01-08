package com.yang.ktbase.network.netutil

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.aisier.network.entity.ApiResponse
import com.yang.ktbase.network.ResultListener
import com.yang.ktbase.network.`interface`.UiView
import com.yang.ktbase.network.parseData
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch



fun UiView.launchIn(requestBlock: suspend () -> Unit) {
    lifecycleScope.launch {
        flow {
            emit(requestBlock())
        }.onStart {
            showLoading()
        }.onCompletion {
            hideLoading()
        }.collect()
    }
}


fun <T> Flow<ApiResponse<T>>.collectIn(
    lifecycleOwner: LifecycleOwner,
    minActiveState: Lifecycle.State = Lifecycle.State.STARTED,
    listenerBuilder: ResultListener<T>.() -> Unit,
): Job = lifecycleOwner.lifecycleScope.launch {
    flowWithLifecycle(
        lifecycleOwner.lifecycle,
        minActiveState
    ).collect { apiResponse: ApiResponse<T> ->
        apiResponse.parseData(listenerBuilder)
    }
}



/**
 * 请求带Loading&&不需要声明LiveData
 */
fun <T> UiView.launchAndCollect(
    showLoading: Boolean = false,
    requestBlock: suspend () -> ApiResponse<T>,
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







