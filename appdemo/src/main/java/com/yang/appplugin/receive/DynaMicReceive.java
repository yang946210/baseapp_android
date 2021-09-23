package com.yang.appdemo.receive;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;


/***
 * @desc 动态广播接收者
 * @time 2021/9/22
 * @author yangguoq
 */

public class DynaMicReceive extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i("baseTag","DynaMicReceive onReceive" + intent.getDataString());
    }
}
