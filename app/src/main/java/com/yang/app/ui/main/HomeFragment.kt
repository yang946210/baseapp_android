package com.yang.app.ui.main
import android.content.Intent
import android.os.Bundle
import com.yang.app.ui.net_exp.NetRequestActivity
import com.yang.app.databinding.FragmentHomeBinding
import com.yang.ktbase.base.BaseVMFragment


class HomeFragment : BaseVMFragment<FragmentHomeBinding, HomeViewModel>() {

    override fun initView(savedInstanceState: Bundle?) {
        mBinding.apply {
            btnNet.setOnClickListener {
                startActivity(Intent(context,NetRequestActivity::class.java))
            }
        }
        mViewModel.getData()
    }
}




