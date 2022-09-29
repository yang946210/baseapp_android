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

```c
   //C中定义字符串
   char c_str1[] = {'h','e','l','o','\0',}; // \0表示字符串结束。
   char *c_str2 = "hell0"; //这种方式定义会默认在末尾增加\0
   cout << "str1:" << c_str1 << endl; 
   cout << "str2:" << c_str2 << endl;

 
   //C++中定义字符串
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

//调用构造方法 处于栈，出方法释放student 调用析构方法
Student student(1,2,3); 


//动态内存(堆)
Student *student = new Student(1,2,3);
//需要手动释放
delete student;
student = 0;
```



#### 继承

+ C++多继承，java单继承。



####  多态

+ 一个子类可以有多个父类，它继承了多个父类的特性。
+ 静态多态， Parent p=new Child; p->test(); 调用父类test()方法；
+ 动态多态：
    1. 虚函数：virtual 关键字定义父类方法为虚函数，则根据运行时确定加载那个方法。
    2. 构造方法永远不要设为虚函数，析构方法最好默认声明为虚函数。

#### 模板编程

函数模板（java泛型方法）

```c
 //Java中定义泛型方法
  T <T> a (T a,T b){
    return a > b ? a : b;   
  }

  //c++中定义函数模板
  template <typename T>
  T max(T a,T b){
      return a > b ? a : b;
  };
```


类模板（java泛型类）

  ```c
  //java
  public class A <T>{
      T t;
  }

  //c++	
  template <class T , class E>;
  class test{
      public:
      	T test(T t, E e){
              return t + e;
          }
  };
  ```

#### 容器

+ 序列式容器(元素排列的顺序和元素本身无关，由添加的顺序决定的)

  | 容器           | 说明                                                         |
    | -------------- | ------------------------------------------------------------ |
  | vector         | 连续存储的元素 ，支持快速随机访问                       |
  | list           | 由节点组成的双向链表，每个结点包含着一个元素  ，支持快速插入、删除 |
  | deque          | 双端队列  允许两端都可以进行入队和出队操作的队列             |
  | stack          | 后进先出LIFO(Last In First Out)堆栈                          |
  | queue          | 先进先出FIFO(First Input First Output)队列                   |
  | priority_queue | 有优先级管理的queue                                          |



+ 关联式容易

  | 容器   | 说明               |
  | ------ | ------------------ |
  | set | 暂无   |
  | map   | 暂无 |


# JNI