package com.yang.ktbase.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import com.yang.ktbase.extorutil.getVmClazz


/**
 * 构造viewModel,ViewBinding 的base类
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