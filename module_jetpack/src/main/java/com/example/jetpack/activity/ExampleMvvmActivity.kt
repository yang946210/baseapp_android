package com.example.jetpack.activity

import android.os.Bundle
import com.blankj.utilcode.util.ToastUtils
import com.example.jetpack.vm.NetExpViewModel
import com.example.lib_jetpack.databinding.ActivityExampleMvvmBinding
import com.yang.ktbase.activity.BaseActivity
import com.yang.ktbase.network.netutil.collectIn
import com.yang.ktbase.network.netutil.launchAndCollect
import com.yang.ktbase.network.netutil.launchIn

/**
 * 网络封装示例
 * 1：链式请求
 * 2：标准mvvm请求
 * 3.图片加载
 * 4.多请求合并
 * 5.下载
 * 6.上传
 */
class ExampleMvvmActivity : BaseActivity<NetExpViewModel, ActivityExampleMvvmBinding>() {

    override fun initView(savedInstanceState: Bundle?) {

        mBinding.run {
            /**
             * 1.标准封装请求
             */
            btnGetNet1.setOnClickListener {
                //launchIn (requestBlock = mViewModel::getBanner1)
                //launchIn  (requestBlock = { mViewModel.getBanner1()})
                launchIn(showLoading = true) {
                    mViewModel.getBanner1()
                }
            }


            /**
             * 简化链式请求数据
             */
            btnGetNet2.setOnClickListener {
                launchAndCollect(
                    { mViewModel.getPage(1) },
                    showLoading = true,
                ) {
                    onSuccess = {
                        tvShowTitle.text = it?.toString()
                    }
                    onFailed ={ code,msg->{

                    }

                    }
                    onError = {
                        ToastUtils.showLong(it.message)
                    }
                }
            }
        }




        /**
         * 接收1的数据
         */
        mViewModel.titleData.collectIn(this){
            onSuccess={
                mBinding.tvShowTitle.text=it.toString()
            }
            onFailed={ _,msg->
                mBinding.tvShowTitle.text=msg
            }
        }

    }


}