package com.yang.ktbase.base

import android.app.Activity
import android.content.Context
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.blankj.utilcode.util.ToastUtils
import com.yang.ktbase.network.UiState
import com.yang.ktbase.util.LoadingManager
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

/**
 * UI状态观察与 Loading 控制
 */
interface StateObserver : LifecycleOwner {

    /**
     * 展示弹窗(默认样式)
     */
    fun showLoading(msg: String = "加载中...") {
        getUiContext()?.let { ctx ->
            LoadingManager.show(ctx, msg)
        }
    }

    /**
     * 隐藏弹窗(默认样式)
     */
    fun hideLoading() {
        LoadingManager.hide()
    }

    /**
     * 错误处理
     */
    fun onErrorDefault(msg: String? = "未知错误") {
        ToastUtils.showLong(msg)
    }


    /**
     * context获取
     */
    private fun LifecycleOwner.getUiContext(): Context? {
        return when (this) {
            is Activity -> this
            is Fragment -> this.context
            else -> null
        }
    }


    /**
     * 基础 Flow
     * flow.observe {}
     */
    fun <T> Flow<T>.observe(
        followLifecycle: Boolean = true,
        collector: suspend (T) -> Unit
    ) {
        lifecycleScope.launch {
            if (followLifecycle) {
                repeatOnLifecycle(Lifecycle.State.STARTED) {
                    collectLatest(collector)
                }
            } else {
                collectLatest(collector)
            }
        }
    }

    /**
     * 基础 Flow 外添加一些默认处理
     */
    fun <T> Flow<UiState<T>>.observeState(
        followLifecycle: Boolean = true,
        onPrepare: () -> Unit = {},
        onError: (Throwable) -> Unit = { onErrorDefault(it.message) },
        onSuccess: (T) -> Unit,
    ) {
        this.observe(followLifecycle) { state ->
            when (state) {
                is UiState.Prepare -> onPrepare()
                is UiState.Loading -> {
                    if (state.show) showLoading(msg = state.msg) else hideLoading()
                }
                is UiState.Success -> {
                    hideLoading()
                    onSuccess(state.data)
                }
                is UiState.Error -> {
                    hideLoading()
                    onError(state.throwable)
                }
                is UiState.Idle -> {}
            }
        }
    }
}
