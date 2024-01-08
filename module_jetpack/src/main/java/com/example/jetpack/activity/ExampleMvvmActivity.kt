package com.example.jetpack.activity

import android.os.Bundle
import android.widget.Toast
import com.blankj.utilcode.util.ToastUtils
import com.example.jetpack.vm.NetExpViewModel
import com.example.lib_jetpack.databinding.ActivityExampleMvvmBinding
import com.yang.ktbase.activity.BaseActivity
import com.yang.ktbase.network.netutil.launchAndCollect

class ExampleMvvmActivity : BaseActivity<NetExpViewModel, ActivityExampleMvvmBinding>() {

    override fun initView(savedInstanceState: Bundle?) {
        mBinding.run {
            btnGetNet1.setOnClickListener {

            }
            btnGetNet2.setOnClickListener {
                launchAndCollect(
                    showLoading = false,
                    {
                        mViewModel.getBanner()
                    }, {
                        onSuccess = {
                            tvShowTitle.text=it?.toString()
                        }
                        onFailed = { _, msg ->
                            ToastUtils.showLong(msg)
                        }
                    }
                )
            }
        }

    }
}