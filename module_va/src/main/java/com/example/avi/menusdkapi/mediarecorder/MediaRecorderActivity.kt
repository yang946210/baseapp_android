package com.example.avi.menusdkapi.mediarecorder


import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi

import com.example.lib_avi.databinding.ActivityRecordingBinding
import com.yang.ktbase.base.BaseBindActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope

class MediaRecorderActivity :  BaseBindActivity<ActivityRecordingBinding>(), CoroutineScope by MainScope() {

    private val title="""
        音频采集方式：
            1.使用 MediaRecorder (简单，录制音频是处理后的)
            2.使用 AudioRecord  (比较专业，输出是PCM,支持边录边播)
    """.trimIndent()

    @RequiresApi(Build.VERSION_CODES.O)
    override fun initView(savedInstanceState: Bundle?) {
        mViewBind.apply {

            tvTitle.text=title

            tvGetPermission.setOnClickListener {
                MediaRecorderHelper.getPermission(this@MediaRecorderActivity)
            }

            tvStart1.setOnClickListener {
                MediaRecorderHelper.start(tvLength)
            }

            tvEnd1.setOnClickListener {
                MediaRecorderHelper.stop()
            }
        }

    }

}