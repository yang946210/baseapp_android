package com.yang.ktbase.base

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import com.yang.ktbase.ext.getVmClazz

abstract class BaseFragment<M : ViewModel, B : ViewBinding> : BaseBindFragment<B>() {

    protected lateinit var viewModel: M

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel = createViewModel()
        super.onViewCreated(view, savedInstanceState)

    }

    /**
     * 创建viewModel
     */
    private fun createViewModel(): M {
        return ViewModelProvider(this).get(getVmClazz(this))
    }
}