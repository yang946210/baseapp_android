package com.yang.study;

import android.content.ComponentName;
import android.content.Intent;
import android.view.View;

import com.yang.base.base.BaseActivity;
import com.yang.baseapp.R;

public class BroadCastActivity extends BaseActivity implements View.OnClickListener {


    @Override
    protected int getLayoutId() {
        return R.layout.activity_broad_cast;
    }

    @Override
    protected void findViews() {
        findViewById(R.id.tv_sendStatic).setOnClickListener(this);
        findViewById(R.id.tv_sendDynaMic).setOnClickListener(this);
    }

    @Override
    protected void init() {
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_sendStatic:
                Intent intent1=new Intent();
                intent1.setComponent(new ComponentName("com.yang.appdemo","com.yang.appdemo.receive.StaticReceive"));
                sendBroadcast(intent1);
                break;
            case R.id.tv_sendDynaMic:
                Intent intent2=new Intent("com.yang.dynaMic");
                sendBroadcast(intent2);
                break;
        }
    }

}