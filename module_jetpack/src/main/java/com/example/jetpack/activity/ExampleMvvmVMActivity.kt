package com.example.jetpack.activity

import android.os.Bundle
import com.example.jetpack.vm.NetExpViewModel
import com.example.lib_jetpack.databinding.ActivityExampleMvvmBinding
import com.yang.ktbase.base.BaseVMActivity

/**
 * 网络封装示例
 * 1：链式请求
 * 2：标准mvvm请求
 * 3.图片加载
 * 4.多请求合并
 * 5.下载
 * 6.上传
 */
class ExampleMvvmVMActivity : BaseVMActivity<NetExpViewModel, ActivityExampleMvvmBinding>() {

    override fun bindView(savedInstanceState: Bundle?) {
        mBinding.run {
            /**
             * 请求1
             */
            btnGetNet1.setOnClickListener {
                mViewModel.getUserInfo()
            }


            /**
             * 请求2
             */
            btnGetNet2.setOnClickListener {

            }
        }
    }

    override fun bindData() {
        collectData(mViewModel.titleData){
            mBinding.tvShowTitle.text=it.toString()
        }
    }



}