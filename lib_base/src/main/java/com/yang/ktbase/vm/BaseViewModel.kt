package com.yang.ktbase.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import com.yang.ktbase.net.NetException


/**
 * Base ViewModel
 * 封装网络请求方法
 */
open class BaseViewModel : ViewModel() {


    /**
     * 公共 UI状态，在Activity/Fragment 订阅
     */
    protected val _uiState = MutableStateFlow<UiState>(UiState.Idle)
    val uiState: StateFlow<UiState> = _uiState


    /**
     * 请求入口
     */
    fun <T> launch(build: RequestBuilder<T>.() -> Unit) {
        val rb = RequestBuilder<T>().apply(build)
        val req = requireNotNull(rb.request) { "request must be set " }

        viewModelScope.launch {
            try {
                _uiState.value = UiState.ShowLoading
                val result = req()
                result.parseData(
                    onSuccess = {
                        _uiState.value = UiState.HideLoading
                        rb.onSuccess(it)
                    },
                    onError = {
                        _uiState.value = UiState.Error(it.message)
                        rb.onError(it)
                    },
                    onNullResult = rb.nullDefault
                )
            } catch (e: Throwable) {
                e.printStackTrace()
                val netEx = if (e is NetException) e else NetException("系统错误: ${e.message}")
                _uiState.value = UiState.Error(netEx.message)
                rb.onError(netEx)
            }
        }
    }
}



/**
 * UI 状态封装
 */
sealed class UiState {
    //初始
    object Idle : UiState()
    //加载中
    object ShowLoading : UiState()
    //加载成功
    object HideLoading: UiState()
    //加载失败
    data class Error(val message: String?="未知错误") : UiState()
}



