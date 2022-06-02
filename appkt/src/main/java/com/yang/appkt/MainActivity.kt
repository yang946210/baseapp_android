package com.yang.appkt

import android.content.Intent
import android.os.Bundle
import androidx.arch.core.executor.ArchTaskExecutor
import androidx.lifecycle.ViewModelProvider
import com.yang.appkt.databinding.ActivityMainBinding
import com.yang.appkt.menu.CoroutinesActivity
import com.yang.appkt.menu.RecyclerActivity
import com.yang.appkt.menu.RoomActivity
import com.yang.ktbase.LiveDataBus
import com.yang.ktbase.base.BaseActivity
import com.yang.ktbase.base.BaseViewModel
import com.yang.ktbase.ext.logD

class MainActivity : BaseActivity<BaseViewModel, ActivityMainBinding>() {

    override fun initView(savedInstanceState: Bundle?) {


        binding.run {
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

        LiveDataBus.with<String>("test").observe(this){
            it.logD()
        }

    }
}