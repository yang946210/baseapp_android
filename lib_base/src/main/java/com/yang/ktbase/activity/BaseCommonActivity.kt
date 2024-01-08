package com.yang.ktbase.activity

import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity
import com.yang.ktbase.network.`interface`.UiView

/**
 * Activity基类，
 * ...非UI相关公共逻辑都在这里
 */
abstract class BaseCommonActivity: AppCompatActivity(), UiView {


    /**
     * 一般加载中弹窗
     */
    private val progressDialog: ProgressDialog by lazy {
        ProgressDialog(this)
    }


    /**
     * 展示一般加载中弹窗
     */
    override fun showLoading() {
        progressDialog.show()
    }

    /**
     * 关闭一般加载中弹窗
     */
    override fun hideLoading() {
       progressDialog.takeIf { it.isShowing }?.dismiss()
    }


}