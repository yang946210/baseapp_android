package com.yang.app.ui.net_exp

import com.yang.ktbase.network.Banner
import com.yang.app.network.RetrofitApi
import com.yang.ktbase.base.BaseViewModel

class NetRequestModel : BaseViewModel() {

//    private val _bannerState = stateOf<List<Banner>>()
//    val bannerState=_bannerState.asStateFlow()

    val bannerState = stateOf<List<Banner>>()

    fun getBanner() {
        request(bannerState) {
            RetrofitApi.api.getBanner()
        }
    }
}
