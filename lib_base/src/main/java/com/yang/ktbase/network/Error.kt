package com.yang.ktbase.network


enum class Error(private val code: Int, private val err: String) {

    /**
     * 返回数据为null
     */
    TIMEOUT_NULL(1001, "返回数据为null");


    fun getKey(): Int {
        return code
    }

    fun getValue(): String {
        return err
    }



}