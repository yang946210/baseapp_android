package com.yang.appkt.menu
import android.content.Intent
import android.os.Bundle
import com.example.jetpack.activity.CoroutinesVmActivity
import com.example.jetpack.activity.ExampleMvvmVmActivity
import com.example.jetpack.activity.FlowActivity
import com.example.jetpack.activity.LifecycleActivity
import com.example.jetpack.activity.LiveDataVmActivity
import com.example.jetpack.activity.ViewModelVmActivity
import com.yang.appkt.JetPackViewModel
import com.yang.appkt.databinding.FragmentJetpackBinding
import com.yang.appkt.util.FlutterEngineManager
import com.yang.ktbase.fragment.BaseFragment
import io.flutter.embedding.android.FlutterActivity

class JetPackFragment : BaseFragment<JetPackViewModel, FragmentJetpackBinding>() {


    override fun initView(savedInstanceState: Bundle?) {

        mBinding.apply {

            btnScope.setOnClickListener {
                startActivity(Intent(context,CoroutinesVmActivity::class.java))
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
        }
        mViewModel.apply {
            // TODO: 写点啥吧
        }

    }
}



