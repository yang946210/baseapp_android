package com.yang.ktbase.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import com.yang.ktbase.network.ResponseData
import com.yang.ktbase.network.dataOrThrow


/**
 * Base ViewModel
 * 封装网络请求方法
 */
open class BaseViewModel : ViewModel() {

    /**
     * 公共 UI状态，在Activity/Fragment 订阅
     */
    val loadingState = MutableStateFlow(false)


    fun <T> launchRequest(
        state: MutableStateFlow<UiState2<T>>,
        showLoading:Boolean=false,
        block: suspend () -> ResponseData<T>
    ) {
        viewModelScope.launch {
            if (showLoading)loadingState.value = true
            try {
                val response = block()
                val data=response.dataOrThrow()
                state.value = UiState2.Success(data)
            } catch (e: Exception) {
                state.value = UiState2.Error(e)
            } finally {
                if (showLoading)loadingState.value = true
            }
        }
    }
}


sealed class UiState2<out T> {

    data class Success<T>(val data: T) : UiState2<T>()

    data class Error(val throwable: Throwable) : UiState2<Nothing>()
}



