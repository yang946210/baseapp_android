package com.yang.ktbase.network

import com.google.gson.annotations.SerializedName


/**
 * 请求返回实体封装
 */
data class ResponseData<T>(
    @SerializedName(value = "errorCode", alternate = ["code"])
    val code: Int,
    @SerializedName(value = "errorMsg", alternate = ["message", "msg"])
    val message: String?,
    @SerializedName(value = "data", alternate = ["result", "body"])
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
    private fun isSuccess():Boolean{
        return code == 0
    }
}

fun <T> ResponseData<T>.dataOrThrow(): T {
    if (code != 0) {
        throw RuntimeException(message)
    }
    return data!!
}
class NetException(message: String) : Exception(message)
