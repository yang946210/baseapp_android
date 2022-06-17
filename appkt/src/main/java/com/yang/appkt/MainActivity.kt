package com.yang.appkt

import android.content.Intent
import android.os.Bundle
import com.yang.appkt.databinding.ActivityMainBinding
import com.yang.appkt.menu.CoroutinesActivity
import com.yang.appkt.menu.RecyclerActivity
import com.yang.appkt.menu.RoomActivity
import com.yang.appkt.menu.WebViewActivity
import com.yang.ktbase.LiveDataBus
import com.yang.ktbase.base.BaseBindActivity

class MainActivity : BaseBindActivity<ActivityMainBinding>() {

    override fun initView(savedInstanceState: Bundle?) {
        binding.run {
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