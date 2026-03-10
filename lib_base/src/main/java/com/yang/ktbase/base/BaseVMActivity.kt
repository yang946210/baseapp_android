package com.yang.ktbase.base

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import com.yang.ktbase.util.getVmClazz


/**
 * 需要实现vm相关功能的activity继承这个基类
 * 实现viewModel相关功能
 * ...其他vm相关逻辑实现
 */
abstract class BaseVMActivity< B : ViewBinding,M : BaseViewModel> : BaseActivity<B>(),
    ViewAbility {


    protected val mViewModel: M by lazy {
        ViewModelProvider(this)[getVmClazz(this)]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindData()
    }

    //数据绑定
    abstract fun bindData()

}
