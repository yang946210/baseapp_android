package com.yang.ktbase.network.`interface`

import androidx.lifecycle.LifecycleOwner

interface UiView:LifecycleOwner {

    /**
     * 一般加载中弹窗
     */
    fun showLoading()

    /**
     * 关闭加载中弹窗
     */
    fun hideLoading()

}