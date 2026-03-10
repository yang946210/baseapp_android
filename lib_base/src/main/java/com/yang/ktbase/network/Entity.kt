package com.yang.ktbase.network

import com.google.gson.annotations.SerializedName


/**
 * response实体
 */
data class ResponseData<T>(
    @SerializedName(value = "errorCode", alternate = ["code"])
    val code: Int,
    @SerializedName(value = "errorMsg", alternate = ["message", "msg"])
    val message: String?,
    @SerializedName(value = "data", alternate = ["result", "body"])
    val data: T?
)

fun <T> ResponseData<T>.dataOrThrow(): T? {
    if (code != 0) {
        throw RuntimeException(message)
    }
    return data
}
