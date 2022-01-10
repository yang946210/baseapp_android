package com.yang.appdemo;


import android.content.ComponentName;
import android.content.Intent;
import android.content.IntentFilter;
import android.view.View;

import com.yang.appdemo.receive.DynaMicReceive;
import com.yang.base.base.BaseActivity;

public class PluginMainActivity extends BaseActivity implements View.OnClickListener {


    /**
     * 显式显示注册
     */
    private DynaMicReceive dynaMicReceive;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_plugin_main;
    }

    @Override
    protected void findViews() {
        findViewById(R.id.tv_into).setOnClickListener(this);
    }

    @Override
    protected void init() {
        dynaMicReceive = new DynaMicReceive();
        IntentFilter filter=new IntentFilter();
        filter.addAction("com.yang.dynaMic");
        registerReceiver(dynaMicReceive,filter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(dynaMicReceive);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_into:
                intoApp();
                break;
        }
    }

    private void intoApp(){
        Intent intent = new Intent();
        //包名 包名+类名（全路径）
        ComponentName comp = new ComponentName("com.yang.baseapp","com.yang.baseapp.FirstActivity2");
        intent.setComponent(comp);
        intent.setAction(Intent.ACTION_MAIN);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }

}