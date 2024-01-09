package com.yang.ktbase.network.netutil

import androidx.lifecycle.LifecycleOwner

/**
 * 请求侧实现，那个模块(activity?fragment?viewModel?)需要发送端（网络请求）功能，就实现这个接口
 */
public interface Request:LifecycleOwner {

    /**
     * 一般加载中弹窗
     */
    fun showLoading()

    /**
     * 关闭加载中弹窗
     */
    fun hideLoading()

}