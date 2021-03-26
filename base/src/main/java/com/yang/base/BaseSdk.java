package com.yang.base;

import android.app.Application;
import android.content.Context;
import android.content.pm.ActivityInfo;

import com.yang.base.util.BaseCrashHelper;
import com.yang.base.util.logger.AndroidLogAdapter;
import com.yang.base.util.logger.Logger;

import java.io.File;

/***
 * @desc Base框架入口
 * @author yang
 */

public class BaseSdk {

    /**
     * 全局appContext
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
    private int screenType= ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED;

    /**
     * webView是否生产可调式<debug模式无用>
     */
    private boolean webViewDebug=false;

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
    public BaseSdk init(Application application, boolean isDebug) {
        context = application.getApplicationContext();
        debug = isDebug;
        rootPath= isDebug?context.getExternalFilesDir("debug"):context.getFilesDir();
        BaseCrashHelper.getInstance().init();
        return this;
    }

    /**
     * 初始化log相关
     * @return
     */
    public BaseSdk initLog(){
        Logger.addLogAdapter(new AndroidLogAdapter());
        return this;
    }

    /***
     * 初始化屏幕类型
     * @param screenType ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED
     *                   ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
     *                   ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
     */
    public BaseSdk intScreenType(Integer screenType) {
        this.screenType = screenType;
        return this;
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
     * 设置BaseWebView是否生产可调式
     */
    public BaseSdk setCanDebugWebView(boolean webViewDebug) {
        this.webViewDebug = webViewDebug;
        return this;
    }

    /***
     * 设置BaseWebView是否生产可调式
     */
    public boolean getCanDebugWebView() {
        return  webViewDebug;
    }
}
