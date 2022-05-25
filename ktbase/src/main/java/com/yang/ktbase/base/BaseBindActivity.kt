package com.yang.ktbase.base

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.yang.ktbase.ext.getVmClazz

abstract class BaseVmActivity<M : BaseViewModel> : AppCompatActivity() {


    /**
     * 初始化ViewModel
     */
    protected val viewModel by lazy{
        createViewModel()
    }

    /**
     * 创建viewModel
     */
    private fun createViewModel(): M {
        return ViewModelProvider(this).get(getVmClazz(this))
    }

}