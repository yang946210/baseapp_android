package com.yang.ktbase.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import com.yang.ktbase.ext.getVmClazz


/**
 * activity基类
 * @param M:ViewModel
 * @param B:ViewBinding
 * @property viewModel M
 */
abstract class BaseActivity<M : ViewModel, B : ViewBinding> : BaseBindActivity<B>() {

    /**
     * 初始化ViewModel
     */
    protected val viewModel by lazy {
        createViewModel()
    }

    /**
     * 创建viewModel
     */
    private fun createViewModel(): M {
        return ViewModelProvider(this).get(getVmClazz(this))
    }


}