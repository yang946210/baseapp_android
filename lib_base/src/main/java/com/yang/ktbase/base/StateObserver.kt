package com.yang.ktbase.base

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.blankj.utilcode.util.ToastUtils
import com.yang.ktbase.state.LoadingState
import com.yang.ktbase.state.NetState
import com.yang.ktbase.state.NetStateDsl
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
        val ctx = when (this) {
            is androidx.fragment.app.Fragment -> this.requireContext()
            is android.app.Activity -> this
            else -> return
        }
        LoadingManager.show(ctx, msg)
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
    fun onError(msg: String? = "未知错误"){
        ToastUtils.showLong(msg)
    }


    /**
     * 页面统一loading绑定
     */
    fun observeLoadingState(viewModel: BaseViewModel) {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.loadingState.collectLatest { state ->
                    when (state) {
                        is LoadingState.Idle -> hideLoading()
                        is LoadingState.Loading -> {
                            if (state.show) showLoading(state.msg)
                            else hideLoading()
                        }
                    }
                }
            }
        }
    }


    /**
     * 默认collect
     * followLifecycle onStart-onStop收集
     */
    fun <T> LifecycleOwner.collectFlow(
        flow: Flow<T>,
        followLifecycle: Boolean = true,
        collector: suspend (T) -> Unit
    ) {
        lifecycleScope.launch {
            if (followLifecycle) {
                repeatOnLifecycle(Lifecycle.State.STARTED) {
                    flow.collectLatest(collector)
                }
            } else {
                flow.collectLatest(collector)
            }
        }
    }


    /**
     * 简化 collect
     */
    fun <T> LifecycleOwner.collectNetState(
        flow: Flow<NetState<T>>,
        followLifecycle: Boolean = true,
        onError: (Throwable) -> Unit = {onError(it.message)},
        onPrepare: () -> Unit = {},
        onSuccess: (T) -> Unit,
    ) {
        collectFlow(flow, followLifecycle) { state ->
            when (state) {
                is NetState.Idle -> {}
                is NetState.Prepare -> { onPrepare() }
                is NetState.Success -> { onSuccess(state.data) }
                is NetState.Error -> { onError(state.throwable) }
            }
        }
    }


    /**
     * 简化collect DSL
     */
    fun <T> LifecycleOwner.collectNetStateEasy(
        flow: Flow<NetState<T>>,
        followLifecycle: Boolean = true,
        block: NetStateDsl<T>.() -> Unit
    ) {
        val dsl = NetStateDsl<T>().apply(block)
        collectFlow(flow, followLifecycle) { state ->
            when (state) {
                is NetState.Idle -> {}
                is NetState.Prepare -> {dsl.onPrepare?.invoke() }
                is NetState.Success -> { dsl.onSuccess.invoke(state.data) }
                is NetState.Error -> dsl.onError?.invoke(state.throwable)
                    ?: onError(state.throwable.message ?: "请求失败")
            }
        }
    }
}
