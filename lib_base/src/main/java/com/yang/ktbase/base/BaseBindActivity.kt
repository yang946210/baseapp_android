package com.yang.ktbase.base

import android.os.Bundle
import androidx.viewbinding.ViewBinding
import com.yang.ktbase.util.inflateBindingWithGeneric

/**
 * UI相关基类，
 * 实现viewBind功能
 * ...其他UI相关的公共逻辑也可以在这里
 *
 * @param B: ViewBinding
 * @property mBinding B
 */
abstract class BaseBindActivity<B : ViewBinding> : BaseCommonActivity()  {

    /**
     * 初始化viewBind
     */
    protected val mBinding by lazy {
        inflateBindingWithGeneric<B>(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mBinding.root)
        initView(savedInstanceState)
    }

    /**
     * UI初始化/绑定相关
     */
    abstract fun initView(savedInstanceState: Bundle?)


}