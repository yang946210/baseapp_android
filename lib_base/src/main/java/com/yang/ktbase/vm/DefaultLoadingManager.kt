package com.yang.ktbase.vm

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.content.ContextWrapper
import android.view.LayoutInflater
import android.widget.TextView
import com.yang.base.R
import java.lang.ref.WeakReference


/**
 * 全局默认loading弹窗
 */
object DefaultLoadingManager {
    private var dialog: Dialog? = null
    private var hostActivityRef: WeakReference<Activity>? = null

    fun show(context: Context, msg: String = "加载中...") {
        val activity = context.findActivity() ?: return
        if (activity.isFinishing || activity.isDestroyed) return

        if (dialog?.isShowing == true) {
            val host = hostActivityRef?.get()
            if (host === activity) return
            hide()
        }

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
        runCatching {
            dialog?.takeIf { it.isShowing }?.dismiss()
            dialog = null
            hostActivityRef = null
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
