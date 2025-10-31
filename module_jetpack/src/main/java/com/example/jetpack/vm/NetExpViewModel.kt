package com.example.jetpack.vm

import com.google.gson.JsonArray
import com.yang.ktbase.net.RetrofitApi
import com.yang.ktbase.vm.BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

/**
 * 示例代码
 */
class NetExpViewModel : BaseViewModel() {

    private val _titleData = MutableStateFlow(JsonArray())
    val titleData: StateFlow<JsonArray> = _titleData.asStateFlow()

    fun getUserInfo() {
        launchRequest(
            reqCall = { RetrofitApi.api.getBanner() },
            onSuccess = { user ->
                _titleData.value=user;
            },
            onError = {
                // 额外错误处理
            }
        )
    }

}