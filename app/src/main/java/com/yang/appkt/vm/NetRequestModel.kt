package com.yang.appkt.vm


import com.blankj.utilcode.util.ToastUtils
import com.google.gson.JsonArray
import com.yang.ktbase.net.RetrofitApi
import com.yang.ktbase.vm.BaseViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow

class NetRequestModel : BaseViewModel() {

    private val _titleData = MutableSharedFlow<JsonArray>(replay = 1)
    val titleData = _titleData.asSharedFlow()

    fun getUserInfo() {
        launch{
            request { RetrofitApi.api.getBanner() }
            success {
                _titleData.tryEmit(it)
            }
            error {
                ToastUtils.showLong("如果有错误就写${it.message ?: it}")
            }
        }
    }

}
