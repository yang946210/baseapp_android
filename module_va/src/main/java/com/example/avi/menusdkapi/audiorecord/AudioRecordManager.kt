package com.example.avi.menusdkapi.audiorecord

import android.annotation.SuppressLint
import android.content.Context
import android.media.AudioAttributes
import android.media.AudioAttributes.CONTENT_TYPE_MUSIC
import android.media.AudioFormat
import android.media.AudioManager
import android.media.AudioRecord
import android.media.AudioTrack
import android.media.MediaRecorder
import com.blankj.utilcode.util.LogUtils
import com.yang.ktbase.base.BaseApp
import java.io.File


class AudioRecordManager(val context: Context) {

    //录音类
    private lateinit var record: AudioRecord

    //输出目录
    private lateinit var outFile: File

    //输出ing线程
    private lateinit var ioThread: Thread

    //录制状态
    private var isRecord = false


    /**
     * 开始录制
     */
    @SuppressLint("MissingPermission")
    fun startRecord(
        audioSource: Int = MediaRecorder.AudioSource.MIC,  // 音频采集的输入源
        sampleRateInHz: Int = 44100,    //采样率。现在能够保证在所有设备上使用的采样率是44100Hz
        channelConfig: Int = AudioFormat.CHANNEL_IN_MONO, //通道数的配置，CHANNEL_IN_MONO（单通道），CHANNEL_IN_STEREO（双通道）
        audioFormat: Int = AudioFormat.ENCODING_PCM_16BIT, //返回的音频数据的格式 ENCODING_PCM_16BIT（16bit），ENCODING_PCM_8BIT（8bit）
        bufferSizeInBytes: Int = AudioRecord.getMinBufferSize(
            sampleRateInHz,
            channelConfig,
            audioFormat
        ),  //内部的音频缓冲区的大小，该缓冲区的值不能低于一帧“音频帧”（Frame）的大小
        outFile: File = File(
            BaseApp.getAppCtx().externalCacheDir,
            "audio_${System.currentTimeMillis()}.pcm"
        )
    ) {
        this.outFile = outFile
        //初始化
        record =
            AudioRecord(audioSource, sampleRateInHz, channelConfig, audioFormat, bufferSizeInBytes)

        //开启子线程收集数据
        ioThread = Thread {
            //设置线程优先级
            android.os.Process.setThreadPriority(android.os.Process.THREAD_PRIORITY_URGENT_AUDIO)

            //并开启采集
            record.startRecording()
            isRecord = true
            var buffer: Int
            val bytes = ByteArray(bufferSizeInBytes)

            while (isRecord) {

                //读取缓冲区数据
                buffer = record.read(bytes, 0, bufferSizeInBytes)

                //错误就跳到下次
                if (buffer == AudioRecord.ERROR_INVALID_OPERATION || buffer == AudioRecord.ERROR_BAD_VALUE) {
                    LogUtils.d("buffer error =${buffer}")
                    continue
                }
                //没有数据就结束了
                if (buffer == 0 || buffer == -1) {
                    LogUtils.d("buffer end =${buffer}")
                    break
                }
                //LogUtils.d("buffer length=${buffer}and file length=${outFile.length()}")
                outFile.appendBytes(bytes)
            }
        }
        runCatching {
            ioThread.start()
        }.onFailure {
        }
    }

    fun stopRecord() {
        kotlin.runCatching {
            isRecord = false
            record.stop()
            record.release()
            ioThread.stop()
        }
    }


    /**
     * 播放刚刚录制的音频
     */
    fun play(assentFileName:String) {
        //STATIC模式：一次性将所有的数据放到一个固定的buffer，然后直接传送给AudioTrack，简单有效，通常应用于播放铃声或者系统提示音等，占用内存较少的音频数据
        //STREAM模式：一次一次的将音频数据流写入到AudioTrack对象中，并持续处于阻塞状态，当数据从Java层到Native层执行播放完毕后才返回，这种方式可以避免由于音频过大导致内存占用过多。当然对应的不足就是总是在java和native层进行交互，并且阻塞直到播放完毕，效率损失较大。
        val mode = AudioTrack.MODE_STREAM

        //audio配置
        val audioAttributes = AudioAttributes.Builder()
            .setContentType(CONTENT_TYPE_MUSIC)
            .setUsage(AudioAttributes.USAGE_MEDIA)
            .build()

        //声音格式配置
        val audioFormat = AudioFormat.Builder()
            .setSampleRate(44100)
            .setChannelMask(AudioFormat.CHANNEL_OUT_MONO)
            .setEncoding(AudioFormat.ENCODING_PCM_16BIT)
            .build()

        //最小帧计算
        val bufferSize = AudioTrack.getMinBufferSize(
            44100,
            AudioFormat.CHANNEL_OUT_MONO,
            AudioFormat.ENCODING_PCM_16BIT
        )

        //初始化声音播放类
        val audioTrack = AudioTrack(audioAttributes, audioFormat, bufferSize, mode, AudioManager.AUDIO_SESSION_ID_GENERATE)

        Thread {
            val buffer = ByteArray(bufferSize)
            var len: Int

            //val ins = FileInputStream(outFile)
            val input =  BaseApp.getAppCtx().assets.open(assentFileName)

            while (input.read(buffer).also { len = it } > 0) {
                audioTrack.write(buffer, 0, len)
            }
            audioTrack.stop()
            audioTrack.release()
        }.start()
        audioTrack.play()
    }
}