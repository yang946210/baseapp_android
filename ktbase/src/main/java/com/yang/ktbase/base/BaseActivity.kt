package com.yang.ktbase.base

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import com.yang.ktbase.ext.getVmClazz


/**
 * activity基类
 * @param M:BaseViewModel
 * @param B:ViewBinding
 * @property viewModel M
 */
abstract class BaseActivity<M : BaseViewModel, B : ViewBinding> : BaseBindActivity<B>() {


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