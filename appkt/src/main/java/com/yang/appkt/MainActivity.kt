package com.yang.appkt

import android.content.Intent
import android.os.Bundle
import com.yang.appkt.databinding.ActivityMainBinding
import com.yang.appkt.menu.*
import com.yang.ktbase.LiveDataBus
import com.yang.ktbase.base.BaseBindActivity
import kotlinx.coroutines.*

class MainActivity : BaseBindActivity<ActivityMainBinding>(), CoroutineScope by MainScope() {


    override fun initView(savedInstanceState: Bundle?) {
        binding.run {

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

            tvLiveData.setOnClickListener {
                LiveDataBus.with<String>("test").postData("1212")
            }

            tvFrame.setOnClickListener {
                startActivity(Intent(this@MainActivity, RecyclerActivity::class.java))
            }

            tvCoroutines.setOnClickListener {
                startActivity(Intent(this@MainActivity, CoroutinesActivity::class.java))
            }
        }
    }
}