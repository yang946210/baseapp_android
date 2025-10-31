package com.example.avi.android.camera2

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.graphics.ImageFormat
import android.graphics.SurfaceTexture
import android.hardware.camera2.CameraCaptureSession
import android.hardware.camera2.CameraCharacteristics
import android.hardware.camera2.CameraDevice
import android.hardware.camera2.CameraManager
import android.hardware.camera2.CaptureRequest
import android.media.Image
import android.media.ImageReader
import android.os.Handler
import android.os.HandlerThread
import android.os.Looper
import android.view.Surface
import android.view.TextureView
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import com.blankj.utilcode.util.LogUtils
import java.io.File
import java.util.Arrays


/**
 * camera2管理类 参考：
 * https://www.jianshu.com/p/9a2e66916fcb
 * https://www.jianshu.com/p/23e8789fbc10
 * https://www.jianshu.com/p/067889611ae7
 */
class Camera2Manager(val context: Context) {

    //相机管理类
    private var mCameraManager:CameraManager = context.getSystemService(Context.CAMERA_SERVICE) as CameraManager
    //抽象相机类
    private var mCameraDevice: CameraDevice? = null

    //相机抽象事物类
    private var mCaptureSession: CameraCaptureSession? = null
    //相机预览请求配置类
    private var mPreCaptureRequestBuilder: CaptureRequest.Builder? = null

    //图像数据
    private var mImageReader:ImageReader?=null

    //镜头id
    private var mCameraId:String?=null

    //不同类型handler
    private var cameraHandler= Handler(HandlerThread("camera").apply { start() }.looper)
    private var imageHandler=Handler (HandlerThread("image").apply { start() }.looper)
    private var backgroundHandler=Handler(Looper.getMainLooper())

    //输出文件
    private var outFile=File(context.getExternalFilesDir(null), "out_${System.currentTimeMillis()}.yuv")


    //获取相机数据并初始化
    fun getCameraInfo(context: Context){
        var info:StringBuffer = StringBuffer("相关信息：\n")
        //找到后置摄像头id
        for (id in mCameraManager.cameraIdList) {
            val cameraCharacteristics = mCameraManager.getCameraCharacteristics(id)
            val facing = cameraCharacteristics.get(CameraCharacteristics.LENS_FACING)
            //默认使用后置摄像头
            if (facing == CameraCharacteristics.LENS_FACING_BACK ) {
                mCameraId = id
            }
        }

        /**
         * 封装相机特性的只读类包括不限于：
         * 相机朝向 LENS_FACING
         * 判断闪光灯是否可用 FLASH_INFO_AVAILABLE
         * 获取所有可用 AE模式 CONTROL_AE_AVAILABLE_MODES
         * 等等。。。
         */
        val cameraCharacteristics = mCameraManager.getCameraCharacteristics(mCameraId!!)

        //摄像头儿支持等级
        val supportLevel = cameraCharacteristics.get(CameraCharacteristics.INFO_SUPPORTED_HARDWARE_LEVEL)
        if (supportLevel == null || supportLevel == CameraCharacteristics.INFO_SUPPORTED_HARDWARE_LEVEL_LEGACY){
              Toast.makeText(context, "您的手机不支持Camera2的高级特效", Toast.LENGTH_SHORT).show()
        }



        //摄像头儿支持的所有输出格式和尺寸
        val configurationMap = cameraCharacteristics.get(CameraCharacteristics.SCALER_STREAM_CONFIGURATION_MAP)
        val savePicSize = configurationMap?.getOutputSizes(ImageFormat.JPEG)
        val previewSize = configurationMap?.getOutputSizes(SurfaceTexture::class.java)
        info.append("相机支持等级$supportLevel\n")
        info.append("保存图片支持的尺寸集合${Arrays.toString(savePicSize)}\n")
        info.append("预览图片支持的尺寸集合${Arrays.toString(previewSize)}\n")
        LogUtils.d(info)
    }


    //打开摄像机
    @SuppressLint("CheckResult", "MissingPermission")
    fun openCamera(fragmentActivity: FragmentActivity,textureView: TextureView){
//        kotlin.runCatching {
//            //权限请求
//            val rxPermissions=RxPermissions(fragmentActivity)
//            rxPermissions.request(Manifest.permission.CAMERA).subscribe { success ->
//                if (success){
//                    //打开相机
//                    mCameraManager.openCamera((if (mCameraId==null) mCameraManager.cameraIdList[0] else mCameraId)!!, object :CameraDevice.StateCallback(){
//                        override fun onOpened(camera: CameraDevice) {
//                            // 表示相机打开成功，可以真正开始使用相机，创建Capture会话
//                            mCameraDevice = camera
//                            // 创建预览请求
//                            createPreviewSession(textureView)
//                        }
//
//                        override fun onDisconnected(camera: CameraDevice) {
//                            //当相机断开连接时回调该方法，需要进行释放相机的操作
//                            camera.close()
//                            mCameraDevice = null
//                        }
//
//                        override fun onError(camera: CameraDevice, error: Int) {
//                            //当相机打开失败时，需要进行释放相机的操作
//                            camera.close()
//                            mCameraDevice = null
//                            LogUtils.d("camera error : $error")
//                        }
//
//                        override fun onClosed(camera: CameraDevice) {
//                            //调用Camera.close()后的回调方法
//                            super.onClosed(camera)
//                        }
//                    }, backgroundHandler)
//
//                    //设置预览图片监听
//                    textureView.surfaceTextureListener=object :TextureView.SurfaceTextureListener{
//                        override fun onSurfaceTextureAvailable(surface: SurfaceTexture, width: Int, height: Int) {
//                            LogUtils.d("onSurfaceTextureAvailable===>$width ==$height")
//                        }
//
//                        override fun onSurfaceTextureSizeChanged(surface: SurfaceTexture, width: Int, height: Int) {
//                            LogUtils.d("onSurfaceTextureSizeChanged===>$width ==$height")
//                        }
//
//                        override fun onSurfaceTextureDestroyed(surface: SurfaceTexture): Boolean {
//                            LogUtils.d("onSurfaceTextureDestroyed===$")
//                            return false
//                        }
//
//                        override fun onSurfaceTextureUpdated(surface: SurfaceTexture) {
//                            //LogUtils.d("onSurfaceTextureUpdated===")
//                        }
//                    }
//                }
//            }
//        }.onFailure {
//            LogUtils.d("open camera fail:${it.message}")
//        }
    }


    /**
     * 配置了目标 Surface 的 Pipeline 实例
     * 一个 CameraDevice 一次只能开启一个 CameraCaptureSession
     */
    @SuppressLint("Recycle")
    fun createPreviewSession(mTextureView: TextureView){
        //初始化mImageReader
        mImageReader=ImageReader.newInstance(mTextureView.width, mTextureView.height, ImageFormat.JPEG, 2)
        mImageReader?.setOnImageAvailableListener({
            val image: Image = it.acquireNextImage()
            LogUtils.d("OnImageAvailable===>${image.width}")
            image.close()
        },imageHandler)

        //SurfaceTexture 是 Surface 和 OpenGL ES (GLES) 纹理的组合。SurfaceTexture 用于提供输出到 GLES 纹理的 Surface
        val texture: SurfaceTexture? = mTextureView.surfaceTexture
        texture?.setDefaultBufferSize(mTextureView.width, mTextureView.height)
        val surface = Surface(texture)

        LogUtils.d("createPreviewSession width=${mTextureView.width},height${mTextureView.height}")
        //CaptureRequest是向CameraCaptureSession提交Capture请求时的信息载体，其内部包括了本次Capture的参数配置和接收图像数据的Surface。
        //TEMPLATE_PREVIEW：预览模式
        //TEMPLATE_STILL_CAPTURE：拍照模式
        //TEMPLATE_RECORD：视频录制模式
        //TEMPLATE_VIDEO_SNAPSHOT：视频截图模式
        //TEMPLATE_MANUAL：手动配置参数模式
        mPreCaptureRequestBuilder = mCameraDevice?.createCaptureRequest(CameraDevice.TEMPLATE_PREVIEW)

        /**
         * 参数1：用于接受图像数据的surface集合，这里传入的是一个preview的surface
         * 参数2：用于监听 Session 状态的CameraCaptureSession.StateCallback对象
         * 参数3：用于执行CameraCaptureSession.StateCallback的Handler对象，传入null则使用当前的主线程Handler
         */

        mCameraDevice?.createCaptureSession(listOf(surface, mImageReader?.surface),object :CameraCaptureSession.StateCallback(){
            override fun onConfigured(session: CameraCaptureSession) {
                // 相机关闭时退出
                if (null == mCameraDevice) return
                mCaptureSession = session


                //配置相机参数，CaptureRequest还可以配置很多其他信息，例如图像格式、图像分辨率、传感器控制、闪光灯控制、3A(自动对焦-AF、自动曝光-AE和自动白平衡-AWB)控制等
                mPreCaptureRequestBuilder?.addTarget(surface)
                mPreCaptureRequestBuilder?.set(CaptureRequest.CONTROL_AF_MODE, CaptureRequest.CONTROL_AF_MODE_CONTINUOUS_PICTURE) // 自动对焦

                /**
                 * 参数1：CaptureRequest对象
                 * 参数2：监听Capture 状态的回调
                 * 参数3：用于执行CameraCaptureSession.CaptureCallback的Handler对象，传入null则使用当前的主线程Handler
                 */
                mCaptureSession?.setRepeatingRequest(mPreCaptureRequestBuilder!!.build(),null,null)

            }

            override fun onConfigureFailed(session: CameraCaptureSession) {

            }

            override fun onClosed(session: CameraCaptureSession) {
                if (mCaptureSession != null && mCaptureSession == session) {
                    mCaptureSession = null
                }
            }
        },cameraHandler)


        kotlin.runCatching {
            // TODO:  
        }.onFailure {
            LogUtils.d("createCameraPreviewSession fail:${it.message}")
        }

    }



    //关闭相机
    private fun closeCamera() {
        mImageReader?.close()
        mCaptureSession?.close()
        mCameraDevice?.close()
        mImageReader=null
        mCaptureSession=null
        mCameraDevice=null
    }
}


