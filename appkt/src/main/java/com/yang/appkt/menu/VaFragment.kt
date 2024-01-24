package com.yang.appkt.menu

import android.content.Intent
import android.os.Bundle
import com.example.avi.android.audiorecord.AudioRecordActivity
import com.example.avi.android.camera2.Camera2Activity
import com.example.avi.android.camerax.CameraXActivity
import com.example.avi.android.mediacodec.MediaCodecActivity
import com.example.avi.android.mediaextractor.MediaExtractorActivity
import com.example.avi.android.mediarecorder.MediaRecorderActivity
import com.yang.appkt.databinding.FragmentVaBinding
import com.yang.ktbase.fragment.BaseBindFragment

class VaFragment : BaseBindFragment<FragmentVaBinding>() {

    override fun initView(savedInstanceState: Bundle?) {
        mBinding.apply {

            /**
             * 音频采集(MediaRecorder)
             */
            tvRecording.setOnClickListener {
                startActivity(Intent(context, MediaRecorderActivity::class.java))
            }

            /**
             * 音频采集/播放(AudioRecord)
             */
            tvAudio.setOnClickListener {
                startActivity(Intent(context, AudioRecordActivity::class.java))
            }


            /**
             * 视频采集(Camera2)
             */
            tvCamera2.setOnClickListener {
                startActivity(Intent(context, Camera2Activity::class.java))
            }

            /**
             * 视频采集(CameraX)
             */
            tvCameraX.setOnClickListener {
                startActivity(Intent(context, CameraXActivity::class.java))
            }

            /**
             * MediaRecorder解封装
             */
            tvMediaExtractor.setOnClickListener {
                startActivity(Intent(context, MediaExtractorActivity::class.java))
            }

            /**
             * mediaCodec 编/解码
             */
            tvMediaExtractor.setOnClickListener {
                startActivity(Intent(context, MediaCodecActivity::class.java))
            }

        }
    }
}