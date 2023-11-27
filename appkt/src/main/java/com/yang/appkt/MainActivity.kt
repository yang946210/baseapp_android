package com.yang.appkt

import com.example.avi.AviMenuActivity
import android.content.Intent
import android.os.Bundle
import com.yang.appkt.databinding.ActivityMainBinding
import com.yang.appkt.menu.*
import com.yang.ktbase.base.BaseBindActivity
import kotlinx.coroutines.*
import java.util.function.Function


class MainActivity : BaseBindActivity<ActivityMainBinding>(), CoroutineScope by MainScope() {


    override fun initView(savedInstanceState: Bundle?) {
        binding.apply {
            tvAndroidBase.setOnClickListener {
                startActivity(Intent(this@MainActivity, AndroidMenuActivity::class.java))
            }
            tvAvi.setOnClickListener {
                startActivity(Intent(this@MainActivity, AviMenuActivity::class.java))
            }
        }


    }

    override fun onDestroy() {
        super.onDestroy()
        cancel()
    }
}