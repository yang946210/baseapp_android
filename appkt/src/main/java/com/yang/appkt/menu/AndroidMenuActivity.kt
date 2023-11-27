package com.yang.appkt.menu

import android.content.Intent
import android.os.Bundle
import com.example.avi.AviMenuActivity
import com.yang.appkt.databinding.ActivityAndroidBaseBinding
import com.yang.ktbase.base.BaseBindActivity

class AndroidMenuActivity : BaseBindActivity<ActivityAndroidBaseBinding>() {

    override fun initView(savedInstanceState: Bundle?) {
        binding.apply {
            tvJavaBase.setOnClickListener {
                startActivity(Intent(this@AndroidMenuActivity, JavaMemoryActivity::class.java))
            }
            tvActivity.setOnClickListener {
                startActivity(Intent(this@AndroidMenuActivity, ActivityActivity::class.java))
            }
            tvLaunch.setOnClickListener {
                startActivity(Intent(this@AndroidMenuActivity, LaunchActivity::class.java))
            }
            tvHandler.setOnClickListener {
                startActivity(Intent(this@AndroidMenuActivity, HandlerActivity::class.java))
            }
            tvWebView.setOnClickListener {
                startActivity(Intent(this@AndroidMenuActivity, WebViewActivity::class.java))
            }
            tvRoom.setOnClickListener {
                startActivity(Intent(this@AndroidMenuActivity, RoomActivity::class.java))
            }
            tvLifecycle.setOnClickListener {
                startActivity(Intent(this@AndroidMenuActivity, LifecycleActivity::class.java))
            }
            tvLiveData.setOnClickListener {
                startActivity(Intent(this@AndroidMenuActivity, VmActivity::class.java))
            }
            tvFrame.setOnClickListener {
                startActivity(Intent(this@AndroidMenuActivity, RecyclerActivity::class.java))
            }
            tvCoroutines.setOnClickListener {
                startActivity(Intent(this@AndroidMenuActivity, CoroutinesActivity::class.java))
            }
            tvBluetooth.setOnClickListener {
                startActivity(Intent(this@AndroidMenuActivity, NdkActivity::class.java))
            }
            tvNdk.setOnClickListener {
            }
            tvAvi.setOnClickListener {
                startActivity(Intent(this@AndroidMenuActivity, AviMenuActivity::class.java))
            }
        }
    }
}