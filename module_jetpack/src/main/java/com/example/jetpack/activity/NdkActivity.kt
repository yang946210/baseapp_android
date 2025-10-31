package com.example.jetpack.activity

import android.os.Bundle
import com.example.lib_jetpack.databinding.ActivityNdkBinding
import com.yang.ktbase.base.BaseActivity


/**
 * ndk的一些验证
 */
class NdkActivity : BaseActivity<ActivityNdkBinding>() {

    override fun bindView(savedInstanceState: Bundle?) {
        mBinding.apply {
            //tvGetString.text= NativeLib.stringFromJNI()
        }
    }

}