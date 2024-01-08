package com.yang.ktbase.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import com.yang.ktbase.util.inflateBindingWithGeneric


/***
 *  UI相关Fragment基类
 *  创建默认viewBind
 *  ...其他UI相关公共逻辑
 */
abstract class BaseBindFragment<B : ViewBinding> : BaseCommonFragment() {

    private var _binding: B? = null

    protected val mBinding: B get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = inflateBindingWithGeneric(inflater, container, false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView(savedInstanceState)
    }

    /**
     * 初始化布局
     */
    abstract fun initView(savedInstanceState: Bundle?)


    /**
     * 跟着就销毁了
     */
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}