package com.yang.ktbase.base

import android.app.ProgressDialog
import android.os.Bundle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import com.yang.ktbase.vm.UiStateObserver
import com.yang.ktbase.util.getVmClazz
import com.yang.ktbase.vm.BaseViewModel


/**
 * 需要实现vm相关功能的activity继承这个基类
 * 实现viewModel相关功能
 * ...其他vm相关逻辑实现
 */
abstract class BaseVMActivity<M : ViewModel, B : ViewBinding> : BaseActivity<B>(), UiStateObserver {


    /**
     * 初始化ViewModel
     */
    protected val mViewModel: M by lazy {
        ViewModelProvider(this)[getVmClazz(this)]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        observeUiState(mViewModel as BaseViewModel)
        bindData()
    }

    //绑定数据
    abstract fun bindData()

}