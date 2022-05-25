package com.yang.appkt.viewmodel

import androidx.lifecycle.MutableLiveData
import com.google.gson.JsonArray
import com.yang.ktbase.base.BaseViewModel
import com.yang.appkt.net.RetrofitApi
import com.yang.ktbase.ext.request

class FrameViewModel : BaseViewModel() {

    var loginData = MutableLiveData<JsonArray?>()

    fun getData() {
        request({RetrofitApi.get.getAnt(0)},{loginData.value= it?.get("datas") as JsonArray? },{})
    }

}




