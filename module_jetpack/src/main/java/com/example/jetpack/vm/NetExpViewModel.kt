package com.example.jetpack.vm

import com.yang.ktbase.network.ResponseData
import com.google.gson.JsonArray
import com.yang.ktbase.network.RetrofitApi
import com.yang.ktbase.vm.BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

/**
 * 示例代码
 */
class NetExpViewModel : BaseViewModel() {


    private val _titleData = MutableStateFlow<ResponseData<JsonArray>>(ResponseData())
    val titleData: StateFlow<ResponseData<JsonArray>> = _titleData.asStateFlow()


    suspend fun getBanner1() {
        _titleData.value = RetrofitApi.api.getBanner()
    }

    suspend fun getBanner2(): ResponseData<JsonArray> {
        return RetrofitApi.api.getBanner()
    }

}