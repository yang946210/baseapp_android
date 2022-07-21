package com.yang.appkt.menu.ui.home

import android.os.Bundle
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.lifecycle.lifecycleScope
import com.yang.appkt.databinding.FragmentHomeBinding
import com.yang.appkt.ext.logLifeCycle
import com.yang.ktbase.base.BaseFragment
import com.yang.ktbase.ext.logD
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class HomeFragment : BaseFragment<HomeViewModel, FragmentHomeBinding>() {

    val scope:CoroutineScope = CoroutineScope(Dispatchers.IO)

    override fun initView(savedInstanceState: Bundle?) {
        logLifeCycle()

        scope.launch {
        }

        scope.cancel()

        binding.apply {
            tvAdd.setOnClickListener {
                viewModel.add()
            }
            tvReduce.setOnClickListener {
                viewModel.reduce()
            }

            tvLeakTest.setOnClickListener {
                viewModel.leakTest()
            }
        }
    }
}



