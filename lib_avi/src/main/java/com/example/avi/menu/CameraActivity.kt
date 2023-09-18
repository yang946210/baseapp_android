package com.example.avi.menu

import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.camera.core.CameraSelector
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.core.content.ContextCompat
import com.example.lib_avi.R
import com.example.lib_avi.databinding.ActivityCameraBinding
import com.yang.ktbase.base.BaseBindActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope

class CameraActivity :  BaseBindActivity<ActivityCameraBinding>(), CoroutineScope by MainScope() {

    override fun initView(savedInstanceState: Bundle?) {

    }


    @RequiresApi(Build.VERSION_CODES.P)
    private fun initCameraX(){
        val cameraProviderFuture = ProcessCameraProvider.getInstance(this)
        cameraProviderFuture.addListener({
            val cameraProvider = cameraProviderFuture.get()  //获取相机信息
            val cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA  //默认后置摄像头

        }, ContextCompat.getMainExecutor(this))
    }
}