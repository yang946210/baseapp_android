package com.example.avi.helper

import android.Manifest
import android.annotation.SuppressLint
import android.media.MediaRecorder
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.fragment.app.FragmentActivity
import com.blankj.utilcode.util.LogUtils
import com.blankj.utilcode.util.ToastUtils
import com.example.lib_avi.databinding.ActivityRecordingBinding
import com.tbruyelle.rxpermissions3.Permission
import com.tbruyelle.rxpermissions3.RxPermissions
import com.yang.ktbase.BaseHelper
import java.io.File
import java.util.function.Consumer


@RequiresApi(Build.VERSION_CODES.O)
object MediaRecorderHelper {

    private var mMediaRecorder: MediaRecorder = MediaRecorder();

    init {
        try {
            /* ②setAudioSource/setVedioSource */
            mMediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);// 设置麦克风
            /*
             * ②设置输出文件的格式：THREE_GPP/MPEG-4/RAW_AMR/Default THREE_GPP(3gp格式
             * ，H263视频/ARM音频编码)、MPEG-4、RAW_AMR(只支持音频且音频编码要求为AMR_NB)
             */
            mMediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
            /* ②设置音频文件的编码：AAC/AMR_NB/AMR_MB/Default 声音的（波形）的采样 */
            mMediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AAC);

            val fileName =
                File(BaseHelper.getContext().externalCacheDir, "${System.currentTimeMillis()}.m4a")

            LogUtils.d("=========fileName${fileName}")
            /* ③准备 */
            if(Build.VERSION.SDK_INT < 26){
                //若api低于26，调用setOutputFile(String path)
                mMediaRecorder.setOutputFile(fileName.absolutePath);
            }else{
                //若API高于26 使用setOutputFile(File path)
                mMediaRecorder.setOutputFile(fileName);
            }

            mMediaRecorder.prepare();

            /* ④开始 */

        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    @SuppressLint("CheckResult")
    fun getPermission(activity: FragmentActivity){
        val rxp=RxPermissions(activity)
        rxp.request(Manifest.permission.RECORD_AUDIO,Manifest.permission.WRITE_EXTERNAL_STORAGE).subscribe {
            ToastUtils.showLong("result====${it}")
        }
    }

    /**
     * 开始
     */
    fun start() {
        ToastUtils.showLong("开始")
        mMediaRecorder.start();
    }

    /**
     * 结束
     */
    fun stop() {
        ToastUtils.showLong("结束")
        try {
            mMediaRecorder.stop()
            mMediaRecorder.release()
        } catch (e: RuntimeException) {
            mMediaRecorder.reset()
            mMediaRecorder.release()
        }
    }
}