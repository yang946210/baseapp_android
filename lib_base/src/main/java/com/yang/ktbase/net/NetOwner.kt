package com.yang.ktbase.net

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

/**
 * 网络请求能力接口
 * Activity / Fragment 实现即可使用
 */
interface NetOwner : LifecycleOwner {

    /**
     * 作用域
     */
    val safeScope: CoroutineScope get() = lifecycleScope

    /**
     * 生命周期
     */
    val safeLifecycle: Lifecycle get() = lifecycle

    /**
     * 显示 Loading
     */
    fun showLoading(msg: String = "加载中...")

    /**
     * 隐藏 Loading
     */
    fun hideLoading()

}

/**
 * 一次性请求（推荐 MVVM 调用方式）
 */
fun <T> NetOwner.requestWithCollect(
    reqCall: suspend () -> ResponseData<T>,
    showLoading: Boolean = false,
    loadingMsg: String = "加载中",
    onError: (NetException) -> Unit = {},
    onSuccess: (T) -> Unit
) {
    safeScope.launch {
        if (showLoading) showLoading(loadingMsg)
        try {
            val result = reqCall()
            result.parseData(onError, onSuccess)
        } catch (e: Throwable) {
            if (e is NetException) onError(e)
            else onError(NetException("request error: ${e.message}"))
        } finally {
            if (showLoading) hideLoading()
        }
    }
}

/**
 * 请求（无返回数据）
 */
fun NetOwner.request(
    reqCall: suspend () -> Unit,
    showLoading: Boolean = false,
    loadingMsg: String = "加载中",
) {
    safeScope.launch {
        if (showLoading) showLoading(loadingMsg)
        try {
            reqCall()
        } catch (e: Throwable) {
            e.printStackTrace()
        } finally {
            if (showLoading) hideLoading()
        }
    }
}

/**
 *  接收
 */
fun <T> Flow<ResponseData<T>>.collectIn(
    lifecycleOwner: LifecycleOwner,
    minActiveState: Lifecycle.State = Lifecycle.State.STARTED,
    onError: (NetException) -> Unit = {},
    onSuccess: (T) -> Unit
): Job = lifecycleOwner.lifecycleScope.launch {
    this@collectIn
        .flowWithLifecycle(lifecycleOwner.lifecycle, minActiveState) // ✅ 关键是这里的 this@collectIn
        .catch { e ->
            if (e is NetException) onError(e)
            else onError(NetException("flow error: ${e.message}"))
        }
        .collect { it.parseData(onError, onSuccess) }
}


/**
 * 解析 ResponseData 并分发成功或错误回调
 */
private inline fun <T> ResponseData<T>.parseData(
    onError: (NetException) -> Unit = {},
    onSuccess: (T) -> Unit
) {
    runCatching {
        if (isSuccess) {
            data?.let(onSuccess)
                ?: onError(NetException(-1, "response data is null"))
        } else throw NetException(errorCode, errorMsg)
    }.onFailure {
        if (it is NetException) onError(it)
        else onError(NetException("response error: ${it.message}"))
    }
}
