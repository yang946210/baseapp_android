package com.example.avi
import android.content.Intent
import android.os.Bundle
import com.example.avi.menusdkapi.audiorecord.AudioRecordActivity
import com.example.avi.menusdkapi.mediarecorder.MediaRecorderActivity
import com.example.avi.menusdkapi.camera2.Camera2Activity
import com.example.avi.menusdkapi.camerax.CameraXActivity
import com.example.avi.menusdkapi.mediacodec.MediaCodecActivity
import com.example.avi.menusdkapi.mediaextractor.MediaExtractorActivity
import com.example.lib_avi.databinding.ActivityAviMenuBinding
import com.yang.ktbase.base.BaseBindActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
class AviMenuActivity : BaseBindActivity<ActivityAviMenuBinding>(), CoroutineScope by MainScope()  {
    override fun initView(savedInstanceState: Bundle?) {
        mViewBind.apply {

            /**
             * 音频采集(MediaRecorder)
             */
            tvRecording.setOnClickListener {
                startActivity(Intent(this@AviMenuActivity, MediaRecorderActivity::class.java))
            }

            /**
             * 音频采集/播放(AudioRecord)
             */
            tvAudio.setOnClickListener {
                startActivity(Intent(this@AviMenuActivity, AudioRecordActivity::class.java))
            }


            /**
             * 视频采集(Camera2)
             */
            tvCamera2.setOnClickListener {
                startActivity(Intent(this@AviMenuActivity, Camera2Activity::class.java))
            }

            /**
             * 视频采集(CameraX)
             */
            tvCameraX.setOnClickListener {
                startActivity(Intent(this@AviMenuActivity, CameraXActivity::class.java))
            }

            /**
             * MediaRecorder解封装
             */
            tvMediaExtractor.setOnClickListener {
                startActivity(Intent(this@AviMenuActivity, MediaExtractorActivity::class.java))
            }

            /**
             * mediaCodec 编/解码
             */
            tvMediaExtractor.setOnClickListener {
                startActivity(Intent(this@AviMenuActivity, MediaCodecActivity::class.java))
            }

        }
    }
}