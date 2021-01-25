package com.yang.base;

import android.content.Intent;
import android.widget.TextView;

import com.yang.base.base.BaseActivity;
import com.yang.base.util.BaseThreadHelper;

import java.util.Timer;
import java.util.TimerTask;

/**
 * @desc 引导页
 * @author yanggq
 * @time 2020/12/29
 */

public class FirstActivity extends BaseActivity {

    /**
     * 倒计时时间
     */
    private int time=0;

    /**
     * 倒计时控件
     */
    private TextView tv_time;

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
        threadTest();

    }

    /**
     * 倒计时
     */
    public  void threadTest(){
        Timer timer=new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                BaseThreadHelper.getInstance().getMainHandler().post(new Runnable() {
                    @Override
                    public void run() {
                        if (time<0){
                            timer.cancel();
                            startActivity(new Intent(FirstActivity.this,MainActivity.class));
                            finish();
                        }else {
                            tv_time.setText("倒计时："+time);
                            time--;
                        }
                    }
                });
            }
        }, 0, 1000);
    }



}