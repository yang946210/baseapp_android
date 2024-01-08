package com.yang.appkt.menu
import android.content.Intent
import android.os.Bundle
import com.example.jetpack.activity.CoroutinesActivity
import com.example.jetpack.activity.FlowActivity
import com.example.jetpack.activity.LifecycleActivity
import com.example.jetpack.activity.LiveDataActivity
import com.example.jetpack.activity.ViewModelActivity
import com.yang.appkt.JetPackViewModel
import com.yang.appkt.databinding.FragmentJetpackBinding
import com.yang.ktbase.fragment.BaseFragment

class JetPackFragment : BaseFragment<JetPackViewModel, FragmentJetpackBinding>() {


    override fun initView(savedInstanceState: Bundle?) {
        mBinding.apply {
            btnScope.setOnClickListener {
                startActivity(Intent(context,CoroutinesActivity::class.java))
            }
            btnFlow.setOnClickListener {
                startActivity(Intent(context,FlowActivity::class.java))
            }
            btnLifecycle.setOnClickListener {
                startActivity(Intent(context,LifecycleActivity::class.java))
            }
            btnViewModel.setOnClickListener {
                startActivity(Intent(context,ViewModelActivity::class.java))
            }
            btnLiveData.setOnClickListener {
                startActivity(Intent(context,LiveDataActivity::class.java))
            }
        }
        mViewModel.apply {
            // TODO: 写点啥吧
        }

    }
}



