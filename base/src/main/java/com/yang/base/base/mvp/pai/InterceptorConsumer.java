package com.yang.base.base.mvp.pai;

import android.text.TextUtils;


import org.json.JSONObject;

import java.nio.charset.Charset;

import io.reactivex.functions.Consumer;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;


//一个简单错误拦截器
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
