package com.yang.appkt

import android.util.FloatProperty
import android.util.Log
import kotlin.random.Random


object BaseInfo {

    private fun mLogInfo(msg: String) {
        Log.d("===info===", msg)
    }


    /**
     * 基本类型
     */
    var aByte: Byte = -128                  //byte 8位
    var aShort: Short = 0b01010101_01010101 //short 16位
    var aInt: Int = 10_9999_2121             //int 32位
    var aLong: Long = 1222L                  //long 64位
    var aFloat: Float = 12.22F               //float 32位
    var aDouble: Float = 122.72F             //double 64位

    var aBoolean = false     //boolean
    var aChar = 'a'          //字符
    private var nString = if (Random.nextBoolean()) "lalala" else null
    private var aString = "sds"      //字符串
    var aString2 = String(charArrayOf('s', 'd', 's')) //字符
    var aString3 = """
         //ss   //    
        """.trimIndent()    //原始字符串

    /**
     * range
     */
    var aString4: String = "${aString}字符串模板"  //字符串
    private var aIntRange = 1..8  //1到 8的数组
    private var bIntRange = 1 until 9 step 2  //1到 8的数组(不包含8)2步起跳


    /**
     * let also
     * run apply
     * with
     */
    private var nullString: String? = if (Random.nextBoolean()) "justString" else null

    fun letTest() {
        nullString?.let {
            mLogInfo( it.length.toString())
            mLogInfo(it)
            "return"
        } //为null就不执,默认返回最后一行。

        nullString?.also{
            mLogInfo( it.length.toString())
            mLogInfo(it)
            "not return,return the nullString"
        } //和let用法差不多，但是返回的时nullString对象

        nullString?.run {
            mLogInfo(length.toString())
            mLogInfo(this)
            "return"
        }//为null就不执,默认返回最后一行。
        nullString?.apply {
            mLogInfo(length.toString())
            mLogInfo(this)
            "not return,return the nullString"
        }
        with(nullString){
            mLogInfo(this?.length.toString())
        }
    }


    /**
     * null处理
     */
    private var len = nString?.length             //不为空才调用
    private var len1 = nString?.length ?: "lalala"  //null时给与默认值
    //private var len2=nString!!.length           //直接空指针

    fun infoTest() {
        mLogInfo("len==${len ?: "2"}  len1=$len1")
        bIntRange.forEach { mLogInfo(it.toString()) }
        aString4.also { }
    }

    /**
     * vararg 可变参数
     * in  是否在某个区间内
     * is  类似instanceOf
     * when 类似switch
     */
    fun varargTest(vararg string: String, indexStr: String, indexInt: Int) {
        for (s in string) {
            mLogInfo(s)
        }
        if (indexStr in string) {
            mLogInfo(indexStr)
        }

        if (indexInt in 1..10) {
            mLogInfo(indexInt.toString())
        }

        val s = when (indexStr) {
            "a" -> "aaa"
            "b" -> "aac"
            else -> "bbb"
        }
        mLogInfo(s)

    }

    /**
     *  函数
     *
     */
    fun bFun(aInt: Int, bInt: Int): Int = aInt + bInt
    var cInt = { aInt: Int, bInt: Int -> aInt + bInt }
    var dFun: (aInt: Int, bInt: Int) -> Int = { aInt, bInt -> aInt + bInt }

    private fun maxOf(aInt: Int, bInt: Int = 2) = if (aInt > bInt) aInt else bInt

    fun funTest() {
        mLogInfo(maxOf(4).toString())
    }


    /**
     *
     */


    /**
     *
     * ************** 类 ***************
     *
     *
     *
     * 类默认都是final的,接口默认都是open,类中的方法默认也是finial
     *
     * interface <接口：作用类只能被实现，其变量和方法亦然>
     * abstract <抽象类：作用类只能被继承，作用方法必须被重写,作用变量必须加在构造函数或添加get,set方法>
     * open  <作用类可以被继承，作用方法可以被重写>
     * final <作用禁止被继承，作用方法禁止被重写>
     *
     * constructor <主构造函数：跟在类名后面，特殊条件可不写关键字
     *              次级构造函数：写在类内部，必须要有主构造函数>
     * init <初始化器：类初始化器，只从主构造函数取数据，然后执行init,再执行次级构造函数>
     *
     */
    interface People {
        var height: String

        var weight: String

        fun study()

        fun play()
    }

    //抽象类
    abstract class Student(name: String) : People {

        abstract var age: String

        constructor(name: String, birth: String) : this(name)

        constructor(name: String, birth: String, like: String) : this(name, birth)

        override fun play() {
            print("Student is run")
        }

        abstract fun talk()


        open fun run() {
            print("Student is run")
        }
    }

    //类
    open class Monitor(
        private var sName: String,
        override var height: String,
        override var age: String
    ) : Student(sName) {
        private var monName: String = sName;
        private var monNickname: String? = null;


        constructor(name: String, height: String, age: String, nickname: String) : this(
            name,
            height,
            age
        ) {
            monNickname = nickname;
            print("next name is $name height is $height nickName $nickname")
        }


        override fun study() {
            print("Monitor is study")
        }

        override var weight: String
            get() = "20kg"
            set(value) {}

        final override fun play() {
            print("Monitor is play")
        }

        override fun talk() {
            print("Monitor is talk")
        }

        override fun run() {
            //super.run()
            print("Monitor is run")
            make(sName)
        }

        private fun make(name: String) {
            print("Monitor is make")
        }
    }

    /**
     * 单例类
     */
    object SigHelper {
        var name: String = "text"

        fun getCar() {
            mLogInfo("单例，getCar")
        }
    }

    fun objectTest() {
        SigHelper.getCar()
    }

    /**
     * 数据类
     */
    data class Children(val name: String, var age: String)

    fun dataClassTest() {
        val children = Children("张三", "18")
        mLogInfo(children.copy(name = "李四").toString())
    }


}


