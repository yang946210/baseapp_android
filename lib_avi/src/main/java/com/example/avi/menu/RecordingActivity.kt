package com.example.avi.menu

import android.Manifest
import android.annotation.SuppressLint
import android.media.MediaRecorder
import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import com.blankj.utilcode.util.ToastUtils
import com.example.lib_avi.databinding.ActivityRecordingBinding
import com.tbruyelle.rxpermissions3.RxPermissions
import com.yang.ktbase.base.BaseBindActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import java.io.File

/**
 * 录音相关
 */
class RecordingActivity : BaseBindActivity<ActivityRecordingBinding>(),
    CoroutineScope by MainScope() {

    private var mediaRecorder: MediaRecorder? = null;

    @SuppressLint("CheckResult")
    @RequiresApi(Build.VERSION_CODES.O)
    override fun initView(savedInstanceState: Bundle?) {
        binding.apply {
            tvStartMediaRecorder.setOnClickListener {

                val rxPermissions =  RxPermissions(this@RecordingActivity)
                rxPermissions.request(Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.RECORD_AUDIO)
                    .subscribe { granted ->
                        if (granted) {
                            startMediaRecorder()
                        } else {
                           ToastUtils.showLong("给权限啊")
                        }
                    }
            }

            tvStopMediaRecorder.setOnClickListener {
                stopMediaRecorder()
            }
        }
    }


    /**
     * 使用MediaRecorder
     * 开始录音
     */
    @RequiresApi(Build.VERSION_CODES.O)
    private fun startMediaRecorder() {
        mediaRecorder = MediaRecorder()
        mediaRecorder?.apply {
            setAudioSource(MediaRecorder.AudioSource.MIC) // 设置麦克风
            setOutputFormat(MediaRecorder.OutputFormat.MPEG_4) //设置文件输出格式
            setAudioEncoder(MediaRecorder.AudioEncoder.AAC) //设置音频文件编码格式
            setOutputFile(File(getExternalFilesDir("audio"),"${System.currentTimeMillis()}.mp4"))
            prepare()
            start()
        }
    }

    private fun stopMediaRecorder() {
        mediaRecorder?.apply {
            runCatching {
                stop()
                release()
            }.onFailure {
                reset()
                release()
            }
        }
        mediaRecorder = null
    }


}
