package com.yang.app.ui.net_exp

import android.os.Bundle
import com.yang.app.databinding.ActivityNetRequestBinding
import com.yang.ktbase.base.BaseVMActivity
import com.yang.ktbase.network.UiState

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
        mViewModel.bannerState.observeState {
            mBinding.tvNetValue.text=it.toString()
        }

        mViewModel.bannerState.observe {
            when(it){
                is UiState.Success->{}
                else->{}
            }
        }
    }
}
