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

public class ServiceNextActivity extends BaseActivity implements View.OnClickListener {


    @Override
    protected int getLayoutId() {
        return R.layout.activity_next_about;
    }

    @Override
    protected void findViews() {
        findViewById(R.id.tv_nextActivity).setOnClickListener(this);
        findViewById(R.id.tv_nextBind).setOnClickListener(this);
    }

    @Override
    protected void init() {

    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Logger.simple("activityNextAbout onNewIntent");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_nextActivity:
                startActivity(new Intent(this, ServiceActivity.class));
                break;
            case R.id.tv_nextBind:
                bindService(new Intent(this,ServiceAbout.class),new BaseNextContent(), Context.BIND_AUTO_CREATE);
                break;

        }
    }

    public static class BaseNextContent implements ServiceConnection {

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Logger.simple("aboutNext onServiceConnected,name"+name+",iBinder");
        }
        @Override
        public void onServiceDisconnected(ComponentName name) {
            Logger.simple("aboutNext onServiceDisconnected,name"+name);
        }
    }
}