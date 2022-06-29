package com.yang.appkt.viewmodel

import androidx.lifecycle.MutableLiveData
import com.yang.ktbase.base.BaseViewModel

class LifecycleViewModel: BaseViewModel() {

    var data = MutableLiveData<String>()
}