package com.yang.mvp.api;


import io.reactivex.functions.Consumer;


/**
 * 一个简单错误拦截器
 * @param <T>
 */
public abstract class InterceptorConsumer<T> implements Consumer<T> {
    @Override
    public void accept(T t) throws Exception {
        if (true){
            onSuccess(t);
        }else {
            onError("错了");
        }
    }

    public abstract void onSuccess(T t) throws Exception;


    public void onError(String codeMassage){};


}
