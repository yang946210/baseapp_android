package com.yang.appkt

import android.content.Intent
import android.os.Bundle
import com.yang.appkt.databinding.ActivityMainBinding
import com.yang.appkt.menu.*
import com.yang.ktbase.base.BaseBindActivity
import kotlinx.coroutines.*


class MainActivity : BaseBindActivity<ActivityMainBinding>(), CoroutineScope by MainScope() {


    override fun initView(savedInstanceState: Bundle?) {

        binding.apply {
            tvJavaBase.setOnClickListener {
                startActivity(Intent(this@MainActivity, JavaMemoryActivity::class.java))
            }
            tvActivity.setOnClickListener {
                startActivity(Intent(this@MainActivity, ActivityActivity::class.java))
            }
            tvLaunch.setOnClickListener {
                startActivity(Intent(this@MainActivity, LaunchActivity::class.java))
            }
            tvHandler.setOnClickListener {
                startActivity(Intent(this@MainActivity, HandlerActivity::class.java))
            }
            tvWebView.setOnClickListener {
                startActivity(Intent(this@MainActivity, WebViewActivity::class.java))
            }
            tvRoom.setOnClickListener {
                startActivity(Intent(this@MainActivity, RoomActivity::class.java))
            }
            tvLifecycle.setOnClickListener {
                startActivity(Intent(this@MainActivity, LifecycleActivity::class.java))
            }
            tvLiveData.setOnClickListener {
                startActivity(Intent(this@MainActivity, VmActivity::class.java))
            }
            tvFrame.setOnClickListener {
                startActivity(Intent(this@MainActivity, RecyclerActivity::class.java))
            }
            tvCoroutines.setOnClickListener {
                startActivity(Intent(this@MainActivity, CoroutinesActivity::class.java))
            }
            tvBluetooth.setOnClickListener {
                startActivity(Intent(this@MainActivity, BluetoothActivity::class.java))
            }
            tvNdk.setOnClickListener {
                startActivity(Intent(this@MainActivity, NdkActivity::class.java))
            }
            tvAvi.setOnClickListener {

            }

        }
    }

    override fun onDestroy() {
        super.onDestroy()
        cancel()
    }
}