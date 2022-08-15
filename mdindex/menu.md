# Java

# Android

## activity

## Handler

+ 作用：线程切换
+ Handler：负责发送处理消息(持有Looper,MessageQueue的引用)
+ Looper: 负责循环取出消息给Handler处理(会初始化一个ThreadLocal，MessageQueue,Looper)
  ，通过死循环获取消息再调用handler的dispatchMessage方法分发
+ MessageQueue：消息队列，负责存储消息
+ ThreadLocal: 线程数据存储区，在每个线程中存放各自对应的一个Looper
+ 注意：
    1. 一个线程可以有多个handler,但是只有一个Looper,
    2. 使用Looper必须手动开启循环(Looper.loop())，主线程Looper在activityThread中已经初始化过了，子线程Handler必须手动调用。

## 事件分发

+ 事件分发主要由以下三个方法完成:
    1. public boolean dispatchTouchEvent（event）：用于进行点击事件的分发
    2. public boolean onInterceptTouchEvent（event）：用于进行点击事件的拦截(viewGroup专用)
    3. public boolean onTouchEvent（event）：用于处理点击事件
+ ![]()

## 启动流程

+ 步骤:
    1. init进程创建zygote进程，zygote进程fork出SystemServer进程(AMS,WMS,PMS所在)
    2. launcher进程启动(先不管)
    3. 点击桌面图标时:Launcher -> startActivityActivity() -> mInstrumentation.execStartActivity(
       ActivityThread.getApplicationThread()) <br>
       getApplicationThread是ActivityThread的内部类ApplicationThread，这是一个Binder对象，之后AMS通过此对象与App的通信。
    4. AMS启动Activity并通知Launcher进入Paused状态
    5. 新的进程启动，ActivityThread的main函数入口(进入第二部)
    6. 创建activity(performLaunchActivity)并做如下操作：
        1. 创建要启动activity的上下文环境
        2. 通过Instrumentation的newActivity方法，以反射形式创建activity实例
        3. 如果Application不存在的话会创建Application并调用Application的onCreate方法
        4. 初始化Activity，创建Window对象（PhoneWindow）并实现Activity和Window相关联
        5. 通过Instrumentation调用Activity的onCreate方法

## jetpack

## 版本适配

+ 4.4
    + webView添加注解，解决远程注入漏洞
+ 5
    + 新增悬挂Notification
    + matierialDesign
    + 引入Toolbar，取代ActionBar
+ 6
    + 运行时权限，部分权限需要动态申请
    + App Link 无缝打开 App 提高用户体验
    + 支持指纹
    + Doze电量管理，手机静止不动一段时间后，会进入Doze电量管理模式，提高续航时间
+ 7
    + 文件分享：禁止通过file://协议分享文件，需要通过FileProvider来生成content://协议操作
    + 多窗口模式（分屏模式）
+ 8
    + 后台service限制，骚操作进程保活基本无效了
    + 广播静态注册已经无效了，需要通过动态注册的方式
    + 通知改进：所有通知都必须分到一个渠道，即新增NotificationChannel
    + 运行时权限策略变化，之前6.0 动态申请某个权限会错误的将对应组权限都开放
+ 9
    + 刘海屏给出官方适配
    + 无法通过application,server启动activity了(解决:添加FLAG_ACTIVITY_NEW_TASK)。
+ 10
    + 分区存储，无法直接访问外部存储，(此版本尚可兼容)
+ 11
    + 强制分区存储
    + 强制需要v2才能安装
    + 强制 设置allowBackup
+ 12
    + 启动优化

.

​     

