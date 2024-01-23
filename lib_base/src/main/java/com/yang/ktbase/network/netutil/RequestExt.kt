package com.yang.ktbase.network.netutil

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import com.yang.ktbase.NetConfig
import com.yang.ktbase.network.BaseResponse
import com.yang.ktbase.network.ErrorResponse
import com.yang.ktbase.network.FailedResponse
import com.yang.ktbase.network.NetException
import com.yang.ktbase.network.ResultListener
import com.yang.ktbase.network.SuccessResponse
import com.yang.ktbase.network.parseData
import com.yang.ktbase.util.logD
import com.yang.ktbase.vm.BaseViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch


/**
 * 请求挂载组件，哪个类要用请求就继承这个
 */
interface RequestExt : LifecycleOwner {

    fun showLoading()

    fun hideLoading()

}


/**
 * 简易流处理，发送/订阅链式调用
 */
fun <T> RequestExt.requestAndCollect(
    requestBlock: suspend () -> BaseResponse<T>,
    showLoading: Boolean = false,
    listenerBuilder: ResultListener<T>.() -> Unit,
) {
    lifecycleScope.launch {
        flow {
            emit(requestBlock())
        }.onStart {
            if (showLoading) showLoading()
        }.onCompletion {
            if (showLoading) hideLoading()
        }.collect { response ->
            response.parseData(listenerBuilder)
        }
    }
}

/**
 * 流处理：发送端
 */
fun RequestExt.request(showLoading: Boolean = false, requestBlock: suspend () -> Unit) {
    lifecycleScope.launch {
        flow {
            emit(requestBlock())
        }.onStart {
            if (showLoading) showLoading()
        }.onCompletion {
            if (showLoading) hideLoading()
        }.collect()
    }
}

/**
 * 流处理：订阅端
 */
fun <T> Flow<BaseResponse<T>>.collectIn(
    lifecycleOwner: LifecycleOwner,
    minActiveState: Lifecycle.State = Lifecycle.State.STARTED,
    listenerBuilder: ResultListener<T>.() -> Unit
): Job = lifecycleOwner.lifecycleScope.launch {
    flowWithLifecycle(
        lifecycleOwner.lifecycle,
        minActiveState
    ).collect { baseResponse: BaseResponse<T> ->
        baseResponse.parseData(listenerBuilder)
    }
}

/**
 * 执行流操作，并返回不同类型的Response
 */
suspend fun <T> ViewModel.execute(block: suspend () -> BaseResponse<T>): BaseResponse<T> {
    runCatching {
        block.invoke()
    }.onSuccess { baseResponse: BaseResponse<T> ->
        //返回200之后再按业务层判断是否真的成功。
        return if (!baseResponse.isSuccess) {
            FailedResponse(baseResponse.errorCode, baseResponse.errorMsg)
        } else {
            val data = baseResponse.data
            if (data == null || data is List<*> && (data as List<*>).isEmpty()) {
                //null数据也是不成功的
                FailedResponse(NetConfig.NET_CONF_EMPTY_CODE, NetConfig.NET_CONF_EMPTY_MSG)
            } else {
                //保证返回不为null
                SuccessResponse(data)
            }
        }
    }.onFailure { e ->
        return ErrorResponse(e)
    }
    return ErrorResponse(Throwable(NetConfig.NET_CONF_NO_DATA))
}


/**
 * 请求封装2
 */
fun <T> BaseViewModel.request(
    block: suspend () -> BaseResponse<T>,
    showDialog: Boolean = false,
    loadingMsg: String = "请求网络中...",
    onError: (NetException) -> Unit = {},
    onSuccess: (T) -> Unit
): Job {
    return viewModelScope.launch {
        runCatching {
            //if (isShowDialog) //loadingChange.showDialog.postValue(loadingMessage)
            //请求体
            block()
        }.onSuccess {
            //网络请求成功 关闭弹窗
            //loadingChange.dismissDialog.postValue(false)
            //返回200之后再按业务层判断是否真的成功。
            val data = it.data
            val isSuccess = it.isSuccess
            if (isSuccess) {
                onSuccess(data!!)
            } else throw NetException(it.errorCode, it.errorMsg)

        }.onFailure {
            it.printStackTrace()
            it.message?.logD(tag = "okhttp==>>")
            //网络请求异常 关闭弹窗
            //loadingChange.dismissDialog.postValue(false)
            if (it is NetException) {
                onError(it)
            } else {
                onError(NetException(-1, it.message.toString()))
            }
        }
    }
}

