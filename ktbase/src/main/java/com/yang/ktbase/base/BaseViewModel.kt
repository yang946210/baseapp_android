package com.yang.ktbase.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.cancel

abstract class BaseViewModel : ViewModel(){
    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }
}