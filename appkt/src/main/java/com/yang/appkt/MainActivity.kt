package com.yang.appkt

import android.content.Intent
import android.os.Bundle
import androidx.arch.core.executor.ArchTaskExecutor
import com.yang.appkt.databinding.ActivityMainBinding
import com.yang.appkt.menu.CoroutinesActivity
import com.yang.appkt.menu.RecyclerActivity
import com.yang.ktbase.LiveDataBus
import com.yang.ktbase.base.BaseActivity
import com.yang.ktbase.base.BaseViewModel
import com.yang.ktbase.ext.logD

class MainActivity : BaseActivity<BaseViewModel, ActivityMainBinding>() {

    override fun initView(savedInstanceState: Bundle?) {
        LiveDataBus.with<String>("TestLiveDataBus").observer(this) {
            it.logD()
        }

        binding.run {
            tvLiveData.setOnClickListener {
                LiveDataBus.with<String>("TestLiveDataBus").postData("1212")
            }
            tvFrame.setOnClickListener {
                startActivity(Intent(this@MainActivity, RecyclerActivity::class.java))
            }
            tvInfo.setOnClickListener {  }
            tvCoroutines.setOnClickListener {
                startActivity(Intent(this@MainActivity, CoroutinesActivity::class.java))
            }
        }

    }
}