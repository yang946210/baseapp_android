package com.yang.appkt.menu

import android.os.Bundle
import com.yang.appkt.databinding.ActivityActivityBinding
import com.yang.ktbase.base.BaseBindActivity

class ActivityActivity : BaseBindActivity<ActivityActivityBinding>() {

    override fun initView(savedInstanceState: Bundle?) {
        binding.tvAcContent.text="""
         
        """.trimIndent()

    }
}