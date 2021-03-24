package com.yang.base.base.mvp;


import com.uber.autodispose.AutoDisposeConverter;

public interface BaseView {

    /**
     * 显示加载中
     */
    void onShowLoading();

    /**
     * 隐藏加载
     */
    void onHideLoading();

    /**
     * 数据获取失败
     * @param throwable
     */
    void onError(Throwable throwable);

}
