package com.yang.ktbase.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import com.yang.ktbase.util.getVmClazz


/**
 * 需要实现vm相关功能的activity继承这个基类
 * 实现viewModel相关功能
 * ...其他vm相关逻辑实现
 */
abstract class BaseVmActivity<M : ViewModel, B : ViewBinding> : BaseActivity<B>() {

    /**
     * 初始化ViewModel
     */
    protected val mViewModel:M by lazy {
       ViewModelProvider(this)[getVmClazz(this)]
    }

}