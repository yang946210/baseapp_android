package com.yang.ktbase.ext


import androidx.lifecycle.viewModelScope
import com.yang.ktbase.base.BaseViewModel
import com.yang.ktbase.net.ResponseResult
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import okhttp3.internal.http2.ErrorCode
import java.lang.reflect.ParameterizedType
import kotlin.concurrent.thread


/**
 *
 * @receiver BaseViewModel viewModel 请求拓展类
 * @param block SuspendFunction0<ResponseResult<T>> 请求体
 * @param success SuspendFunction0<Unit> 请求成功回调
 * @param error SuspendFunction1<[@kotlin.ParameterName] Throwable, Unit>失败回调
 * @param showLoading Boolean 是否显示请求dialog
 * @param loadingMsg String  dialog显示字体
 * @return Job
 */
fun <T> BaseViewModel.request(
    block: suspend () -> ResponseResult<T>,
    success:  (T?) -> Unit,
    error:  (t: Throwable) -> Unit,
    showLoading: Boolean = false,
    loadingMsg: String = "加载中..."
): Job {
    return viewModelScope.launch (){
        runCatching {
            block()
        }.onSuccess {
            success(it.data)
        }.onFailure {
            error(it)
        }
    }
}

