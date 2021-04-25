package com.yang.baseapp.base;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;

import androidx.annotation.NonNull;

import com.yang.base.BaseSdk;
import com.yang.base.base.BaseApplication;
import com.yang.baseapp.BuildConfig;
import com.yang.base.util.BaseCryptoHelper;

import leakcanary.LeakCanary;
import shark.Leak;

/***
 * @desc baseApplication
 * @author yang
 */


public class BaseApp extends BaseApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        BaseSdk.getInstance().init(this, BuildConfig.DEBUG).
                initLog("baseApp",BuildConfig.DEBUG).
                intScreenType(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        BaseCryptoHelper.getInstance().init("aabbccddeeffgghh");
    }

}
