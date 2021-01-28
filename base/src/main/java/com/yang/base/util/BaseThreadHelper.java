package com.yang.base.util;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import androidx.annotation.CallSuper;
import androidx.annotation.NonNull;

import com.yang.base.BaseSdk;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/***
 * @desc 一个常用线程池，替代new thread。
 * @author yangguoq
 */

public class BaseThreadHelper implements RejectedExecutionHandler {


    private static class threadDemoHelperHolder{
        private static final BaseThreadHelper instance=new BaseThreadHelper();
    }

    public static BaseThreadHelper getInstance(){
        return threadDemoHelperHolder.instance;
    }

    private BaseThreadHelper(){
        int CPU_COUNT = Runtime.getRuntime().availableProcessors();
        CPU_COUNT= Math.max(CPU_COUNT, 2);
        poolTExecutor= (ThreadPoolExecutor) Executors.newFixedThreadPool(CPU_COUNT*2+1,new DefaultThreadFactory());
    }

    /**
     * 线程池对象
     */
    private ThreadPoolExecutor poolTExecutor;

    /**
     * 是否在主线程
     * @return
     */
    public boolean isMainThread() {
        return Looper.myLooper() == Looper.getMainLooper();
    }

    /**
     * 获取handler
     * @return
     */
    public Handler getMainHandler() {
        return BaseHandlerHelper.getInstance().getHandler();
    }

    /**
     * 切换至主线程
     * @param runnable
     */
    public void runOnUiThread(Runnable runnable) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            runnable.run();
        } else {
            BaseHandlerHelper.getInstance().getHandler().post(runnable);
        }
    }

    /**
     * 延时切换至主线程
     * @param runnable
     * @param delayMillis
     */
    public void runOnUiThreadDelayed(final Runnable runnable, long delayMillis) {
        BaseHandlerHelper.getInstance().getHandler().postDelayed(runnable, delayMillis);
    }


    /**
     * 运行
     */
    public void run(Runnable task){
        poolTExecutor.execute(task);
    }

    /**
     * 运行<重写runnable,避免内存泄漏>
     */
    public void run(Task task){
        poolTExecutor.execute(task);
    }

    /**
     * 运行，包含超时监听
     * @param task task
     * @param outTime  设定的超时时间(单位/秒)
     * @param outListener    超时监听
     */
    public void run(Task task, int outTime, Task.OnTimeoutListener outListener){
        task.setOutTime(outTime);
        task.setListener(outListener);
        poolTExecutor.execute(task);
    }

    /***
     * 获取线程池对象
     * @return
     */
    public ThreadPoolExecutor getExecutor() {
        return poolTExecutor;
    }


    /***
     * 停止所有线程
     */
    public void stopAll() {
        poolTExecutor.shutdown();
    }


    @Override
    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
        // TODO: 2020/12/22  线程拒绝策略，队列满，抛异常。还没想好怎么处理
    }

    /**
     * 线程factory
     */
    static class DefaultThreadFactory implements ThreadFactory {
        private final AtomicInteger threadNumber = new AtomicInteger(1);
        private final String namePrefix;


        DefaultThreadFactory() {
            namePrefix = "otherThread-"+BaseCommHelper.getAppName(BaseSdk.getInstance().getContext());
        }

        @Override
        public Thread newThread(Runnable r) {
            Thread t = new Thread(r, namePrefix + threadNumber.getAndIncrement());
            if (t.isDaemon())
                t.setDaemon(false);
            if (t.getPriority() != Thread.NORM_PRIORITY)
                t.setPriority(Thread.NORM_PRIORITY);
            return t;
        }
    }


    /**
     * 自定义runable，处理泄漏并做一些拓展操作
     */
    public abstract static class Task implements Runnable {

        private volatile Thread runner;

        private Timer mTimer;

        private long outTime;

        private OnTimeoutListener listener;

        public abstract void threadRun();

        public void setOutTime(long outTime) {
            this.outTime = outTime;
        }

        public void setListener(OnTimeoutListener listener) {
            this.listener = listener;
        }

        @Override
        public void run() {
            if (listener != null) {
                runner = Thread.currentThread();
                mTimer = new Timer();
                mTimer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        if (outTime>0) {
                            outTime=outTime-1000;
                        }else {
                            listener.onTimeout(runner);
                            onDone();
                        }
                    }}, 0,1000);
            }
            threadRun();
        }

        public interface OnTimeoutListener {
            void onTimeout(Thread thread);
        }

        @CallSuper
        protected void onDone() {
            if (mTimer != null) {
                mTimer.cancel();
                mTimer = null;
                listener = null;
            }
        }
    }
}
