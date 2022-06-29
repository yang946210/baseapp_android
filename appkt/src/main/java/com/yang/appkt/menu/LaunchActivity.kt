package com.yang.appkt.menu

import android.os.Bundle
import com.yang.appkt.busTag
import com.yang.appkt.databinding.ActivityLaunchBinding
import com.yang.ktbase.LiveDataBus
import com.yang.ktbase.base.BaseBindActivity


class LaunchActivity : BaseBindActivity<ActivityLaunchBinding>() {


    override fun initView(savedInstanceState: Bundle?) {

        binding.apply {
            tvSengBusMsg.setOnClickListener {
                LiveDataBus.with<String>(busTag).postData("from launch process")
            }
        }
    }


}


