package com.yang.ktbase.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import com.yang.ktbase.network.ResponseData
import com.yang.ktbase.network.dataOrThrow
import com.yang.ktbase.state.LoadingState
import com.yang.ktbase.state.NetState


/**
 * Base ViewModel
 * 封装网络请求方法
 */
open class BaseViewModel : ViewModel() {

    /**
     * 公共 UI状态，在Activity/Fragment 订阅
     */
     val loadingState = MutableStateFlow<LoadingState<Nothing>>(LoadingState.Idle)


    fun <T> launchRequest(
        state: MutableStateFlow<NetState<T>>,
        showLoading: Boolean = true,
        loadingMsg: String = "加载中...",
        block: suspend () -> ResponseData<T>
    ) {
        viewModelScope.launch {
            if (showLoading) loadingState.value = LoadingState.Loading(true, loadingMsg)
            try {
                state.value = NetState.Prepare
                val response = block()
                val data = response.dataOrThrow()
                state.value = NetState.Success(data)
            } catch (e: Exception) {
                state.value = NetState.Error(e)
            } finally {
                if (showLoading) loadingState.value = LoadingState.Idle
            }
        }
    }
}







