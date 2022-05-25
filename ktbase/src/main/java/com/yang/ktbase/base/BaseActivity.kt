package com.yang.ktbase.base

import android.os.Bundle
import androidx.viewbinding.ViewBinding
import com.yang.ktbase.ext.inflateBindingWithGeneric

abstract class BaseActivity<M:BaseViewModel,B:ViewBinding> :BaseVmActivity<M>(){

    /**
     * 初始化viewBind
     */
    protected val binding by lazy {
        inflateBindingWithGeneric<B>(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initView(savedInstanceState)
    }

    /**
     * 初始化
     */
    abstract fun initView(savedInstanceState: Bundle?)

}