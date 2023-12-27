package com.yang.ktbase.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.yang.ktbase.extorutil.inflateBindingWithGeneric


/**
 * 构造viewBinding 的base类
 */
abstract class BaseBindFragment<B : ViewBinding> : Fragment() {

    private var _binding: B? = null

    protected val binding: B get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = inflateBindingWithGeneric(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView(savedInstanceState)
    }

    /**
     * 初始化布局
     */
    abstract fun initView(savedInstanceState: Bundle?)


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}