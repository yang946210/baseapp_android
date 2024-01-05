package com.yang.ktbase.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import com.yang.ktbase.util.getVmClazz


/**
 * vm相关基类
 * 实现viewModel相关功能
 * ...其他vm相关逻辑实现
 */
abstract class BaseActivity<M : ViewModel, B : ViewBinding> : BaseBindActivity<B>() {

    /**
     * 初始化ViewModel
     */
    protected val mViewModel by lazy {
        createViewModel()
    }

    /**
     * 创建viewModel
     */
    private fun createViewModel(): M {
        return ViewModelProvider(this)[getVmClazz(this)]
    }


}