package com.yang.appkt.vm


import com.blankj.utilcode.util.ToastUtils
import com.yang.ktbase.network.Banner
import com.yang.ktbase.network.RetrofitApi
import com.yang.ktbase.vm.BaseViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow

class NetRequestModel : BaseViewModel() {

    private val _titleData = MutableSharedFlow<List<Banner>>()
    val titleData = _titleData.asSharedFlow()

    fun getUserInfo() {
        launch(showLoading = false){
            request { RetrofitApi.api.getBanner() }
            success {
                _titleData.emit(it)
            }
            error {
                ToastUtils.showLong("如果有错误就写${it.message ?: it}")
            }

        }
    }

}
