package com.yang.ktbase.net



/**
 * 请求返回实体封装
 */
data class ResponseData<T>(
    val code: Int,
    val message: String?,
    val data: T?
) {
    fun parseData(
        onError: (NetException) -> Unit,
        onSuccess: (T) -> Unit
    ) {
        if (code == 0 && data != null) {
            onSuccess(data)
        } else {
            onError(NetException(message?:"未知错误"))
        }
    }
}


class NetException(message: String) : Exception(message)