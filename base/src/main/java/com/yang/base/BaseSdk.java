package com.yang.base;

import android.app.Application;
import android.content.Context;
import android.content.pm.ActivityInfo;

import com.yang.base.util.BaseCrashHelper;

import java.io.File;

/***
 * @desc Base框架入口
 * @author yang
 */

public class BaseSdk {
    /**
     * 全局context
     */
    private Context context;

    /**
     * 全局debug标志
     */
    private boolean debug;

    /**
     * 内部存储根目录
     */
    private File rootPath;

    /***
     * 屏幕类型(横竖屏)
     */
    private Integer screenType;

    private BaseSdk(){}

    private static class BaseSdkHolder{
        private static final BaseSdk instance=new BaseSdk();
    }

    public static BaseSdk getInstance(){
        return BaseSdkHolder.instance;
    }

    /**
     * 初始化sdk
     * @param application  application
     * @param isDebug  是否debug
     */
    public void init(Application application, boolean isDebug) {
        context = application.getApplicationContext();
        debug = isDebug;
        rootPath= isDebug?context.getExternalFilesDir("debug"):context.getFilesDir();
        BaseCrashHelper.getInstance().init();
    }

    /**
     * 初始化sdk
     * @param application  application
     * @param isDebug  是否debug
     * @param screenType 默认横竖屏
     */
    public void init(Application application, boolean isDebug,Integer screenType) {
        this.screenType=screenType;
        init(application,isDebug);
    }

    /**
     * 获取全局context
     * @return
     */
    public Context getContext() {
        return context;
    }

    /**
     * 获取debug状态
     * @return
     */
    public boolean isDebug() {
        return debug;
    }

    /**
     * 获取统一内部存储目录
     * @return
     */
    public File getRootPath(){
        return rootPath;
    }

    /***
     * 获取屏幕类型
     * @return
     */
    public Integer getScreenType() {
        return screenType;
    }

    /***
     * 设置屏幕类型
     * @param screenType ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED
     *                   ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
     *                   ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
     */
    public void setScreenType(Integer screenType) {
        this.screenType = screenType;
    }
}
