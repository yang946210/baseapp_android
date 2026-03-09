package com.yang.appkt.vm

import com.yang.ktbase.network.Banner
import com.yang.ktbase.network.RetrofitApi
import com.yang.ktbase.base.BaseViewModel
import com.yang.ktbase.network.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class NetRequestModel : BaseViewModel() {

    private val _bannerState = MutableStateFlow<UiState<List<Banner>>>(UiState.Idle)
    val bannerState = _bannerState.asStateFlow()

    fun getBanner() {
        request(_bannerState) { RetrofitApi.api.getBanner() }
    }
}
