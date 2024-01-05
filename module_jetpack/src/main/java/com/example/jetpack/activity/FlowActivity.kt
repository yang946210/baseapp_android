package com.example.jetpack.activity

import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.example.jetpack.viewmodel.FlowViewModel
import com.example.lib_jetpack.databinding.ActivityFlowBinding
import com.yang.ktbase.base.BaseActivity
import kotlinx.coroutines.launch

class FlowActivity : BaseActivity<FlowViewModel, ActivityFlowBinding>() {

    override fun initView(savedInstanceState: Bundle?) {
        mBinding.apply {
            btnEmit1.setOnClickListener {
                lifecycleScope.launch {
                    mViewModel.emit1()
                }
            }
        }
    }
}