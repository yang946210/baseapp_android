package com.yang.ktbase.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import com.yang.ktbase.network.ResponseData
import com.yang.ktbase.network.dataOrThrow
import com.yang.ktbase.network.UiState
import com.yang.ktbase.util.LoadingManager


/**
 * Base ViewModel
 * 封装网络请求方法
 */
open class BaseViewModel : ViewModel() {

    /**
     * 简化MutableStateFlow
     */
    fun <T> stateOf(): MutableStateFlow<UiState<T>> {
        return MutableStateFlow(UiState.Idle)
    }

    /**
     * 网络请求
     */
    fun <T> request(
        uiState: MutableStateFlow<UiState<T>>,
        showLoading: Boolean = true,
        loadingMsg: String = "加载中...",
        defaultNull: (() -> T)? = null,
        block: suspend () -> ResponseData<T>
    ) {
        viewModelScope.launch {
            try {
                //准备/初始化状态
                uiState.value = UiState.Prepare

                // Loading状态
                if (showLoading)uiState.value = UiState.Loading(true, loadingMsg)

                // 执行请求
                val response = block()
                val data = response.dataOrThrow()

                // 请求成功，data=null添加默认值(如果有设置)
                val finalData = data ?: defaultNull?.invoke() ?: checkNotNull(data) { "Response data is null" }

                // 4. 成功状态
                uiState.value = UiState.Success(finalData)
            } catch (e: Exception) {
                //状态状态
                uiState.value = UiState.Error(e)
            }
        }
    }

    /**
     * vm销毁
     */
    override fun onCleared() {
        super.onCleared()
        LoadingManager.hide()
    }
}







