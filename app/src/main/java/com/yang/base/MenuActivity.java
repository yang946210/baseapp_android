package com.yang.base;

import android.util.Log;
import android.view.View;

import com.yang.base.base.BaseActivity;
import com.yang.base.util.BaseThreadHelper;

public class MenuActivity extends BaseActivity {


    @Override
    protected int getLayoutId() {
        return R.layout.activity_menu;
    }

    @Override
    protected void findViews() {

    }

    @Override
    protected void init() {
    }


    public void thread(View view){
      start();
    }

    private static void start(){
        final int[] index = {0};
        BaseThreadHelper.getInstance().run(new BaseThreadHelper.Task() {
            @Override
            public void threadRun() {
                while (true){
                    try {
                        index[0]++;
                        Log.i("=====","======"+ index[0]);
                        if (Thread.currentThread()!=null){
                            Thread.sleep(1000);
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, 10, new BaseThreadHelper.Task.OnTimeoutListener() {
            @Override
            public void onTimeout(Thread thread) {
                thread.interrupt();
                Log.i("=====","===========超时");
            }
        });
    }

}
