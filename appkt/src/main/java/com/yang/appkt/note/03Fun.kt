package com.yang.ktdemo.note

/**
 * 函数相关
 */


/**
 * 函数的四种基本写法
 */
fun aFun(aInt: Int, bInt: Int): Int {
    return aInt + bInt
}

fun bFun(aInt: Int, bInt: Int): Int = aInt + bInt

var cInt = { aInt: Int, bInt: Int -> aInt + bInt }

var dFun: (aInt: Int, bInt: Int) -> Int = { aInt, bInt -> aInt + bInt }


data class Man(val name: String, val age: Int, val play: String)

val list = arrayListOf<Man>()

/**
 * dsl
 */

fun main(args: Array<String>) {
    getManInfo()
}

fun getManInfo() {


    list.add(Man("张三", 12, "闲逛"))
    list.add(Man("李四", 22, "喝水"))
    list.add(Man("王二麻子", 21, "发呆"))

    for (e in list) {

    }
    print(list.maxOf { man: Man -> man.age })
    val onlyList = list.filter { it.age>12 }
    onlyList.forEach { print(it.name) }
    print(list.map { it.name })


}

fun less12(man: Man): Boolean {
    return man.age > 12
}