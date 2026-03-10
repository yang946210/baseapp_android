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

interface ViewAbility {

    /**
     * 编译期验证继承是否合规
     */
    private val scopeLifecycleOwner: LifecycleOwner
        get() = (this as? LifecycleOwner)
            ?: throw IllegalStateException("ViewAbility 必须由 LifecycleOwner (Activity/Fragment) 实现")

    /**
     * 展示弹窗(默认样式)
     */
    fun showLoading(msg: String = "加载中...") {
        getUiContext(scopeLifecycleOwner)?.let { ctx ->
             LoadingManager.show(ctx, msg)
        }
    }

    /**
     * 隐藏弹窗
     */
    fun hideLoading() {
         LoadingManager.hide()
    }

    /**
     * 安全获取 Context
     */
    private fun getUiContext(owner: LifecycleOwner): Context? {
        return when (owner) {
            is Activity -> owner
            is Fragment -> owner.context
            else -> null
        }
    }

    /**
     * flow收集
     */
    fun <T> Flow<T>.observe(
        followLifecycle: Boolean = true,
        collector: suspend (T) -> Unit
    ) {
        val owner = scopeLifecycleOwner
        owner.lifecycleScope.launch {
            if (followLifecycle) {
                owner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                    collectLatest(collector)
                }
            } else {
                collectLatest(collector)
            }
        }
    }

    /**
     * uiState 更新
     */
    fun <T> Flow<UiState<T>>.observeState(
        followLifecycle: Boolean = true,
        onPrepare: () -> Unit = {},
        onError: (Throwable) -> Unit = {  ToastUtils.showLong(it.message) },
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