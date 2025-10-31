package com.yang.ktbase.vm

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.blankj.utilcode.util.ToastUtils
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

/**
 * 统一封装 UI 状态观察与 Loading 控制
 */
interface UiStateObserver : LifecycleOwner {


    /**
     * 展示弹窗(默认样式)
     */
    fun showLoading(msg: String = "加载中..."){
        val ctx = when (this) {
            is androidx.fragment.app.Fragment -> this.requireContext()
            is android.app.Activity -> this
            else -> return
        }
        DefaultLoadingManager.show(ctx, msg)
    }

    /**
     * 隐藏弹窗(默认样式)
     */
    fun hideLoading(){
        DefaultLoadingManager.hide()
    }


    /**
     * 展示错误信息
     */
    fun showErrorMsg(msg: String= "未知错误"){
        ToastUtils.showLong(msg)
    }

    /**
     * 订阅viewmodel中的状态及处理
     */
    fun observeUiState(viewModel: BaseViewModel) {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collectLatest { state ->
                    when (state) {
                        UiState.Loading -> showLoading()
                        UiState.Idle -> hideLoading()
                        is UiState.Success<*> -> hideLoading()
                        is UiState.Error -> {
                            hideLoading()
                            showErrorMsg("请求失败 ${state.message}")
                        }
                    }
                }
            }
        }
    }

    /**
     * 简化数据绑定
     */
    fun <T> LifecycleOwner.collectData(
        flow: StateFlow<T>,
        collector: suspend (T) -> Unit
    ) {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                flow.collect(collector)
            }
        }
    }
}