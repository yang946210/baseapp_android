package com.yang.ktbase.network

import com.google.gson.annotations.SerializedName

data class ResponseResult<T>(@SerializedName("errorCode") var errorCode: Int = -1,
                             @SerializedName("errorMsg") var errorMsg: String? = "other message",
                             @SerializedName("data") var data: T?)
