package com.yang.appkt.menu
import android.content.Intent
import android.os.Bundle
import com.example.avi.menusdkapi.mediaextractor.MediaExtractorActivity
import com.example.avi.menusdkapi.mediarecorder.MediaRecorderActivity
import com.example.jetpack.activity.CoroutinesActivity
import com.example.jetpack.activity.LifecycleActivity
import com.yang.appkt.databinding.FragmentJetpackBinding
import com.yang.ktbase.base.BaseBindFragment

class JetPackFragment : BaseBindFragment<FragmentJetpackBinding>() {

    override fun initView(savedInstanceState: Bundle?) {
        binding.apply {
            btnScope.setOnClickListener {
                startActivity(Intent(context,CoroutinesActivity::class.java))
            }
            btnFlow.setOnClickListener {
                startActivity(Intent(context,LifecycleActivity::class.java))
            }
            btnLifecycle.setOnClickListener {
                startActivity(Intent(context,LifecycleActivity::class.java))
            }
            btnViewModel.setOnClickListener {
                startActivity(Intent(context,LifecycleActivity::class.java))
            }
            btnLiveData.setOnClickListener {
                startActivity(Intent(context,LifecycleActivity::class.java))
            }


        }

    }
}



