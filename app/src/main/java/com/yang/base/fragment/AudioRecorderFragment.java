package com.yang.base.fragment;

import android.Manifest;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;

import com.yang.base.base.BaseFragment;
import com.yang.base.base.PermissionListener;
import com.yang.base.R;
import com.yang.base.util.AudioRecordUtil;

import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;
import okio.ByteString;

/***
 * @desc
 * @time 2020/11/23
 * @author yangguoq
 */

public class AudioRecorderFragment extends BaseFragment implements View.OnClickListener {

    private   int SAMPLE_RATE = 16000;

    private   int SAMPLES_PER_FRAME = 1200;

    private   String  WebSocketUrl = "ws://140.143.199.16:9083/websocket";


    @Override
    protected int getFragmentLayoutId() {
        return R.layout.fragment_audiorecorder;
    }

    @Override
    protected void findViews() {
        view.findViewById(R.id.bt_toasts).setOnClickListener(this);
        view.findViewById(R.id.bt_stop).setOnClickListener(this);
    }

    @Override
    protected void init() {
        String[] permissions=new String[]{ Manifest.permission.RECORD_AUDIO,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE};
        requestRuntimePermission(permissions, new PermissionListener() {
            @Override
            public void success() {
                Log.d("=====","权限success");
            }
            @Override
            public void fail(List<String> failList) {
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bt_toasts:
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        runWebSocket();
                    }
                }).start();
                break;
            case R.id.bt_stop:
                AudioRecordUtil.getInstance().closeAudioRecord();
                break;
        }
    }

    public void runWebSocket() {
        WebSocket webSocket=null;
        try {
            OkHttpClient okHttpClient = new OkHttpClient.Builder()
                    .connectTimeout(5, TimeUnit.SECONDS)
                    .writeTimeout(10, TimeUnit.SECONDS)
                    .readTimeout(10, TimeUnit.SECONDS)
                    .build();
             webSocket = okHttpClient.newWebSocket(new Request.Builder().url(WebSocketUrl).build(), new WebSocketListener() {

                @Override
                public void onOpen(@NonNull WebSocket webSocket, @NonNull Response response) {
                    Log.d("=====","录音WebSocket启动");

                    //TDDO webview.loadUrl("javascript:socketOpen()");
                }

                @Override
                public void onClosed(@NonNull WebSocket webSocket, int code, @NonNull String reason) {
                    Log.d("=====","录音WebSocket已关闭");
                    //TDDO webview.loadUrl("javascript:socketClosed(code, reason)");
                }

                @Override
                public void onClosing(@NonNull WebSocket webSocket, int code, @NonNull String reason) {
                    Log.d("=====","录音WebSocket正在关闭");
                    //TDDO webview.loadUrl("javascript:socketClosing(code, reason)");
                }

                @Override
                public void onFailure(@NonNull WebSocket webSocket, @NonNull Throwable t, Response response) {
                    Log.d("=====","录音WebSocket连接错误", t);
                    AudioRecordUtil.getInstance().closeAudioRecord();
                    //TDDO webview.loadUrl("javascript:socketFail(t.getMessage())");
                }

                @Override
                public void onMessage(@NonNull WebSocket webSocket, @NonNull String text) {
                    Log.d("=====message",text);
                    //TDDO webview.loadUrl("javascript:socketMessage(" + text + ")");
                }
            });
//        int bufferSizeInBytes = AudioRecord.getMinBufferSize(SAMPLE_RATE, AudioFormat.CHANNEL_IN_MONO, AudioFormat.ENCODING_PCM_16BIT);
//        mAudioRecorder = new AudioRecord(MediaRecorder.AudioSource.DEFAULT, SAMPLE_RATE,
//                AudioFormat.CHANNEL_IN_MONO, AudioFormat.ENCODING_PCM_16BIT, bufferSizeInBytes);
//        if (mAudioRecorder.getState() == AudioRecord.STATE_UNINITIALIZED) {
//            throw new IllegalStateException("Failed to initialize AudioRecord!");
//        }
//        mAudioRecorder.startRecording();
            AudioRecordUtil.getInstance().startAudioRecord(SAMPLE_RATE);

            byte[] byteBuffer = new byte[SAMPLES_PER_FRAME];
            while ( AudioRecordUtil.getInstance().getAudioRecord().read(byteBuffer, 0, SAMPLES_PER_FRAME) > 0) {
                webSocket.send(ByteString.of(byteBuffer));
                //TDDO webview.loadUrl("javascript:setSnr(" + Math.abs((byteBuffer[0] & 0xff) << 8 | byteBuffer[1]) + ")");
            }
        }catch (Throwable e){
            AudioRecordUtil.getInstance().closeAudioRecord();
        }finally {
            if (webSocket!=null){
                webSocket.close(1000, "=====正常关闭");
            }
        }


    }


}
