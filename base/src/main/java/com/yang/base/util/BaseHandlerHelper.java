package com.yang.base.util;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/***
 * @desc 线程切换工具类，线程间消息通信
 * @time 2020-07-01
 * @author yang
 */
public class BaseHandlerHelper{

    /***
     * 主线程
     */
    private final Handler mThreadHandler;

    /***
     * 锁
     */
    private final Lock lock = new ReentrantLock();

    /***
     * 监听器
     */
    private final List<HandlerListener> mListeners;

    /***
     * 构造函数
     */
    private BaseHandlerHelper() {
        Looper.prepare();
        mThreadHandler = new Handler(Looper.getMainLooper()){
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);
                try {
                    lock.lock();
                    for (int i = mListeners.size() - 1; i >= 0; i--) {
                        mListeners.get(i).handleMessage(msg, msg.getTarget() == mThreadHandler);
                    }
                } finally {
                    lock.unlock();
                }
            }
        };
        mListeners = new ArrayList<HandlerListener>(10);
    }

    /***
     * 结束
     */
    public void stop() {
        mThreadHandler.getLooper().quit();
        mListeners.clear();
    }

    /**
     * 单例
     ****/
    private final static BaseHandlerHelper mInstance = new BaseHandlerHelper();

    /***
     * 获取实例
     * @return
     */
    public static BaseHandlerHelper getInstance() {
        return mInstance;
    }

    /***
     * 获取主线程handler
     * @return
     */
    public Handler getHandler() {
        return mThreadHandler;
    }

    /***
     * 获取Message
     * @return
     */
    public Message obtainHandlerMessage() {
        return mThreadHandler.obtainMessage();
    }

    /***
     * 发送消息
     * @param delay 延时 毫秒
     * @param what 事件ID
     */
    public void sendMessage(int delay, int what) {
        mThreadHandler.sendMessageDelayed(mThreadHandler.obtainMessage(what), delay);
    }

    /***
     * 发送消息
     * @param delay 延时 毫秒
     * @param what 事件ID
     * @param obj 消息传递参数
     */
    public void sendMessage(int delay, int what, Object obj) {
        mThreadHandler.sendMessageDelayed(mThreadHandler.obtainMessage(what, obj), delay);
    }

    /***
     * 移除事件ID
     * @param what 事件ID
     */
    public void removeMessageWhat(int what) {
        mThreadHandler.removeMessages(what);
    }

    /***
     * 移除消息执行体
     * @param run
     */
    public void removeMessageRunnable(Runnable run) {
        mThreadHandler.removeCallbacks(run);
    }

    /***
     * 添加事件处理监听
     * @param listener 监听器
     */
    public void addHandleListener(HandlerListener listener) {
        try {
            lock.lock();
            if (listener == null || mListeners.contains(listener)) {
                return;
            }
            mListeners.add(listener);
        } finally {
            lock.unlock();
        }
    }


    /***
     * 移除事件监听器
     * @param listener 回调
     */
    public void removeHandleListener(HandlerListener listener) {
        try {
            lock.lock();
            if (listener == null || !mListeners.contains(listener)) {
                return;
            }
            mListeners.remove(listener);
        } finally {
            lock.unlock();
        }

    }



    public interface HandlerListener {
        /***
         * 事件回调
         * @param msg 消息内容
         * @param mainThread 是否是主线程
         */
        void handleMessage(Message msg, boolean mainThread);
    }
}
