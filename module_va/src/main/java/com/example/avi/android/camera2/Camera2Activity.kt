package com.example.avi.android.camera2

import android.os.Bundle

import com.example.lib_avi.databinding.ActivityCameraBinding
import com.yang.ktbase.base.BaseActivity

class Camera2Activity : BaseActivity<ActivityCameraBinding>() {


    //自定义camera2 管理类
    private lateinit var cameraManager: Camera2Manager

    override fun bindView(savedInstanceState: Bundle?) {
        mBinding.apply {
            //获取对应摄像头的基本信息
            tvGetInfo.setOnClickListener {
                cameraManager.getCameraInfo(this@Camera2Activity)
            }
            //打开相机并开启预览
            tvPreview.setOnClickListener {
                cameraManager.openCamera(this@Camera2Activity,ttvCamera2)
            }
        }
        cameraManager=Camera2Manager(this@Camera2Activity)
     }

}