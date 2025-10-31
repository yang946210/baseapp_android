package com.yang.ktbase.vm

import android.app.Dialog
import android.content.Context
import android.view.LayoutInflater
import android.widget.TextView
import com.yang.base.R


/**
 * 全局默认loading弹窗
 */
object DefaultLoadingManager {
    private var dialog: Dialog? = null

    fun show(context: Context, msg: String = "加载中...") {
        if (dialog?.isShowing == true) return

        val view = LayoutInflater.from(context).inflate(R.layout.dialog_loading, null)
        view.findViewById<TextView>(R.id.tv_message).text = msg

        dialog = Dialog(context).apply {
            window?.setBackgroundDrawableResource(android.R.color.transparent)
            setContentView(view)
            setCancelable(false)
            show()
        }
    }

    fun hide() {
        runCatching {
            dialog?.takeIf { it.isShowing }?.dismiss()
            dialog = null
        }

    }
}