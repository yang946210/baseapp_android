package com.yang.ktdemo.note



/**
 ****2类和对象
 */


/**
 * 类默认都是final的,接口默认都是open,类中的方法默认也是finial
 *
 *
 *
 * interface <接口：作用类只能被实现，其变量和方法亦然>
 * abstract <抽象类：作用类只能被继承，作用方法必须被重写,作用变量必须加在构造函数或添加get,set方法>
 * open  <作用类可以被继承，作用方法可以被重写>
 * final <作用禁止被继承，作用方法禁止被重写>
 *
 * constructor <主构造函数：跟在类名后面，特殊条件可不写关键字
 *              次级构造函数：写在类内部，必须要有主构造函数>
 * init <初始化器：类初始化器，只从主构造函数取数据，然后执行init,再执行次级构造函数>
 */
interface People {
    var height: String

    var weight: String

    fun study()

    fun play()
}

abstract class Student(name: String) : People {

    abstract var age: String

    constructor(name: String, birth: String) : this(name)

    constructor(name: String,birth: String,like:String):this(name,birth)

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


fun main(args: Array<String>) {
    var zhangsan = Monitor("zhangsan", "175cm", "25岁")
    print("\n")
    var lisi = Monitor("lisi", "178cm", "35岁", "拉屎")

}


