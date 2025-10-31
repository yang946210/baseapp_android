package com.example.avi.android.audiorecord

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.example.lib_avi.databinding.ActivityAudioRecordBinding
import com.yang.ktbase.base.BaseActivity
import com.yang.ktbase.util.getSizeWithUnit
import kotlinx.coroutines.launch
import java.io.File
import java.util.Timer
import kotlin.concurrent.timerTask

class AudioRecordActivity : BaseActivity<ActivityAudioRecordBinding>() {

    @SuppressLint("SetTextI18n")
    override fun bindView(savedInstanceState: Bundle?) {


        val audioManager=AudioRecordManager(this@AudioRecordActivity)

        var mTimer:Timer?=null

        mBinding.apply {

            tvStart.setOnClickListener {
                val file=File(externalCacheDir,"audio_${System.currentTimeMillis()}.pcm")
                audioManager.startRecord(outFile = file)

                mTimer=Timer().apply {
                    schedule(timerTask {
                        lifecycleScope.launch() {
                            tvFileSize.text = "文件长度：${file.getSizeWithUnit()}"
                        }
                    },1000,1000)
                }
            }


            tvStop.setOnClickListener {
                audioManager.stopRecord()
                mTimer?.cancel()
            }

            tvPlay.setOnClickListener {
                audioManager.play("pcm_test1.pcm")
            }
        }
    }

}