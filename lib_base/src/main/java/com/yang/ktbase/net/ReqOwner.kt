package com.yang.ktbase.net

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch


/**
 * 请求挂载组件，哪个activity或fragment要用就实现这个
 */
interface ReqOwner : LifecycleOwner {

    fun showLoading(msg:String="加载中...")

    fun hideLoading()

}


/**
 * 请求/返回链式调用（mvvm的话最好用下面的标准些）
 */
fun <T> ReqOwner.requestWithCollect(
    reqCall: suspend () -> ResponseData<T>,
    showLoading: Boolean = false,
    loadingMsg:String="加载中",
    onError: (NetException) -> Unit = {},
    onSuccess: (T) -> Unit
) {
    lifecycleScope.launch {
        flow {
            emit(reqCall())
        }.onStart {
            if (showLoading) showLoading(loadingMsg)
        }.onCompletion {
            it?.run {
                onError(NetException("flow error：${it.message}"))
            }
            if (showLoading) hideLoading()
        }.collect {
            it.parseData(onError,onSuccess)
        }
    }
}



/**
 * 发送请求
 */
fun ReqOwner.request(
    showLoading: Boolean = false,
    loadingMsg:String="加载中",
    reqCall: suspend () -> Unit) {
    lifecycleScope.launch {
        flow {
            emit(reqCall())
        }.onStart {
            if (showLoading) showLoading(loadingMsg)
        }.onCompletion {
            if (showLoading) hideLoading()
        }.collect()
    }
}

/**
 * 获取请求数据
 */
fun <T> Flow<ResponseData<T>>.collectIn(
    lifecycleOwner: LifecycleOwner,
    minActiveState: Lifecycle.State = Lifecycle.State.STARTED,
    onError: (NetException) -> Unit = {},
    onSuccess: (T) -> Unit
): Job = lifecycleOwner.lifecycleScope.launch {
    flowWithLifecycle(
        lifecycleOwner.lifecycle,
        minActiveState
    ).collect {
        it.parseData(onError,onSuccess)
    }
}


/**
 * 返回200之后再按业务层判断是否真的成功。
 */
private inline fun <T> ResponseData<T>.parseData(onError: (NetException) -> Unit = {}, onSuccess: (T) -> Unit) {
    runCatching {
        if (isSuccess) {
            onSuccess(data!!)
        } else throw NetException(errorCode,errorMsg)
    }.onFailure {
        if (it is NetException) {
            onError(it)
        } else {
            onError(NetException("response error:${it.message}"))
        }
    }
}




