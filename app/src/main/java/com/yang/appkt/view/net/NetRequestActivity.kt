package com.yang.appkt.view.net

import android.os.Bundle
import com.yang.appkt.databinding.ActivityNetRequestBinding
import com.yang.appkt.vm.NetRequestModel

import com.yang.ktbase.base.BaseVMActivity
import com.yang.ktbase.util.logD

class NetRequestActivity : BaseVMActivity<NetRequestModel, ActivityNetRequestBinding>() {
    //进入自动加载数据
    override fun bindData() {
    }

    override fun bindView(savedInstanceState: Bundle?) {
        mBinding.apply {
            //点击加载
            tvGetNet.setOnClickListener {
                mBinding.tvNetValue.text=null
                mViewModel.getUserInfo()
            }
        }

        collectFlow(mViewModel.titleData){
            it.toString().logD("net")
            mBinding.tvNetValue.text=it.toString()
        }
    }

}