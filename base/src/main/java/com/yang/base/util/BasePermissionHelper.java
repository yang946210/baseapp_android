package com.yang.base.util;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;
import java.util.List;

/***
 * 权限相关工具类
 */
public class BasePermissionHelper {

    /***
     * 是否有某个权限
     * @param context 上线文容器
     * @param permission 权限名称
     * @return
     */
    public static boolean hasPermission(Activity context, String permission) {
        return ContextCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_GRANTED;
    }

    /**
     * 检测是否拥有全部权限
     * @param context 上下文
     * @param permissions  权限合集
     * @return 合集中没有获得权限的权限合集
     */
    public static List<String> checkPermissions(Activity context, String[] permissions) {
        List<String> list = new ArrayList<>();
        for (String p : permissions) {
            if (ContextCompat.checkSelfPermission(context, p) != PackageManager.PERMISSION_GRANTED) {
                list.add(p);
            }
        }
        return list;
    }

    /***
     * 申请权限
     * @param context 上下文
     * @param permissions 权限集合
     */
    public static void requestPermissions(Activity context, String[] permissions,int requestCode) {
        if (permissions == null || permissions.length == 0) {
            return;
        }
        ActivityCompat.requestPermissions(context, permissions, requestCode);
    }

    /***
     * 申请权限
     * @param context 上下文
     * @param permission 权限
     * @param requestCode 请求code
     */
    public static void requestPermission(Activity context, String permission, int requestCode) {
        if (ContextCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(context, new String[]{permission}, requestCode);
        }
    }



    /***
     * 判断权限是否被禁止
     * @param context 上下文
     * @param permission 权限
     * @return
     */
    public static boolean shouldShowRequestPermissionRationale(Activity context, String[] permission) {
        for (String per : permission) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(context, per)) {
                return true;
            }
        }
        return false;
    }

    /***
     * 进入不同品牌手机权限设置页面
     * @param context 上下文
     * @param packageName 包名
     */
    public static void checkPermissionAccept(Context context, String packageName) {
        switch (Build.MANUFACTURER.toUpperCase()) {
            case "HUAWEI":
                try {
                    Intent intent = new Intent();
                    intent.putExtra("packageName", packageName);
                    ComponentName comp = new ComponentName("com.huawei.systemmanager", "com.huawei.permissionmanager.ui.MainActivity");
                    intent.setComponent(comp);
                    if (context.getPackageManager().resolveActivity(intent, PackageManager.MATCH_ALL) == null) { // 判断是否存在这个activity
                        goSettingView(context);
                        return;
                    }
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                } catch (Exception e) {
                    goSettingView(context);
                }

                break;
            case "MEIZU":
                try {
                    Intent intent2 = new Intent("com.meizu.safe.security.SHOW_APPSEC");
                    intent2.addCategory(Intent.CATEGORY_DEFAULT);
                    intent2.putExtra("packageName", packageName);
                    if (context.getPackageManager().resolveActivity(intent2, PackageManager.MATCH_ALL) == null) { // 判断是否存在这个activity
                        goSettingView(context);
                        return;
                    }
                    intent2.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent2);
                } catch (Exception e) {
                    goSettingView(context);
                }
                break;
            case "XIAOMI":
                try {
                    Intent intent3 = new Intent("miui.intent.action.APP_PERM_EDITOR");
                    ComponentName componentName = new ComponentName("com.miui.securitycenter", "com.miui.permcenter.permissions.AppPermissionsEditorActivity");
                    intent3.setComponent(componentName);
                    intent3.putExtra("extra_pkgname", packageName);
                    if (context.getPackageManager().resolveActivity(intent3, PackageManager.MATCH_ALL) == null) { // 判断是否存在这个activity
                        goSettingView(context);
                        return;
                    }
                    intent3.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent3);
                } catch (Exception e) {
                    goSettingView(context);
                }
                break;
            default:
                goSettingView(context);
        }
    }


    /***
     * 进入系统自带设置
     * @param context 上下文
     */
    public static void goSettingView(Context context) {
        try {
            Intent localIntent = new Intent();
            localIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            if (Build.VERSION.SDK_INT >= 9) {
                localIntent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
                localIntent.setData(Uri.fromParts("package", context.getPackageName(), null));
            } else if (Build.VERSION.SDK_INT <= 8) {
                localIntent.setAction(Intent.ACTION_VIEW);
                localIntent.setClassName("com.android.settings", "com.android.settings.InstalledAppDetails");
                localIntent.putExtra("com.android.settings.ApplicationPkgName", context.getPackageName());
            }
            context.startActivity(localIntent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
