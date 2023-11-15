package com.yang.ktbase.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.yang.ktbase.ext.inflateBindingWithGeneric

/**
 * activity基类，实现viewBind功能
 * 如果某个activity不需要viewModel功能，就可以直接继承这个基类
 * @param B: ViewBinding
 * @property binding B
 */
abstract class BaseBindActivity<B : ViewBinding> : AppCompatActivity() {

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
        initData(savedInstanceState)
    }

    /**
     * 初始化
     */
    abstract fun initView(savedInstanceState: Bundle?)
    open fun initData(savedInstanceState: Bundle?){

    }
}