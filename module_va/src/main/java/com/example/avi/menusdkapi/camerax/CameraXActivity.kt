package com.example.avi.menusdkapi.camerax

import android.Manifest
import android.annotation.SuppressLint
import android.os.Bundle
import com.example.lib_avi.databinding.ActivityCameraXactivityBinding
import com.tbruyelle.rxpermissions3.RxPermissions
import com.yang.ktbase.base.BaseBindActivity
import java.io.File


val note="""
    1.Surface(填充图像数据的内存空间)、SurfaceView(显示Surface 的View)、SurfaceHolder(对外暴露Surface的接口)
    2
    
""".trimIndent()

/**
 * camerax 笔记
 */
class CameraXActivity : BaseBindActivity<ActivityCameraXactivityBinding>() {


    @SuppressLint("CheckResult")
    override fun initView(savedInstanceState: Bundle?) {
        //自定义相机管理类
        val cameraXManager=CameraXManager(this@CameraXActivity)

        mBinding.apply {
            tvNote.text = note

            //初始化及预览
            tvPreview.setOnClickListener {
                cameraXManager.bindPreview(pvMainView)
            }

            //拍照
            tvTakePic.setOnClickListener {
                val rxP= RxPermissions(this@CameraXActivity)
                rxP.request(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE).subscribe {
                    if (it) {
                        cameraXManager.takePic(File(getExternalFilesDir("camerax"),"pic${System.currentTimeMillis()}.JPEG"))
                    }
                }
            }

            //录像
            tvTakeVideo.setOnClickListener {
                cameraXManager.takeVideo()
            }

            //录像
            tvStopVideo.setOnClickListener {
                cameraXManager.stopVideo()
            }

            //采集yuv视频数据
            tvGetYuv.setOnClickListener {
                cameraXManager.startSave()
            }

            //停止采集yuv视频数据
            tvStopGetYuv.setOnClickListener {
                cameraXManager.stopSave()
            }
        }
    }
}