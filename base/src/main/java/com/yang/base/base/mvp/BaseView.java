package com.yang.base.base.mvp;



public interface BaseView {

    /**
     * 开始获取数据
     */
    void onStartLoad();

    /**
     * 获取数据成功
     */
    void onSuccess(String success);

    /**
     * 数据获取失败
     * @param throwable
     */
    void onError(Throwable throwable);

}
