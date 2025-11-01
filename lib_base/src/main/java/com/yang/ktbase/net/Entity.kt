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
        onSuccess: (T) -> Unit,
        onError: (NetException) -> Unit,
        onNullResult: (() -> T)?
    ) {
        if (isSuccess()) {
            val value = data ?: onNullResult?.invoke()
            if (value != null) {
                onSuccess(value)
            } else {
                onError(NetException(message ?: "未知错误"))
            }
        } else {
            onError(NetException(message ?: "未知错误"))
        }
    }

    /**
     * 请求成功
     */
    fun isSuccess():Boolean{
        return code == 0
    }
}


class NetException(message: String) : Exception(message)