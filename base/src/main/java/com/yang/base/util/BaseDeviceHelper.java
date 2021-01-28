package com.yang.base.util;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.os.Build;
import android.telephony.TelephonyManager;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Method;
import java.util.ArrayList;


/***
 * @desc 设备相关帮助类
 * @author yang
 */
public class BaseDeviceHelper {

    /**
     * 获取手机型号
     * @return 手机型号
     */
    public static String getSystemModel() {
        return Build.MODEL;
    }

    /**
     * 获取手机厂商
     * @return 手机厂商
     */
    public static String getDeviceBrand() {
        return Build.BRAND;
    }

    /***
     * 判断APP能否被调试
     * @param context  上下文
     * @return
     */
    public static boolean isAPPCanDebug(Context context) {
        try {
            return android.os.Debug.isDebuggerConnected() || (context.getApplicationInfo().flags & ApplicationInfo.FLAG_DEBUGGABLE) != 0;
        } catch (Throwable e) {
            e.printStackTrace();
            return false;
        }
    }

    /***
     * 检查设备是否root
     * @return
     */
    public static boolean hasRoot() {
        File f = null;
        final String kSuSearchPaths[] = {"/system/bin/", "/system/xbin/", "/system/sbin/", "/sbin/", "/vendor/bin/"};
        try {
            for (int i = 0; i < kSuSearchPaths.length; i++) {
                f = new File(kSuSearchPaths[i] + "su");
                if (f != null && f.exists()) {
                    return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            String[] strCmd = new String[]{"/system/xbin/which", "su"};
            ArrayList<String> execResult = executeCommand(strCmd);
            if (execResult != null) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /***
     * 执行shell命令
     * @param shellCmd 执行参数
     * @return
     */
    public static ArrayList<String> executeCommand(String[] shellCmd) {
        String line = null;
        ArrayList<String> fullResponse = new ArrayList<String>();
        Process localProcess = null;
        try {
            localProcess = Runtime.getRuntime().exec(shellCmd);
        } catch (Exception e) {
            return null;
        }
        BufferedReader in = null;
        try {
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(localProcess.getOutputStream()));
            in = new BufferedReader(new InputStreamReader(localProcess.getInputStream()));
            while ((line = in.readLine()) != null) {
                fullResponse.add(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            BaseCommHelper.closeStream(in);
        }
        return fullResponse;
    }

    /**
     * 返回屏幕分辨率的宽度  px
     *
     * @param context 上下文
     * @return
     */
    public static int getDisplayWidth(Context context) {
        return context.getResources().getDisplayMetrics().widthPixels;
    }

    /**
     * 返回屏幕分辨率的高度  px
     *
     * @param context 上下文
     * @return
     */
    public static int getDisplayHeight(Context context) {
        return context.getResources().getDisplayMetrics().heightPixels;
    }

    /***
     * 获取状态栏高度
     * @param context 上下文
     * @return
     */
    public static int getStatubarHeight(Context context) {
        int statusBarHeight = -1;
        //获取status_bar_height资源的ID
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            //根据资源ID获取响应的尺寸值
            statusBarHeight = context.getResources().getDimensionPixelSize(resourceId);
        }
        return statusBarHeight;
    }


    /***
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     * @param context 上下文
     * @param dpValue dp值
     * @return
     */
    public static int dip2px(Context context, float dpValue) {
        float displaydensity = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * displaydensity + 0.5f);
    }


    /***
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     * @param context 上下文
     * @param pxValue 像素值
     * @return
     */
    public static int px2dip(Context context, float pxValue) {
        float displaydensity = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / displaydensity + 0.5f);
    }

    /***
     * 将SP转换为px
     * @param context 上下文
     * @param spValue sp值
     * @return
     */
    public static int sp2px(Context context, float spValue) {
        float var2 = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * var2 + 0.5F);
    }

    /***
     * px转换成sp
     * @param context 上下文
     * @param pxValue px值
     * @return
     */
    public static int px2sp(Context context, float pxValue) {
        float var2 = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (pxValue / var2 + 0.5F);
    }


    /***
     * 获取IMEI
     * @param context 上下文
     * @param slotId slotId slotId为卡槽Id，它的值为 0、1；
     * @return
     */
    public static String getIMEI(Context context, int slotId) {
        try {
            TelephonyManager manager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
            Method method = manager.getClass().getMethod("getImei", int.class);
            String imei = (String) method.invoke(manager, slotId);
            return imei;
        } catch (Throwable e) {
            return "";
        }
    }

}
