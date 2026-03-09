package com.yang.appkt.view.net

import android.os.Bundle
import com.yang.appkt.databinding.ActivityNetRequestBinding
import com.yang.appkt.vm.NetRequestModel
import com.yang.ktbase.base.BaseVMActivity

class NetRequestActivity : BaseVMActivity<ActivityNetRequestBinding, NetRequestModel>() {

    override fun bindView(savedInstanceState: Bundle?) {
        mBinding.apply {
            tvGetNet.setOnClickListener {
                mBinding.tvNetValue.text=null
                mViewModel.getBanner()
            }
        }
    }

    override fun bindData() {
        collectNetState(mViewModel.bannerState) {
            mBinding.tvNetValue.text=it.toString()
        }
    }
}
