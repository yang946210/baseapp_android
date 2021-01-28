package com.yang.base.util;

import android.view.Gravity;
import android.widget.Toast;

import com.yang.base.BaseSdk;

/***
 * @desc 基础toast
 * @author yang
 */

public class BaseToastHelper {


    /**
     * 弹出Toast
     * @param text    提示的文本
     */
    public static void showToast(String text) {
        _showToast(text,0, Gravity.BOTTOM);
    }

    /**
     * 弹出Toast
     * @param text    提示的文本
     * @param duration 持续时间（0：短；1：长）
     */
    public static void showToast(String text, int duration) {
        _showToast(text,duration, Gravity.CENTER);
    }

    /**
     *
     * 弹出Toast
     * @param text    提示的文本
     * @param duration 持续时间（0：短；1：长）
     * @param gravity  位置（Gravity.CENTER;Gravity.TOP;...）
     */
    public static void showToast(String text, int duration,int gravity) {
        _showToast(text,duration,gravity);
    }

    private static void _showToast(String message, int duration, int gravity){
        Toast mToast = Toast.makeText(BaseSdk.getInstance().getContext(), message, duration);
        mToast.setText(message);
        mToast.setDuration(duration);
        mToast.setGravity(gravity, 0, 50);
        mToast.show();
    }
}
