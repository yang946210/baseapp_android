package com.yang.ktbase.vm

import androidx.lifecycle.ViewModel
import com.yang.ktbase.network.entity.ApiErrorResponse
import com.yang.ktbase.network.entity.ApiFailedResponse
import com.yang.ktbase.network.entity.ApiResponse
import com.yang.ktbase.network.entity.ApiSuccessResponse
import com.yang.ktbase.network.NET_CONF_EMPTY_CODE
import com.yang.ktbase.network.NET_CONF_EMPTY_MSG
import com.yang.ktbase.network.NET_CONF_TIME_OUT_MSG
import com.yang.ktbase.util.logD


/**
 * ViewModel基类
 */
abstract class BaseViewModel : ViewModel(){

    /**
     * 网络请求数据简单处理
     */
    suspend fun <T> executeHttp(block: suspend () -> ApiResponse<T>): ApiResponse<T> {
        runCatching {
            block.invoke()
        }.onSuccess { response: ApiResponse<T> ->
            //返回200之后再按业务层判断是否真的成功。
            return if (!response.isSuccess){
                ApiFailedResponse(response.errorCode,response.errorMsg)
            }else{
                val data = response.data
                if (data == null || data is List<*> && (data as List<*>).isEmpty()){
                    //null数据也是不成功的
                    ApiFailedResponse(NET_CONF_EMPTY_CODE, NET_CONF_EMPTY_MSG)
                } else{
                    //保证返回不为null
                    ApiSuccessResponse(data)
                }
            }
        }.onFailure { e ->
            return ApiErrorResponse(e)
        }
        return ApiErrorResponse(Throwable(NET_CONF_TIME_OUT_MSG))
    }


    override fun onCleared() {
        super.onCleared()
        "ViewModel onCleared :${this::class.java.simpleName}".logD()
    }


}