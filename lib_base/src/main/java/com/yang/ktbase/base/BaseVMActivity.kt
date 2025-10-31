package com.yang.ktbase.base

import android.app.ProgressDialog
import android.os.Bundle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.viewbinding.ViewBinding
import com.yang.ktbase.util.getVmClazz
import com.yang.ktbase.util.toastUtil
import com.yang.ktbase.vm.BaseViewModel
import com.yang.ktbase.vm.UiState
import kotlinx.coroutines.flow.collectLatest


/**
 * 需要实现vm相关功能的activity继承这个基类
 * 实现viewModel相关功能
 * ...其他vm相关逻辑实现
 */
abstract class BaseVMActivity<M : ViewModel, B : ViewBinding> : BaseActivity<B>() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindData()
    }

    abstract fun bindData();

    /**
     * 网络请求加载弹窗
     */
    private val progressDialog: ProgressDialog by lazy {
        ProgressDialog(this)
    }

    /**
     * 初始化ViewModel
     */
    protected val mViewModel:M by lazy {
       ViewModelProvider(this)[getVmClazz(this)]
    }

    /**
     * 订阅 ViewModel 的 UI 状态
     */
    fun observeUiState(viewModel: BaseViewModel) {
        lifecycleScope.launchWhenStarted {
            viewModel.uiState.collectLatest { state ->
                when (state) {
                    is UiState.Loading -> showLoading()
                    is UiState.Success<*> -> hideLoading()
                    is UiState.Error -> {
                        hideLoading()
                        showError(state.message)
                    }
                    else -> {}
                }
            }
        }
    }

    /**
     * 显示 Loading
     */
    open fun showLoading(msg: String = "加载中...") {
        progressDialog.show()
        progressDialog.setTitle(msg)
    }

    /**
     * 隐藏 Loading
     */
    open fun hideLoading() {
        progressDialog.takeIf { it.isShowing }?.dismiss()
    }

    /**
     *  请求错误错误提示
     */
    open fun showError(msg: String) {
        toastUtil(msg)
    }

}