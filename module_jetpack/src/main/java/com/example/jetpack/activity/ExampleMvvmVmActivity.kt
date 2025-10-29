package com.example.jetpack.activity

import android.os.Bundle
import com.blankj.utilcode.util.ToastUtils
import com.example.jetpack.vm.NetExpViewModel
import com.example.lib_jetpack.databinding.ActivityExampleMvvmBinding
import com.yang.ktbase.base.BaseVmActivity
import com.yang.ktbase.net.collectIn
import com.yang.ktbase.net.request
import com.yang.ktbase.net.requestWithCollect

/**
 * 网络封装示例
 * 1：链式请求
 * 2：标准mvvm请求
 * 3.图片加载
 * 4.多请求合并
 * 5.下载
 * 6.上传
 */
class ExampleMvvmVmActivity : BaseVmActivity<NetExpViewModel, ActivityExampleMvvmBinding>() {

    override fun initView(savedInstanceState: Bundle?) {
        mBinding.run {

            /**
             * 请求1
             */
            btnGetNet1.setOnClickListener {
                request (
                    mViewModel::getBanner1,
                    showLoading = true,
                )
            }
            /**
             * 接收1的数据
             */
            mViewModel.titleData.collectIn(
                this@ExampleMvvmVmActivity,
                onError = {}
            ) {
                tvShowTitle.text = it.toString()
            }





            /**
             * 请求返回链式调用
             */
            btnGetNet2.setOnClickListener {
                requestWithCollect(
                    { mViewModel.getBanner2() },
                    showLoading = true,
                    onError = {
                        ToastUtils.showLong("请求失败:${it.message}${it.code}")
                    },
                ) {
                    tvShowTitle.text = it.toString()
                }
            }
        }
    }
}