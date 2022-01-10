package com.yang.baseapp;

import android.content.Intent;
import android.util.Log;
import android.widget.TextView;

import com.yang.base.base.BaseActivity;
import com.yang.base.util.BaseThreadHelper;
import com.yang.base.util.log.Logger;

import java.util.Timer;
import java.util.TimerTask;

/**
 * @desc 引导页
 * @author yanggq
 */

public class FirstActivity2 extends BaseActivity {

    /**
     * 倒计时时间
     */
    private int time=3;

    /**
     * 倒计时控件
     */
    private TextView tv_time;
    private Timer timer;

    @Override
    protected void beforeSetView() {
        super.beforeSetView();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_next;
    }

    @Override
    protected void findViews() {
        tv_time=findViewById(R.id.tv_time);
    }

    @Override
    protected void init() {
        tv_time.setText("倒计时："+time);
    }

    @Override
    protected void onStart() {
        super.onStart();
        threadTest();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("==========first",""+getTaskId());
    }

    /**
     * 倒计时
     */
    public  void threadTest(){
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                BaseThreadHelper.getInstance().getMainHandler().post(new Runnable() {
                    @Override
                    public void run() {
                        if (time<0){
                            timer.cancel();
                            Logger.d("调用了");
                            Intent intent=new Intent(FirstActivity2.this,MainActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(intent);
                            finish();
                        }else {
                            tv_time.setText("倒计时："+time);
                            time--;
                        }
                    }
                });
            }
        }, 0, 200);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        timer.cancel();
    }
}