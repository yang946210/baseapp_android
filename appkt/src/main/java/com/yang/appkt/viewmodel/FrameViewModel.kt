package com.yang.appkt.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.JsonArray
import com.yang.ktbase.network.RetrofitApi
import kotlinx.coroutines.launch

class FrameViewModel : ViewModel() {

    private var loginData = MutableLiveData<JsonArray?>()

    fun getData(){
        viewModelScope.launch {
            var res=RetrofitApi.get.getBanner()
        }
    }
}


