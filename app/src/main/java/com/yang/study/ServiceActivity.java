package com.yang.study;


import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.view.View;

import com.yang.base.base.BaseActivity;
import com.yang.base.util.log.Logger;
import com.yang.baseapp.R;

/***
 * @desc
 * @time 2021/9/7
 * @author yangguoq
 */

public class ServiceActivity extends BaseActivity {


    private static BaseContent baseContent;

    private Intent intent;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_activity_about;
    }

    @Override
    protected void findViews() {

    }

    @Override
    protected void init() {
        intent=new Intent(this, ServiceAbout.class);
        baseContent=new BaseContent();
    }

    public void startService(View view){
        startService(intent);
    }

    public void bindService(View view){
        bindService(intent,baseContent, Context.BIND_AUTO_CREATE);
    }

    public void stopService(View view){
        stopService(intent);
    }

    public void onUnBindSerVice(View view){
        unbindService(baseContent);
    }

    public void nextActivity(View view){
        startActivity(new Intent(this, ServiceNextActivity.class));
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Logger.simple("activityAbout onNewIntent");
    }

    public static class BaseContent implements ServiceConnection{

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Logger.simple("about onServiceConnected,name"+name.getClassName()+",iBinder");
        }
        @Override
        public void onServiceDisconnected(ComponentName name) {
            Logger.simple("about onServiceDisconnected,name"+name.getClassName());
        }
    }
}
