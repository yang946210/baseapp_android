package com.yang.appkt.menu
import android.content.Intent
import android.os.Bundle
import com.example.avi.menusdkapi.mediarecorder.MediaRecorderActivity
import com.example.jetpack.activity.CoroutinesActivity
import com.yang.appkt.databinding.FragmentJetpackBinding
import com.yang.ktbase.base.BaseBindFragment

class JetPackFragment : BaseBindFragment<FragmentJetpackBinding>() {

    override fun initView(savedInstanceState: Bundle?) {
        binding.apply {
            btnScope.setOnClickListener {
                startActivity(Intent(context,CoroutinesActivity::class.java.javaClass))
            }
        }


    }
}



