package com.yang.appkt

import android.util.Log


object BaseInfo {

    private fun mLogInfo(msg: String) {
        Log.d("===info===", msg)
    }

    /**
     *  *************** 基本类型 ***************
     */
    var aByte: Byte = -128
    var aShort: Short = 0b01010101_01010101 //short 16位
    var aInt = 10_9999_2121                 //int 32位
    var aLong = 1222L        //double 64位
    var aDouble = 122.7       //double 64位
    var aFloat = 12.22F      //float 32位
    var aBoolean = false     //boolean
    var aChar = 'a'          //字符
    var aIntRange = 1..8
    var aString = "sds"      //字符串
    var aString2 = String(charArrayOf('s', 'd', 's'))
    var aString3 = """
            \n
            \t
         //ss//   
        """.trimIndent() //原始字符串


    /**
     *    *************** 函数 ***************
     */
    fun aFun(aInt: Int, bInt: Int): Int {
        return aInt + bInt
    }

    fun bFun(aInt: Int, bInt: Int): Int = aInt + bInt
    var cInt = { aInt: Int, bInt: Int -> aInt + bInt }
    var dFun: (aInt: Int, bInt: Int) -> Int = { aInt, bInt -> aInt + bInt }


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

        fun daze() {
            print("Student is talk")
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
            println("单例，getCar")
        }
    }

    val objectTest = {
        SigHelper.getCar()
    }

    /**
     * 数据类
     */
    data class Children(val name: String, var age: String)

    val dataClassTest = {
        val children = Children("张三", "18")
        mLogInfo(children.copy(name = "李四").toString())
    }


}


