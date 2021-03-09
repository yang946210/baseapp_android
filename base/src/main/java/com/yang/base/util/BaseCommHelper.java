package com.yang.base.util;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebSettings;


import com.yang.base.BaseSdk;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.File;
import java.io.FileReader;
import java.util.List;
import java.util.Random;

/***
 * @desc 公共工具类
 * @author yang
 */
public class BaseCommHelper {

    /***
     * 获取设备userAgent
     * @return
     */
    public static String getUserAgent() {
        String userAgent = "";
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            try {
                userAgent = WebSettings.getDefaultUserAgent(BaseSdk.getInstance().getContext());
            } catch (Throwable e) {
                userAgent = System.getProperty("http.agent");
            }
        } else {
            userAgent = System.getProperty("http.agent");
        }
        if (TextUtils.isEmpty(userAgent)) {
            return "Mozilla/5.0 (Linux; Base; Android 8.0.0; LON-AL00-PD Build/HUAWEILON-AL00; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/73.0.3683.90 Mobile Safari/537.36";
        }
        StringBuffer sb = new StringBuffer();
        for (int i = 0, length = userAgent.length(); i < length; i++) {
            char c = userAgent.charAt(i);
            if (c <= '\u001f' || c >= '\u007f') {
                sb.append(String.format("\\u%04x", (int) c));
            } else {
                sb.append(c);
            }
        }
        return sb.toString().replaceFirst("Linux;", "Linux; Base;");
    }

    /***
     * 获取当前进程名称
     * @param pid 进程号,如果是0，获取当前进程名称
     * @return
     */
    public static String getProcessName(int pid) {
        BufferedReader mBufferedReader = null;
        try {
            File file = new File("/proc/" + pid + "/" + "cmdline");
            if (pid <= 0) {
                file = new File("/proc/self/cmdline");
            }

            mBufferedReader = new BufferedReader(new FileReader(file));
            return mBufferedReader.readLine().trim();
        } catch (Throwable e) {
            e.printStackTrace();
            return null;
        } finally {
            closeStream(mBufferedReader);
        }
    }

    /***
     * 关闭流
     * @param stream
     */
    public static void closeStream(Closeable stream) {
        if (stream != null) {
            try {
                stream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /***
     * 检测应用是否安装
     * @param context
     * @param bundleName
     * @return
     */
    public static boolean isInstallAPK(Context context, String bundleName) {
        List<PackageInfo> list = context.getPackageManager().getInstalledPackages(PackageManager.GET_CONFIGURATIONS);
        for (PackageInfo item : list) {
            if (bundleName.equals(item.packageName)) {
                return true;
            }
        }
        return false;
    }

    /***
     * 获取APP版本名称
     * @param context　上下文
     * @return
     */
    public static String getAppVersion(Context context) {
        try {
            PackageManager packageManager = context.getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(context.getPackageName(), PackageManager.GET_CONFIGURATIONS);
            return packageInfo.versionName;
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
    }

    /***
     * 获取包名
     * @param context　上下文
     * @return
     */
    public static String getPackageName(Context context) {
        try {
            PackageManager packageManager = context.getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(context.getPackageName(), PackageManager.GET_CONFIGURATIONS);
            return packageInfo.packageName;
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
    }

    /***
     * 获取APP应用版本号
     * @param context　上下文
     * @return
     */
    public static int getAppVersionCode(Context context) {
        try {
            PackageManager packageManager = context.getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(context.getPackageName(), PackageManager.GET_CONFIGURATIONS);
            return packageInfo.versionCode;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    /***
     * 获取APP名称
     * @param context　上下文
     * @return
     */
    public static String getAppName(Context context) {
        try {
            PackageManager packageManager = context.getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(
                    context.getPackageName(), PackageManager.GET_CONFIGURATIONS);
            int labelRes = packageInfo.applicationInfo.labelRes;
            return context.getResources().getString(labelRes);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /***
     * 设置状态栏颜色
     * @param context　上下文
     * @param color　颜色值
     */
    public static void setWindowStatusBarColor(Activity context, int color) {
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                Window window = context.getWindow();
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                window.setStatusBarColor(color);
                if (color == context.getResources().getColor(android.R.color.white)) {
                    window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                    window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                    window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /***
     * 获取随机字符串
     * @param length 返回字符长度
     * @return
     */
    public static String getRandomString(int length) {
        String data = "1234567890QWERTYUIOPASDFGHJKLZXCVBNMqwertyuiopasdfghjklzxcvbnm";
        Random random = new Random();
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            stringBuilder.append(data.charAt(random.nextInt(data.length())));
        }
        return stringBuilder.toString();
    }





}
