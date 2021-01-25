package com.yang.base.base;

import android.content.pm.ActivityInfo;

import com.yang.base.BaseSdk;
import com.yang.base.BuildConfig;

/***
 * @desc baseApplication
 * @time 2020/10/30
 * @author yang
 */


public class BaseApp extends BaseApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        BaseSdk.getInstance().init(this, BuildConfig.DEBUG, ActivityInfo.SCREEN_ORIENTATION_USER);
    }

}
