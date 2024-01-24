package com.yang.ktbase.util

import android.app.Activity
import android.content.Context
import android.widget.Toast
import com.blankj.utilcode.util.ToastUtils


/**
 * 获取屏幕宽度
 */
val Context.screenWidth
    get() = resources.displayMetrics.widthPixels

/**
 * 获取屏幕高度
 */
val Context.screenHeight
    get() = resources.displayMetrics.heightPixels

/**
 * dp值转换为px
 */
fun Context.dp2px(dp: Int): Int {
    val scale = resources.displayMetrics.density
    return (dp * scale + 0.5f).toInt()
}

/**
 * px值转换成dp
 */
fun Context.px2dp(px: Int): Int {
    val scale = resources.displayMetrics.density
    return (px / scale + 0.5f).toInt()
}

/**
 * 简化一下toast
 */
fun Context.toastUtil(msg: String,duration:Int=Toast.LENGTH_LONG){
    if (this is Activity){
        Toast.makeText(this,msg,duration).show()
    }
}
