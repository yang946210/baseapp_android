package com.example.avi.android.mediacodec

import android.content.Context
import android.media.MediaCodec
import android.media.MediaCodecInfo
import android.media.MediaFormat

class MediaCodecManager (var context: Context){

    /**
     * pcm编码
     */
    fun encodePcmToAac(){
        val mediaCodec = MediaCodec.createEncoderByType(MediaFormat.MIMETYPE_AUDIO_AAC)

        val format = MediaFormat.createAudioFormat(MediaFormat.MIMETYPE_AUDIO_AAC, 2, 2)
        //format.setInteger(MediaFormat.KEY_BIT_RATE, bitRate)
        format.setInteger(MediaFormat.KEY_AAC_PROFILE, MediaCodecInfo.CodecProfileLevel.AACObjectLC)
        mediaCodec.configure(format, null, null, MediaCodec.CONFIGURE_FLAG_ENCODE)
        mediaCodec.start()

    }

    /**
     * yuv编码
     */
    fun encodeYuvToH264(){
    }

    /**
     * pcm解码
     */
    fun reCodecPcm(){
    }

    /**
     * yuv解码
     */
    fun reCodecYuv(){
    }



}