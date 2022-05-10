package com.yang.appkt.menu.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HomeViewModel : ViewModel() {

    var text=MutableLiveData<String>().apply {
        this.value="just init data"
    }

}