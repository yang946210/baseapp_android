package com.yang.appplugin;


import android.content.IntentFilter;

import com.yang.appplugin.receive.DynaMicReceive;
import com.yang.base.base.BaseActivity;

public class PluginMainActivity extends BaseActivity {


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
}