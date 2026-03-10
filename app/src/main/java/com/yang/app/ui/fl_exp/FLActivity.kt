package com.yang.app.ui.fl_exp

import android.annotation.SuppressLint
import android.os.Bundle
import com.yang.app.databinding.ActivityFlactivityBinding
import com.yang.ktbase.base.BaseVMActivity
import com.yang.ktbase.network.UiState


class FLActivity : BaseVMActivity<ActivityFlactivityBinding,FlViewModule>() {

    override fun bindData() {

        mViewModel.banner.observe {
            when(it){
                is UiState.Success->{
                }
                is UiState.Error->{
                }
                else->{}
            }
        }
    }

    @SuppressLint("SetTextI18n")
    override fun bindView(savedInstanceState: Bundle?) {
        mBinding.tvTest.text="9527"
    }

}