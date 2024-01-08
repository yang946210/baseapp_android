package com.example.jetpack.activity

import android.os.Bundle
import com.example.lib_jetpack.databinding.ActivityNdkBinding
import com.yang.ktbase.activity.BaseBindActivity


/**
 * ndk的一些验证
 */
class NdkActivity : BaseBindActivity<ActivityNdkBinding>() {

    override fun initView(savedInstanceState: Bundle?) {
        mBinding.apply {
            //tvGetString.text= NativeLib.stringFromJNI()
        }
    }

}