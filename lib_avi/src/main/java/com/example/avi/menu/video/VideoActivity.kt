package com.example.avi.menu.video

import android.os.Bundle
import com.example.lib_avi.databinding.ActivityCameraBinding
import com.yang.ktbase.base.BaseBindActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope

class VideoActivity :  BaseBindActivity<ActivityCameraBinding>(), CoroutineScope by MainScope() {

    override fun initView(savedInstanceState: Bundle?) {

    }


}