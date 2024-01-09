package com.yang.ktbase.network

import java.io.Serializable

open class BaseResponse<T>(open val data: T? = null,
                           open val errorCode: Int? = null,
                           open val errorMsg: String? = null,
                           open val error: Throwable? = null) : Serializable {
    val isSuccess: Boolean
        get() = errorCode == 0 //实际场景决定

}

/**
 * 请求成功返回
 */
data class SuccessResponse<T>(val response: T) : BaseResponse<T>(data = response)

/**
 * 请求失败返回
 */
data class FailedResponse<T>(override val errorCode: Int?,
                             override val errorMsg: String?) : BaseResponse<T>(errorCode = errorCode, errorMsg = errorMsg)

/**
 * 执行异常返回
 */
data class ErrorResponse<T>(val throwable: Throwable) : BaseResponse<T>(error = throwable)
