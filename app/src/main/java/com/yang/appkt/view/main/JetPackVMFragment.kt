package com.yang.appkt.view.main
import android.content.Intent
import android.os.Bundle
import com.yang.appkt.vm.JetPackViewModel
import com.yang.appkt.databinding.FragmentJetpackBinding
import com.yang.appkt.view.net.NetRequestActivity
import com.yang.ktbase.base.BaseVMFragment


class JetPackVMFragment : BaseVMFragment<JetPackViewModel, FragmentJetpackBinding>() {

    override fun initView(savedInstanceState: Bundle?) {
        mBinding.apply {
            btnNet.setOnClickListener {
                startActivity(Intent(context,NetRequestActivity::class.java))
            }
        }
    }
}




