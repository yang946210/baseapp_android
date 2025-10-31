package com.yang.appkt.view.menu
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import com.example.jetpack.activity.ExampleMvvmVMActivity
import com.example.jetpack.activity.FlowActivity
import com.example.jetpack.activity.LifecycleActivity
import com.example.jetpack.activity.LiveDataVMActivity
import com.example.jetpack.activity.ViewModelVMActivity
import com.google.zxing.integration.android.IntentIntegrator
import com.yang.appkt.vm.JetPackViewModel
import com.yang.appkt.databinding.FragmentJetpackBinding
import com.yang.appkt.util.FlutterEngineManager
import com.yang.ktbase.base.BaseVMFragment


class JetPackVMFragment : BaseVMFragment<JetPackViewModel, FragmentJetpackBinding>() {

    override fun initView(savedInstanceState: Bundle?) {
        mBinding.apply {
            btnFlow.setOnClickListener {
                startActivity(Intent(context,FlowActivity::class.java))
            }
            btnLifecycle.setOnClickListener {
                startActivity(Intent(context,LifecycleActivity::class.java))
            }
            btnViewModel.setOnClickListener {
                startActivity(Intent(context,ViewModelVMActivity::class.java))
            }
            btnLiveData.setOnClickListener {
                startActivity(Intent(context,LiveDataVMActivity::class.java))
            }
            btnNetDemo.setOnClickListener {
                startActivity(Intent(context,ExampleMvvmVMActivity::class.java))
            }
            btnFlutter.setOnClickListener {
                startActivity(
                    FlutterEngineManager.getEngine(requireContext())
                )
            }
            btnScan.setOnClickListener {
                // 启动扫码
                val integrator = IntentIntegrator(activity)
                integrator.setPrompt("请扫描二维码") // 提示
                integrator.setBeepEnabled(false) // 扫描时是否有声音
                integrator.setOrientationLocked(false) // 是否锁定屏幕方向
                integrator.initiateScan() // 启动扫描
            }
        }
        mViewModel.apply {
            // TODO: 写点啥吧
        }

    }


}




