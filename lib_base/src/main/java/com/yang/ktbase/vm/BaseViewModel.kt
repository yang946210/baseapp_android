package com.yang.ktbase.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import com.yang.ktbase.net.NetException
import com.yang.ktbase.net.ResponseData

/**
 * UI 状态封装
 */
sealed class UiState {
    object Idle : UiState()
    object Loading : UiState()
    data class Success<T>(val data: T) : UiState()
    data class Error(val message: String) : UiState()
}

/**
 * Base ViewModel
 * 封装网络请求方法
 */
open class BaseViewModel : ViewModel() {

    // 公共 UI 状态，Activity/Fragment 订阅
    private val _uiState = MutableStateFlow<UiState>(UiState.Idle)
    val uiState: StateFlow<UiState> = _uiState

    private val safeScope: CoroutineScope
        get() = viewModelScope


    /**
     * 一次性网络请求
     */
    fun <T> launchRequest(
        reqCall: suspend () -> ResponseData<T>,
        onSuccess: (T) -> Unit = {},
        onError: (NetException) -> Unit = {}
    ) {
        safeScope.launch {
            try {
                _uiState.value = UiState.Loading
                val result = reqCall()
                result.parseData(
                    onError = {
                        _uiState.value = UiState.Error(it.message ?: "未知错误")
                        onError(it)
                    },
                    onSuccess = {
                        _uiState.value = UiState.Success(it)
                        onSuccess(it)
                    }
                )
            } catch (e: Throwable) {
                val netEx = if (e is NetException) e else NetException("request error: ${e.message}")
                _uiState.value = UiState.Error(netEx.message ?: "未知错误")
                onError(netEx)
            }
        }
    }
}

/**
 * ResponseData 扩展解析方法
 */
private inline fun <T> ResponseData<T>.parseData(
    onError: (NetException) -> Unit,
    onSuccess: (T) -> Unit
) {
    runCatching {
        if (isSuccess) {
            data?.let(onSuccess) ?: onError(NetException(-1, "response data is null"))
        } else throw NetException(errorCode, errorMsg)
    }.onFailure {
        val e = if (it is NetException) it else NetException("response error: ${it.message}")
        onError(e)
    }
}




