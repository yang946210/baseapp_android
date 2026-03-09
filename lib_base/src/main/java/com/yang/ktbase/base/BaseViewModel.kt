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
     * 请求入口
     */
    fun <T> request(
        uiState: MutableStateFlow<UiState<T>>,
        showLoading: Boolean = true,
        loadingMsg: String = "加载中...",
        block: suspend () -> ResponseData<T>
    ) {
        viewModelScope.launch {
            try {
                // 1. 准备/初始化状态
                uiState.value = UiState.Prepare

                // 2. 显示 Loading
                if (showLoading)uiState.value = UiState.Loading(true, loadingMsg)

                // 3. 执行请求
                val response = block()
                val data = response.dataOrThrow()

                // 4. 请求成功
                uiState.value = UiState.Success(data)
            } catch (e: Exception) {

                //请求失败
                uiState.value = UiState.Error(e)
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        LoadingManager.hide()
    }
}







