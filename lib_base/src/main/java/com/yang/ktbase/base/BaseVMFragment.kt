package com.yang.ktbase.base

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import com.yang.ktbase.util.getVmClazz

/***
 *  vm相关Fragment基类
 *  创建默认viewModel
 *  ...其他vm相关公共逻辑
 */
abstract class BaseVMFragment<M : ViewModel, B : ViewBinding> : BaseFragment<B>() {

    protected lateinit var mViewModel: M


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        mViewModel = createViewModel()
        super.onViewCreated(view, savedInstanceState)
    }

    /**
     * 创建viewModel
     */
    private fun createViewModel(): M {
        return ViewModelProvider(this)[getVmClazz(this)]
    }
}