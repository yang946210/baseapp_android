package com.yang.ktbase.base

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import com.yang.ktbase.vm.BaseViewModel
import com.yang.ktbase.vm.UiStateObserver
import com.yang.ktbase.util.getVmClazz


/**
 * 需要实现vm相关功能的activity继承这个基类
 * 实现viewModel相关功能
 * ...其他vm相关逻辑实现
 */
abstract class BaseVMActivity<M : BaseViewModel, B : ViewBinding> : BaseActivity<B>(), UiStateObserver {


    /**
     * 初始化ViewModel
     */
    protected val mViewModel: M by lazy {
        ViewModelProvider(this)[getVmClazz(this)]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        observeUiState(mViewModel)
        bindData()
    }

    //绑定数据
    abstract fun bindData()

}
