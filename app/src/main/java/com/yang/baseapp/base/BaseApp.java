package com.yang.baseapp.base;

import com.yang.base.BaseSdk;
import com.yang.base.base.BaseApplication;
import com.yang.baseapp.BuildConfig;
import com.yang.base.util.BaseCryptoHelper;

/***
 * @desc baseApplication
 * @author yang
 */


public class BaseApp extends BaseApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        BaseSdk.getInstance().init(this, BuildConfig.DEBUG).
                initLog("tag");
        BaseCryptoHelper.getInstance().init("aabbccddeeffgghh");
    }

}
