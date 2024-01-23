package com.example.jetpack.vm

import com.blankj.utilcode.util.ToastUtils
import com.yang.ktbase.network.BaseResponse
import com.google.gson.JsonArray
import com.yang.ktbase.network.RetrofitApi
import com.yang.ktbase.network.netutil.execute
import com.yang.ktbase.network.netutil.request
import com.yang.ktbase.vm.BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

/**
 * 示例代码
 */
class NetExpViewModel : BaseViewModel() {


    private val _titleData = MutableStateFlow<BaseResponse<JsonArray>>(BaseResponse())
    val titleData: StateFlow<BaseResponse<JsonArray>> = _titleData.asStateFlow()

    private val titleData3 = MutableStateFlow<BaseResponse<JsonArray>>(BaseResponse())

    suspend fun getBanner1() {
        _titleData.value = execute { RetrofitApi.api.getBanner() }
    }

    suspend fun getBanner2(): BaseResponse<JsonArray> {
        return execute {
            RetrofitApi.api.getBanner()
        }
    }

    fun getBanner3() {
        request(
            { RetrofitApi.api.getBanner() },
            showDialog = true,
            loadingMsg = "加载不加载",
            onError = {
             ToastUtils.showLong("自定义错误处理方式")
            }
        ){

        }
    }
}