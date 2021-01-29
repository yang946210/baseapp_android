package com.yang.base.base;

import android.content.pm.ActivityInfo;

import com.yang.base.BaseSdk;
import com.yang.base.BuildConfig;
import com.yang.base.util.BaseCryptoHelper;
import com.yang.base.util.BaseHandlerHelper;

/***
 * @desc baseApplication
 * @author yang
 */


public class BaseApp extends BaseApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        BaseSdk.getInstance().init(this, BuildConfig.DEBUG);
        BaseCryptoHelper.getInstance().init("aabbccddeeffgghh");
    }

}
