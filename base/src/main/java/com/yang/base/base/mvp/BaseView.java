package com.yang.base.base.mvp;



public interface BaseView {

    /**
     * 数据获取失败
     * @param throwable
     */
    void onError(Throwable throwable);

}
