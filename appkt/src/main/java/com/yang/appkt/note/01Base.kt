package com.yang.ktdemo.note


/**
 ****1基本数据类型
 */



var aByte:Byte = -128
var aShort: Short =0b01010101_01010101 //short 16位
var aInt = 10_9999_2121                 //int 32位
var aLong = 1222L        //double 64位
var aDouble =122.7       //double 64位
var aFloat = 12.22F      //float 32位
var aBoolean = false     //boolean
var aChar = 'a'          //字符
var aIntRange=1..8
var aString = "sds"      //字符串
var aString2 = String(charArrayOf('s', 'd', 's'))
var aString3 ="""
            \n
            \t
         //ss//   
        """.trimIndent() //原始字符串

/**
 * 数据类型
 */
fun main(args: Array<String>){
    print("\n ${aString == aString2}") //比较值
    print("\n ${aString === aString2}") //比较内存地址
    print("\n" + "$aInt + $aLong 等于 ${aInt + aLong}") //支持字符串表达式
    print("\n" + aString3)
    print("\n")
}
