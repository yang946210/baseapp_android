package com.yang.ktbase.network.entity

import android.widget.Toast
import com.blankj.utilcode.util.ToastUtils
import com.google.gson.JsonParseException
import retrofit2.HttpException
import java.net.SocketTimeoutException
import java.util.concurrent.CancellationException

enum class HttpError(var code: Int, var errorMsg: String) {
    TOKEN_EXPIRE(3001, "token is expired"),
    PARAMS_ERROR(4003, "params is error")

}

internal fun handlingApiExceptions(code: Int?, errorMsg: String?) = when (code) {
    HttpError.TOKEN_EXPIRE.code -> ToastUtils.showLong(HttpError.TOKEN_EXPIRE.errorMsg)
    HttpError.PARAMS_ERROR.code -> ToastUtils.showLong(HttpError.PARAMS_ERROR.errorMsg)
    else -> errorMsg?.let { ToastUtils.showLong(it) }
}

internal fun handlingExceptions(e: Throwable) = when (e) {
    is HttpException -> ToastUtils.showLong(e.message())

    is CancellationException -> {
    }
    is SocketTimeoutException -> {
    }
    is JsonParseException -> {
    }
    else -> {
    }
}