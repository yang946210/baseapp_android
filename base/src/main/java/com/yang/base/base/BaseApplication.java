package com.yang.base.base;


import androidx.multidex.MultiDexApplication;


/***
 * @desc application基类
 * @author yang
 */

public abstract class BaseApplication extends MultiDexApplication{

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
        //level =TRIM_MEMORY_RUNNING_MODERATE  5  表示应用程序正常运行，并且不会被杀掉。但是目前手机的内存已经有点低了，你的正在运行的进程需要释放一些不需要的内存资源。
        //level =TRIM_MEMORY_RUNNING_LOW  10 表示应用程序正常运行，并且不会被杀掉。但是目前手机的内存已经非常低了，你的正在运行的进程需要释放一些不需要的内存资源。
        //level =TRIM_MEMORY_RUNNING_CRITICAL  15 表示应用程序仍然正常运行，但是系统内存已经极度低了，即将不能保留任何后台进程 了。这个时候我们应当尽可能地去释放任何不必要的资源，下一步onLowMemory将会被调用，这样的话，后台将不会保留任何进程。
        //level =TRIM_MEMORY_UI_HIDDEN  20 表示应用程序的所有UI界面被隐藏了，即用户点击了Home键
        //level =TRIM_MEMORY_BACKGROUND  40 app进程不可见，处于LRU列表中，这时候是个释放资源的好时机。
        //level =TRIM_MEMORY_MODERATE  60 系统目前内存已经很低了，并且我们的程序处于LRU缓存列表的中间位置。腾出一些内存让系统运行其他的进程。
        //level =TRIM_MEMORY_COMPLETE  80 系统目前内存已经很低了，并且我们的程序处于LRU缓存列表的最边缘位置，如果系统找不到更多可能的内存，我们的app进程很快将被杀死。
    }
}
