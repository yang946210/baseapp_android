package com.yang.baseapp.fragment;

import android.os.Message;
import android.view.View;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.yang.baseapp.R;
import com.yang.baseapp.base.BaseConfig;
import com.yang.base.base.BaseFragment;
import com.yang.base.util.BaseHandlerHelper;
import com.yang.base.util.BaseThreadHelper;
import com.yang.base.util.BaseToastHelper;



/***
 * @desc handler，线程相关功能展示
 * @author yangguoq
 */

public class HandlerDemoFragment extends BaseFragment implements View.OnClickListener, BaseHandlerHelper.HandlerListener {

    private TextView tv_isMainThread,tv_startThread,tv_sendMessage;

    @Override
    protected int getFragmentLayoutId() {
        return R.layout.fragment_handler_demo;
    }

    @Override
    protected void findViews() {
        tv_isMainThread=findViewById(R.id.tv_isMainThread);
        tv_startThread=findViewById(R.id.tv_startThread);
        findViewById(R.id.tv_start).setOnClickListener(this);
        findViewById(R.id.tv_sendMessage).setOnClickListener(this);
        BaseHandlerHelper.getInstance().addHandleListener(this);
    }

    @Override
    protected void init() {
        BaseThreadHelper.getInstance().run(new BaseThreadHelper.Task() {
            @Override
            public void threadRun() {
                String isMainThread= "当前线程名:"+Thread.currentThread().getName()+"是否是主线程："+BaseThreadHelper.getInstance().isMainThread();
                BaseThreadHelper.getInstance().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        tv_isMainThread.setText(isMainThread);
                    }
                });
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_start:
                startNewThread();
                break;
            case R.id.tv_sendMessage:
                sendMessage();
                break;
        }
    }

    /**
     * 开始一个子线程
     */
    private void startNewThread(){
        BaseThreadHelper.getInstance().run(new BaseThreadHelper.Task() {
            @Override
            public void threadRun() {
                String newThread = "开启一个新的可监听超时的子线程:" + Thread.currentThread().getName();

                BaseThreadHelper.getInstance().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        tv_startThread.setText(newThread);
                    }
                });
            }
        }, 3000, new BaseThreadHelper.Task.OnTimeoutListener() {
            @Override
            public void onTimeout(Thread thread) {
                BaseThreadHelper.getInstance().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        BaseToastHelper.showToast(thread.getName()+"线程超时了");
                    }
                });
            }
        });
    }

    /**
     * 发送全局消息
     */
    private void sendMessage(){
        JSONObject object=new JSONObject();
        object.put("message","message show");
        BaseHandlerHelper.getInstance().sendMessage(BaseConfig.message_test1,0,object);
        BaseHandlerHelper.getInstance().sendMessage(BaseConfig.message_test2);
    }



    @Override
    public void handleMessage(Message msg, boolean mainThread) {
        if (msg.what==BaseConfig.message_test1){
            if (msg.obj instanceof JSONObject){
                BaseToastHelper.showToast("收到了消息"+((JSONObject)msg.obj).getString("message"));
            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        BaseHandlerHelper.getInstance().removeHandleListener(this);
    }
}
