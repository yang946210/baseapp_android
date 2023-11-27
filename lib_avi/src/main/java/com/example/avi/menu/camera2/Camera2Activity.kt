package com.example.avi.menu.camera2

import android.os.Bundle

import com.example.lib_avi.databinding.ActivityCameraBinding
import com.yang.ktbase.base.BaseBindActivity

class Camera2Activity : BaseBindActivity<ActivityCameraBinding>() {


    //自定义camera2 管理类
    private lateinit var cameraManager: Camera2Manager

    override fun initView(savedInstanceState: Bundle?) {
        binding.apply {
            //获取对应摄像头的基本信息
            tvGetInfo.setOnClickListener {
                cameraManager.getCameraInfo(this@Camera2Activity)
            }
            //打开相机并开启预览
            tvPreview.setOnClickListener {
                cameraManager.openCamera(this@Camera2Activity,ttvCamera2)
            }
        }
     }

    override fun initData(savedInstanceState: Bundle?) {
        cameraManager=Camera2Manager(this@Camera2Activity)
    }


}