package com.example.jetpack.activity

import android.os.Bundle
import com.blankj.utilcode.util.ToastUtils
import com.example.jetpack.vm.NetExpViewModel
import com.example.lib_jetpack.databinding.ActivityExampleMvvmBinding
import com.yang.ktbase.activity.BaseVmActivity
import com.yang.ktbase.network.netutil.collectIn
import com.yang.ktbase.network.netutil.request
import com.yang.ktbase.network.netutil.requestAndCollect

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
                //launchIn (requestBlock = mViewModel::getBanner1)
                //launchIn  (requestBlock = { mViewModel.getBanner1()})
                request(showLoading = true) {
                    mViewModel.getBanner1()
                }

            }

            /**
             * 接收1的数据
             */
            mViewModel.titleData.collectIn(this@ExampleMvvmVmActivity){
                onSuccess={
                    mBinding.tvShowTitle.text=it.toString()
                }
                onFailed={ _,msg->
                    ToastUtils.showLong(msg)
                }
                onError ={
                    ToastUtils.showLong(it.message)
                }
            }


            /**
             * 请求返回链式调用
             */
            btnGetNet2.setOnClickListener {
                requestAndCollect(
                    {
                        mViewModel.getBanner2()
                    },
                    showLoading = true,
                ) {
                    onSuccess = {
                        tvShowTitle.text = it.toString()
                    }
                    onFailed ={ _,msg->ToastUtils.showLong(msg) }
                    onError = {
                        ToastUtils.showLong(it.message)
                    }
                }
            }

            /**
             * 请求3 get
             */
            btnGetNet3.setOnClickListener {
                mViewModel.getBanner3()


            }
        }



    }


}