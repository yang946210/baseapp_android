package com.example.jetpack.activity

import android.os.Bundle
import com.blankj.utilcode.util.ToastUtils
import com.example.jetpack.vm.NetExpViewModel
import com.example.lib_jetpack.databinding.ActivityExampleMvvmBinding
import com.yang.ktbase.activity.BaseVmActivity
import com.yang.ktbase.network.collectIn
import com.yang.ktbase.network.request
import com.yang.ktbase.network.requestWithCollect

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
//                request (
//                    reqCall = mViewModel::getBanner1,
//                    showLoading = true,
//                )

//                request(reqCall = {mViewModel.getBanner1()})
                request {
                    mViewModel.getBanner1()
                }
            }


            /**
             * 接收1的数据
             */
            mViewModel.titleData.collectIn(
                this@ExampleMvvmVmActivity,
                //如果你想自己处理错误就添加这个参数
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
                    }
                ) {
                    tvShowTitle.text = it.toString()
                }

            }
        }
    }
}