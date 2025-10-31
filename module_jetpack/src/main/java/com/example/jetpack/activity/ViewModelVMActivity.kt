package com.example.jetpack.activity

import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import com.example.lib_jetpack.databinding.ActivityViewModelBinding
import com.example.jetpack.vm.VmViewModel
import com.yang.ktbase.base.BaseVMActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope


class ViewModelVMActivity : BaseVMActivity<VmViewModel, ActivityViewModelBinding>() ,CoroutineScope by MainScope() {
    override fun bindData() {
        TODO("Not yet implemented")
    }

    override fun bindView(savedInstanceState: Bundle?) {
        //创建viewModel
        createViewModel()
    }

    /**
     *创建viewModel的方式：
     */
    private fun createViewModel(){
        mViewModel
        val vm1 by lazy { ViewModelProvider(this)[VmViewModel::class.java] }
        val vm2 by viewModels<VmViewModel>()
        val vm3: VmViewModel by viewModels()
    }
}