package com.yang.appkt.menu.ui.home

import android.os.Bundle
import com.yang.appkt.databinding.FragmentHomeBinding
import com.yang.appkt.ext.logLifeCycle
import com.yang.ktbase.base.BaseFragment
import kotlinx.coroutines.*

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



