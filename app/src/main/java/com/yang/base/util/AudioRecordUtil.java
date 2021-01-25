package com.yang.base.util;

import android.media.AudioFormat;
import android.media.AudioRecord;
import android.media.MediaRecorder;
import android.util.Log;

/***
 * @desc 全局保存AudioRecord索引，因为调用和关闭不在一个类之中。
 * @time 2020/11/23
 * @author yangguoq
 */

public class AudioRecordUtil {

    /**
     * 录音
     */
    private AudioRecord mAudioRecorder;

    private static AudioRecordUtil instance =new AudioRecordUtil();

    private AudioRecordUtil(){}

    public static AudioRecordUtil getInstance(){
        return instance;
    }

    public void startAudioRecord(int sampleRate){
        int bufferSizeInBytes = AudioRecord.getMinBufferSize(sampleRate, AudioFormat.CHANNEL_IN_MONO, AudioFormat.ENCODING_PCM_16BIT);
        mAudioRecorder = new AudioRecord(MediaRecorder.AudioSource.DEFAULT, sampleRate,
                AudioFormat.CHANNEL_IN_MONO, AudioFormat.ENCODING_PCM_16BIT, bufferSizeInBytes);
        if (mAudioRecorder.getState() == AudioRecord.STATE_UNINITIALIZED) {
            throw new IllegalStateException("Failed to initialize AudioRecord!");
        }
        mAudioRecorder.startRecording();
    }

    /**
     * 获取实列
     * @return
     */
    public AudioRecord getAudioRecord(){
        if (mAudioRecorder==null){
            throw new IllegalStateException("AudioRecord! is not init");
        }
        return mAudioRecorder;
    }

    /**
     * 关闭
     */
    public void closeAudioRecord(){
        Log.d("=========", "closeAudioRecord:=== ");
        if (mAudioRecorder!=null&&mAudioRecorder.getRecordingState() == AudioRecord.RECORDSTATE_RECORDING){
            mAudioRecorder.release();
            mAudioRecorder=null;
        }
    }



}
