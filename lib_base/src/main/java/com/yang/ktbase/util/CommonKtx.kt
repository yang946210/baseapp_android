package com.yang.ktbase.util

import android.app.Activity
import android.graphics.Color
import android.os.Build
import android.view.View
import java.lang.reflect.ParameterizedType

/**
 * 获取当前类绑定的泛型ViewModel-clazz
 */
@Suppress("UNCHECKED_CAST")
fun <M> getVmClazz(obj: Any): M {
    return (obj.javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[0] as M
}


/**
 * 判断是否为空 并传入相关操作(用taskIf了)
 */
inline fun <reified T> T?.notNull(notNullAction: (T) -> Unit, nullAction: () -> Unit = {}) {
    if (this != null) {
        notNullAction.invoke(this)
    } else {
        nullAction.invoke()
    }
}


/**
 * 设置状态栏白底黑字
 * @param activity
 */
fun colorStatusBar(activity: Activity) {
    // 在创建时设置状态栏背景色为白色，并且文字和图标为黑色
    // 设置状态栏背景颜色为白色
    activity.window.statusBarColor = Color.WHITE
    // 设置状态栏文字和图标为黑色
    activity.window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
}





