package com.yang.ktbase.vm

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.blankj.utilcode.util.ToastUtils
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

/**
 * UI状态观察与 Loading 控制
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
     * 订阅 loading状态
     */
    fun observeUiState(viewModel: BaseViewModel) {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collectLatest { state ->
                    when (state) {
                        UiState.Idle -> hideLoading()
                        UiState.ShowLoading -> showLoading()
                        UiState.HideLoading -> showLoading()
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
     * Flow 收集数据简易版
     * followLifecycle跟随生命周期，followLifecycle=ture 后台时收不到数据
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

}