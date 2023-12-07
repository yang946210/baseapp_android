package com.yang.ktbase.util


import java.text.DecimalFormat


/**
 * 字节单位转换
 */
fun getFileUnitSize(size: Long): String {
    val gb = 1024 * 1024 * 1024
    val mb = 1024 * 1014
    val kb = 1024
    val df = DecimalFormat("0.00")

    return when {
        size / gb >= 1 -> df.format(size / gb.toFloat()) + "GB"
        size / mb >= 1 -> df.format(size / mb.toFloat()) + "MB"
        size / kb >= 1 -> df.format(size / kb.toFloat()) + "KB"
        else -> size.toString() + "B"
    }
}

