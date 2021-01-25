package com.yang.show.text;



import android.content.Context;
import android.util.Log;


/***
 * @desc 单例内存泄漏测试
 * @time 2020/11/27
 * @author yangguoq
 */
public class SingleInstance {
    private Context mContext;
    private static SingleInstance instance;

    private SingleInstance() {
    }

    public static SingleInstance getInstance( ) {
        if (instance == null) {
            instance = new SingleInstance();
        }
        return instance;
    }

    public void say(Context context) {
        this.mContext=context;
        Log.i("tag", "this is single instance");
        Log.i("tag", "：code：" + instance.hashCode());
    }
}