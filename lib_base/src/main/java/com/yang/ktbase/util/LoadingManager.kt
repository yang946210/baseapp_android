package com.yang.ktbase.util

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.content.ContextWrapper
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.widget.TextView
import com.yang.base.R
import java.lang.ref.WeakReference


/**
 * 全局默认loading弹窗
 */
object LoadingManager {

    private var dialog: Dialog? = null
    private var hostActivityRef: WeakReference<Activity>? = null

    /**
     * 延迟展示，请求过快时更平滑
     */
    private val handler = Handler(Looper.getMainLooper())
    private var showRunnable: Runnable? = null

    /**
     * @param delayMillis 延迟显示的时间，默认 200ms
     */
    fun show(context: Context, msg: String = "加载中...", delayMillis: Long = 200L) {
        val activity = context.findActivity() ?: return
        if (activity.isFinishing || activity.isDestroyed) return

        // 1. 如果当前已经有正在排队的显示任务，先移除（防止快速连续调用导致逻辑混乱）
        cancelPendingShow()

        // 2. 如果当前弹窗已经在显示
        if (dialog?.isShowing == true) {
            val host = hostActivityRef?.get()
            if (host === activity) {
                // 如果是同一个 Activity，直接更新文字即可，无需延迟
                dialog?.findViewById<TextView>(R.id.tv_message)?.text = msg
                return
            }
            // 如果是不同 Activity，立即隐藏旧的
            hide()
        }

        // 3. 创建延迟显示任务
        showRunnable = Runnable {
            executeShow(activity, msg)
        }.also {
            handler.postDelayed(it, delayMillis)
        }
    }

    private fun executeShow(activity: Activity, msg: String) {
        if (activity.isFinishing || activity.isDestroyed) return

        val view = LayoutInflater.from(activity).inflate(R.layout.dialog_loading, null)
        view.findViewById<TextView>(R.id.tv_message).text = msg

        dialog = Dialog(activity).apply {
            window?.setBackgroundDrawableResource(android.R.color.transparent)
            setContentView(view)
            setCancelable(false)
            show()
        }
        hostActivityRef = WeakReference(activity)
    }

    fun hide() {
        // 关键：隐藏时必须先取消掉还在排队的延迟任务
        cancelPendingShow()

        runCatching {
            dialog?.takeIf { it.isShowing }?.dismiss()
            dialog = null
            hostActivityRef = null
        }
    }

    private fun cancelPendingShow() {
        showRunnable?.let {
            handler.removeCallbacks(it)
            showRunnable = null
        }
    }

    private fun Context.findActivity(): Activity? {
        var ctx: Context? = this
        while (ctx is ContextWrapper) {
            if (ctx is Activity) return ctx
            ctx = ctx.baseContext
        }
        return null
    }
}