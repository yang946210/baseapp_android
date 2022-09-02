# Java

#### 基本数据类型和引用类型
+ 栈内存分配速度快，空间小。Java的基本类型，局部变量，引用类型的对象引用是存在在栈内存中。
+ 堆内存分配速度稍慢，空间大。Java的引用类型指向的具体对象是存放在堆内存中的。

#### 虚拟机原理

1. 程序技术区(线程私有)：一块较小的内存空间，当前线程所执行的字节码行号指示器
2. 虚拟机栈(线程私有)：它用来保存方法的局部变量、部分结果，并参与方法的调用和返回。
3. 本地方法区(线程私有)：
4. 虚拟机堆(线程共享)：
5. 方法区(线程共享)：

![](image/jvm.png)

#### 设计模式

+ 观察者：Android当前运用最多的设计模式
+ 单例：一个进程只有单一实例。懒汉，饿汉，双锁，静态内部类，枚举...object。
+ 工厂：
+ 代理模式：by
+ 构建者： builder

#### 数组和集合

+ 集合可分为 Collection 和 Map 两种体系：
    + Collection
        + list(有序可重复)
          1.ArrayList 线程不安全，动态数组结构，查询快，新删慢 2.LinkedList 线程不安全，链表结构，查询慢，增删快 3.Vector 线程安全(开销大点)
          ，其他同ArrayList
        + set(不重复集合，链表结构)
            + HashSet 线程不安全，无序不重复 <----LinkedHashSet有序不重复
    + Map(建值对，建不能重复，值可以)
        + HashMap 底层数组+链表实现，无论key还是value都不能为null，线程安全 <----LinkHashMap使用entry新增的两个参数来维护元素之间的关系。
        + HashTab 底层数组+链表+红黑树实现，可以存储null键和null值，线程不安全
        h
#### String,StringBuffer,StringBuilder

+ String 字符串常量，字符串长度不可变
+ StringBuffer 字符串变量（Synchronized，线程安全）效率高
+ StringBuffer 字符串变量（线程不安全）效率更高一点

# Android

#### 网络编程

+ http/http(1应用层):
    1. HTTPS是加密传输协议，HTTP是明文传输协议
    2. HTTPS需要用到SSL证书，而HTTP不用
    3. HTTPS标准端口443，HTTP标准端口80，等
+ tcp(2传输层):面向链接的通信协议，安全可靠。即先通过三次握手建立链接，再进行数据传输，最后四次挥手释放链接
+ udp(2传输层):无链接通信协议，不可靠。收的只管收，发的只管发
+ ip（3网络层）
+ MQTT协议，工作在TCP/IP协议族上，是基于发布/订阅模式的物联网通信协议，凭借简单易实现、支持 QoS、报文小等特点，占据了物联网协议的半壁江山。

#### Activity

+ 启动模式：
    1. standard 默认启动模式
    2. singleTop(FLAG_ACTIVITY_SINGLE_TOP) 栈顶单例
    3. singleTask(FLAG_ACTIVITY_NEW_TASK) 栈内单例，复用
    4. singleInstance 单独任务栈。
+ 生命周期：

#### Service:

+ 启动服务：onCreate()->onStartCommend()->onDestroy()
+ 绑定服务：onCreate()->onBind()->onUnbind()->onDestroy()

#### 线程和线程池

+ 线程的生命周期

  1. 新建：当一个Thread类或其子类的对象被声明并创建时，新生的线程对象处于新建状态
  2. 就绪：处于新建状态的线程被start()后，将进入线程队列等待CPU时间片，此时它已具备了运行的条件，只是没分配到CPU资源
  3. 运行：当就绪的线程被调度并获得CPU资源时,便进入运行状态，run()方法定义了线程的操作和功能
  4. 阻塞：在某种特殊情况下，被人为挂起或执行输入输出操作时，让出CPU并临时中止自己的执行，进入阻塞状态
  5. 死亡：线程完成了它的全部工作或线程被提前强制性地中止或出现异常导致结束

+ 线程切换的方法
    1. Thread.start()(可能存在内存泄露)
    2. 线程池开启线程

+ 线程池ThreadPoolExecutor
    1. corePoolSize 核心线程数(一直存活)
    2. maximumPoolSize 最大线程数：减去核心线程数为非核心线程数(超过keepAliveTime时间会自动销毁)
    3. maximumPoolSize 非核心线程闲置销毁时间
    4. workQueue 消息队列，可以设置最大最小数，
    5. unit 时间单位
    6. threadFactory 线程创建工厂
    7. handler 拒绝策略

#### Handler

+ 作用：线程切换
+ Handler：负责发送处理消息(持有Looper,MessageQueue的引用)
+ Looper: 负责循环取出消息给Handler处理(会初始化一个ThreadLocal，MessageQueue,Looper)
  ，通过死循环获取消息再调用handler的dispatchMessage方法分发
+ MessageQueue：消息队列，负责存储消息
+ ThreadLocal: 线程数据存储区，在每个线程中存放各自对应的一个Looper
+ 注意：
    1. 一个线程可以有多个handler,但是只有一个Looper,
    2. 使用Looper必须手动开启循环(Looper.loop())，主线程Looper在activityThread中已经初始化过了，子线程Handler必须手动调用。
+ ![](image/handler.png)

#### 事件分发

+ 事件分发主要由以下三个方法完成:
    1. public boolean dispatchTouchEvent（event）：用于进行点击事件的分发
    2. public boolean onInterceptTouchEvent（event）：用于进行点击事件的拦截(viewGroup专用)
    3. public boolean onTouchEvent（event）：用于处理点击事件
+ ![](image/viewtouch.png)

#### 启动流程

+ 步骤:
    1. 加载BootLoader --> 初始化内核 --> 启动init进程 --> init进程fork出Zygote进程 --> Zygote进程fork出SystemServer进程(
       AMS,WMS,PMS所在)
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

#### jetpack

#### 版本适配

+ 4.4
    + webView添加注解，解决远程注入漏洞
+ 5
    + 新增悬挂Notification
    + materialDesign
    + 引入Toolbar，取代ActionBar
+ 6
    + 运行时权限，部分权限需要动态申请
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
    

# C
#### 基础
	1.**.c/.cpp** 源文件 程序实现文件。
	2.**.h** 头文件 把我们可以暴露给别人的东西暴露。不需要暴露的就在源文件中实现。
	3.**"Demo.h" 和 <Demo.h> **的区别 引入相对路径和 绝对路径的区别
	4.**CMAKE**构建工具，类似与Android中的gradle

#### 基本数据类型

 	1.**signed**----有符号，可修饰char、int。Int是默认有符号的。
 	2.**unsigned**-----无符号，修饰int 、char
 	3.**sizeof**-----表达式 sizeof(type或变量) 得到对象或类型的存储字节大小。

| 整型           | 字节 | 取值范围                        | 占位 |
| :------------- | ---- | ------------------------------- | ---- |
| int            | 4    | -2,147,483,648 到 2,147,483,647 | %d   |
| unsigned int   | 4    | 0 到 4,294,967,295              | %u   |
| short          | 2    | -32,768 到 32,767               | %hd  |
| unsigned short | 2    | 0 到 65,535                     | %hu  |
| long           | 4    | -2,147,483,648 到 2,147,483,647 | %ld  |
| unsigned long  | 4    | 0 到 4,294,967,295              | %lu  |
| char           | 1    | -128 到 127                     | %c   |
| unsigned char  | 1    | 0 到 255                        | %c   |


| 浮点型      | 字节 | 精度     | 占位 |
| ----------- | ---- | -------- | ---- |
| float       | 4    | 6位小数  | %f   |
| double      | 8    | 15位小数 | %lf  |
| long double | 8    | 19位小数 | %Lf  |

#### 字符串

+ C中定义字符串

  ```c
  char c_str1[] = {'h','e','l','o','\0',}; // \0表示字符串结束。
  char *c_str2 = "hell0"; //这种方式定义会默认在末尾增加\0
  cout << "str1:" << c_str1 << endl    
  cout << "str2:" << c_str2 << endl    
  ```

+ C++中定义字符串

  ```c
  string str1="hello";
  //调用构造方法
  string str2(str1);
  string str3("hello");
  //申请内存，调用构造方法
  string *str4=new string("hello");  //需要调用delete str4
  string str5=str1+str2;
  
  cout <<str2.c_str() <<endl //不能像java那样直接打印string对象
  str4->c_str();     
      
  ```

#### 动态内存申请

``` 
1.**malloc** 没有初始化内存的内容,一般调用函数memset来初始化(为什么)这部分的内存空间.
2.**calloc** 申请内存并将初始化内存数据为null, int *pn = (int*)calloc(10, sizeof(int));
3.**realloc** 对malloc申请的内存进行大小的调整.
4.**free** 释放内存(为什么？)

1.**物理内存**物理内存指通过物理内存条而获得的内存空间
2.**虚拟内存**一种内存管理技术
电脑中所运行的程序均需经由内存执行，若执行的程序占用内存很大，则会导致内存消耗尽。
虚拟内存技术还会匀出一部分硬盘空间来充当内存使用。
```

存放程序执行代码（cpu要执行的指令）

> 栈是向低地址扩展数据结构
> 堆是向高地址扩展数据结构

进程分配内存主要由两个系统调用完成：**brk和mmap** 。
1. brk是将_edata(指带堆位置的指针)往高地址推；
2. mmap 找一块空闲的虚拟内存。

通过glibc (C标准库)中提供的malloc函数完成内存申请
malloc小于128k的内存，使用brk分配内存，将_edata往高地址推,大于128k则使用mmap



#### 指针

```c
int i=10;
int *p = &i;    //指针声明加赋值,变量要用&，数组不用
int pv = *p;    //解引用，使用*解出这个地址的值 即为pv=10
int **pp = &p;  //多级指针,指针的指针，函数部分用的比较多
```
```c
const char *p = str;  	==>str[0] = 'c'; //正确 p[0] = 'c';   //错误 
char const *p1; 同上
char * const p2 = str;  ==> p2[0] = 'd';//正确 p2 = "12345"; //错误
char const* const p3 = str; 啥都不能修改
```



#### 函数

	1.**typedef** 创建别名,如:typedef void(*Fun)(char *);
	2.**void(*p)(char*)** 类似于函数表达式，依次为返回值/函数名/参数

```c
void success(int a) {
	printf_s("成功%d\n", a);
}
typedef void(*Callback)(int a);

Callback callback = success;

callback(2);
```



#### 预处理器

| 预处理器 | 说明         |
| -------- | ------------ |
| #include | 导入头文件   |
| #if      | if           |
| #elif    | else if      |
| #else    | else         |
| #endif   | 结束 if      |
| #define  | 宏定义       |
| #ifdef   | 如果定义了宏 |
| #ifndef  | 如果未定义宏 |
| #undef   | 取消宏定义   |



#### 宏

```c
//宏变量，用A替换2
#define A 2

//宏函数
#define test(i) i > 10 ? 10 :0
```



#### 命名空间

```c
namespace A{
    void a(){}
}

错误 : a();
// :: 域操作符
正确： A::a();

//当然也能够嵌套
namespace A {
	namespace B{
		void a() {};
	}
}
A::B::a();

//还能够使用using 关键字
using namespace A;
using namespace A::B;
```

```c
//当全局变量在局部函数中与其中某个变量重名，那么就可以用::来区分 
int i;
int main(){
    int i = 10;
    printf("i : %d\n",i);
    //操作全局变量
    ::i = 11;
    printf("i : %d\n",::i);
}
```



#### 结构体

+ 类似与java中的class,bean类

```c
//定义一个结构体,两种方式；
struct Student
{
    short f;
	int i;
	short j;
    int k;
};

typedef struct {
	int i1;
	int i2;
}Student1;

//使用结构体
struct Student stu;
stu.i = 10;
stu.j = 20;

Student1 stu1;
stu1.i1 = 30;
stu1.i2 = 40;

cout << stu1.i1 << endl;
```

+ 字节对齐

  1. 自然对齐(默认):变量保存都是按照变量字最长节数的整数倍，Student中的 f,i,j,k分别内存为 01，4567，89, 12131415。

  2. #pragma pack(2) 指定以两字节对齐。降低效率，可能引发错误。

     

#### 共用体

```c
在相同的内存位置，存储不同数据
//占用4字节
union Data
{
	int i;
	short j;
}
union Data data;
data.i = 1;
//i的数据损坏
data.j = 1.1f;
```



# C++

#### 类

	1. 析构函数 **~Student(){}** ，类的一种特殊的成员函数，它会在每次删除所创建的对象时执行(不需要手动调用)。
	2. 友元函数 **friend void Name(Stu stu)**，定义在类外部，但有权访问类的所有私有（private）成员和保护（protected）成员。
	3. 常量函数 **void setName(char* _name) const** 函数后写上const，表示不会也不允许修改类中的成员。

```c++
class Student {
    //private：可以被该类中的函数、友元函数访问。 不能被任何其他访问，该类的对象也不能访问。 
	//protected：可以被该类中的函数、子类的函数、友元函数访问。 但不能被该类的对象访问。
	//public：可以被该类中的函数、子类的函数、友元函数访问，也可以被该类的对象访问。  
     //默认 private
	int i;   
private:
	int j;
protected:
	int k;
public:
    //构造方法 
	Student(int i,int j,int k):i(i),j(j),k(k){};	
    
    //析构函数 
	~Student(){};	
    
    //常量函数
    void  setName(char* _name) const  {
		//错误 不能修改name 去掉const之后可以
		name = _name;
	}
};

//===========================================//

//调用构造方法 栈，出方法释放student 调用析构方法
Student student(1,2,3); 


//动态内存(堆)
Student *student = new Student(1,2,3);
//释放
delete student;
student = 0;
```

