package com.example.avi.menu.camerax

import android.annotation.SuppressLint
import android.content.ContentValues
import android.graphics.ImageFormat
import android.graphics.PixelFormat
import android.os.Build
import android.provider.MediaStore
import android.util.Size
import android.widget.Toast
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.video.MediaStoreOutputOptions
import androidx.camera.video.Quality
import androidx.camera.video.QualitySelector
import androidx.camera.video.Recorder
import androidx.camera.video.Recording
import androidx.camera.video.VideoCapture
import androidx.camera.video.VideoRecordEvent
import androidx.camera.view.PreviewView
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
import com.blankj.utilcode.util.LogUtils
import com.blankj.utilcode.util.ToastUtils
import java.io.File
import java.util.concurrent.Executors


class CameraXManager(var context: FragmentActivity) {

    //预览实例
    private lateinit var preview: Preview

    //摄像头选择实例
    private lateinit var cameraSelector: CameraSelector

    //图片分析实例
    private lateinit var imageAnalyzer: ImageAnalysis

    //拍照实例
    private lateinit var imageCapture: ImageCapture

    //视频录制
    private lateinit var videoCapture: VideoCapture<Recorder>

    /**
     * 录制中实体
     */
    private var videoRecording:Recording?=null

    /**
     * 为一个预览对象绑定预览
     */
    fun bindPreview(previewView: PreviewView) {
        ProcessCameraProvider.getInstance(context).apply {
            addListener({
                // 用于将相机的生命周期绑定到应用程序进程中的 LifecycleOwner
                val cameraProvider: ProcessCameraProvider = get()
                initPreView(previewView)
                initImageAnalysis(previewView.width, previewView.height)
                setCameraSelector(true)
                initImageCapture()
                initRecording()
                val recorder = Recorder.Builder()
                    .setQualitySelector(QualitySelector.from(Quality.HIGHEST))
                    .build()
                videoCapture= VideoCapture.withOutput(recorder)
                //开启预览
                kotlin.runCatching {
                    // 先取消所有绑定
                    cameraProvider.unbindAll()
                    //绑定
                    cameraProvider.bindToLifecycle(
                        context,
                        cameraSelector,
                        preview,
                        imageAnalyzer,
                        videoCapture,
                        imageCapture
                    )

                }.onFailure {
                    Toast.makeText(context, "绑定失败：${it.message}", Toast.LENGTH_SHORT).show()
                }
            }, ContextCompat.getMainExecutor(context))
        }
    }

    /**
     * 预览配置
     */
    private fun initPreView(previewView: PreviewView) {
        preview = Preview.Builder().build().apply {
            setSurfaceProvider(previewView.surfaceProvider)
        }
    }

    /**
     * 图片分析配置
     */
    private fun initImageAnalysis(w: Int, h: Int) {
        LogUtils.d("initImageAnalysis :${w}:${h}")
        imageAnalyzer = ImageAnalysis.Builder().apply {
            setTargetResolution(Size(w, h))
            //setOutputImageFormat(ImageAnalysis.OUTPUT_IMAGE_FORMAT_RGBA_8888)
            //setOutputImageFormat(ImageAnalysis.OUTPUT_IMAGE_FORMAT_YUV_420_888)
        }.build().apply {
            setAnalyzer(Executors.newSingleThreadExecutor()) {
                val imgInfoLog = StringBuffer()
                //imgInfoLog.append("format ${it.format==PixelFormat.RGBA_8888}\n")
                imgInfoLog.append("format is yuv_420_888:${it.format==ImageFormat.YUV_420_888}\n")
                imgInfoLog.append("size ${it.width}:${it.height}\n")
                imgInfoLog.append("imageInfo： ${it.imageInfo}\n")

                LogUtils.d(imgInfoLog)
                //val bt=it.toBitmap()
                //it.close()
            }
        }
    }

    /**
     *初始化拍照配置
     */
    private fun initImageCapture() {
        imageCapture = ImageCapture.Builder().build()
    }

    /**
     *初始化录像配置
     */
    private fun initRecording() {
        videoCapture = VideoCapture.withOutput(Recorder.Builder().also {
            it.setExecutor(Executors.newSingleThreadExecutor())
        }.build())
    }

    /**
     * 摄像头选择配置
     */
    private fun setCameraSelector(back: Boolean) {
        cameraSelector = when (back) {
            true -> {
                CameraSelector.DEFAULT_BACK_CAMERA
            }

            false -> {
                CameraSelector.DEFAULT_FRONT_CAMERA
            }
        }
    }

    /**
     * 拍照(先申请权限)
     */
    @SuppressLint("CheckResult")
    fun takePic(outFile: File) {
        if (!outFile.exists()) outFile.mkdirs()
        val outputFileOptions = ImageCapture.OutputFileOptions.Builder(outFile).build()

        imageCapture.takePicture(outputFileOptions, Executors.newSingleThreadExecutor(),
            object : ImageCapture.OnImageSavedCallback {
                override fun onError(error: ImageCaptureException) {
                    LogUtils.d("onError ==>${error.message}")
                }

                override fun onImageSaved(outputFileResults: ImageCapture.OutputFileResults) {
                    ToastUtils.showLong("success")
                    LogUtils.d("onImageSaved ==>${outputFileResults.savedUri}")
                }
            })
    }

    /**
     * 开始录制
     */
    @SuppressLint("CheckResult")
    fun takeVideo() {
        val name = "CameraX-recording-${System.currentTimeMillis()}.mp4"
        val contentValues = ContentValues().apply {
            put(MediaStore.Video.Media.DISPLAY_NAME, name)
        }
        val mediaStoreOutput = MediaStoreOutputOptions.Builder(
            context.contentResolver,
            MediaStore.Video.Media.EXTERNAL_CONTENT_URI
        ).setContentValues(contentValues).build()

        videoRecording =videoCapture.output.prepareRecording(context,mediaStoreOutput).start(Executors.newSingleThreadExecutor()){
            when (it) {
                is VideoRecordEvent.Start -> {
                    ToastUtils.showLong("start")
                }

                is VideoRecordEvent.Finalize -> {
                    ToastUtils.showLong("end")
                }
            }
        }
    }

    /**
     * 停止录制
     */
    fun stopVideo(){
        videoRecording?.stop()
        videoRecording=null
    }
}

