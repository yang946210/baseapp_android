package com.example.jetpack.vm

import com.yang.ktbase.network.BaseResponse
import com.google.gson.JsonArray
import com.yang.ktbase.network.RetrofitApi
import com.yang.ktbase.network.netutil.executeHttp
import com.yang.ktbase.vm.BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

/**
 * 示例代码
 */
class NetExpViewModel: BaseViewModel() {


    private val _titleData = MutableStateFlow<BaseResponse<JsonArray>>(BaseResponse())
    val titleData: StateFlow<BaseResponse<JsonArray>> = _titleData.asStateFlow()

    suspend fun getBanner1() {
        _titleData.value = executeHttp { RetrofitApi.api.getBanner() }
    }

    suspend fun getPage(pageIndex:Int): BaseResponse<JsonArray> {
        return executeHttp {
            RetrofitApi.api.getData(pageIndex)
        }
    };
}