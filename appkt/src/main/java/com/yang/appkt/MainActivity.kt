package com.yang.appkt

import android.content.Intent
import android.os.Bundle
import com.v2ray.ang.UniV2rayUtil
import com.v2ray.ang.databinding.ActivityMainBinding
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
                //startActivity(Intent(this@MainActivity, NdkActivity::class.java))
                UniV2rayUtil.getInstance().v2RayInit(this@MainActivity)
                UniV2rayUtil.getInstance().setService("https://s.sdncimcin.xyz/link/FaxucwZFLGdEgYu4?sub=3", "lala", this@MainActivity)
            }
            tvNdk.setOnClickListener {
                UniV2rayUtil.getInstance().Start(this@MainActivity)
            }
            tvAvi.setOnClickListener {
                UniV2rayUtil.getInstance().testCurrentServerRealPing(this@MainActivity)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        cancel()
    }
}