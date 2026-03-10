package com.yang.app.ui.fl_exp

import com.yang.app.network.RetrofitApi
import com.yang.ktbase.base.BaseViewModel
import com.yang.ktbase.network.Banner

class FlViewModule : BaseViewModel() {

    val banner=stateOf<List<Banner>>()

    fun getData(){
        request(banner){
            RetrofitApi.api.getBanner()
        }
    }

}