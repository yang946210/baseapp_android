package com.yang.appkt.menu.base

import android.os.Bundle
import com.yang.appkt.databinding.ActivityNdkBinding
import com.yang.ktbase.base.BaseBindActivity


/**
 * ndk的一些验证
 */
class NdkActivity : BaseBindActivity<ActivityNdkBinding>() {

    override fun initView(savedInstanceState: Bundle?) {
        binding.apply {
            //tvGetString.text= NativeLib.stringFromJNI()
        }
    }

}