package com.v2ray.ang.receiver

import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.content.Intent
import android.util.Log
import com.v2ray.ang.AppConfig
import com.v2ray.ang.service.V2RayServiceManager

class WidgetProvider : AppWidgetProvider() {
    /**
     * 每次窗口小部件被更新都调用一次该方法
     */
    override fun onUpdate(context: Context, appWidgetManager: AppWidgetManager, appWidgetIds: IntArray) {
        super.onUpdate(context, appWidgetManager, appWidgetIds)
    }



    /**
     * 接收窗口小部件发送的广播
     */
    override fun onReceive(context: Context, intent: Intent) {
        super.onReceive(context, intent)
        Log.d(AppConfig.ANG_PACKAGE,"WidgetProvider 接收窗口小部件发送的广播")
        if (AppConfig.BROADCAST_ACTION_WIDGET_CLICK == intent.action) {
            if (V2RayServiceManager.v2rayPoint.isRunning) {
                //Utils.stopVService(context)
            } else {
                //Utils.startVServiceFromToggle(context)
            }
        } else if (AppConfig.BROADCAST_ACTION_ACTIVITY == intent.action) {

        }
    }
}
