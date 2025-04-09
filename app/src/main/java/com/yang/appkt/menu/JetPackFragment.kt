package com.yang.appkt.menu
import android.content.Intent
import android.content.pm.ActivityInfo
import android.net.Uri
import android.os.Bundle
import com.example.jetpack.activity.ExampleMvvmVmActivity
import com.example.jetpack.activity.FlowActivity
import com.example.jetpack.activity.LifecycleActivity
import com.example.jetpack.activity.LiveDataVmActivity
import com.example.jetpack.activity.ViewModelVmActivity
import com.google.zxing.integration.android.IntentIntegrator
import com.yang.appkt.JetPackViewModel
import com.yang.appkt.databinding.FragmentJetpackBinding
import com.yang.appkt.util.FlutterEngineManager
import com.yang.ktbase.fragment.BaseFragment


class JetPackFragment : BaseFragment<JetPackViewModel, FragmentJetpackBinding>() {

    override fun initView(savedInstanceState: Bundle?) {
        mBinding.apply {

            btnScope.setOnClickListener {
                val packageName = "com.glxx.sdapp" // 替换为目标应用程序的包名
                val intent = Intent(Intent.ACTION_DELETE)
                intent.setData(Uri.parse("package:$packageName"))
                startActivity(intent)

                //startActivity(Intent(context,CoroutinesVmActivity::class.java))
            }
            btnFlow.setOnClickListener {
                startActivity(Intent(context,FlowActivity::class.java))
            }
            btnLifecycle.setOnClickListener {
                startActivity(Intent(context,LifecycleActivity::class.java))
            }
            btnViewModel.setOnClickListener {
                startActivity(Intent(context,ViewModelVmActivity::class.java))
            }
            btnLiveData.setOnClickListener {
                startActivity(Intent(context,LiveDataVmActivity::class.java))
            }
            btnNetDemo.setOnClickListener {
                startActivity(Intent(context,ExampleMvvmVmActivity::class.java))
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




