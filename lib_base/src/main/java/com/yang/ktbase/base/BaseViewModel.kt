package com.yang.ktbase.base

import androidx.lifecycle.ViewModel
import com.yang.ktbase.util.logD


/**
 * ViewModel基类
 */
abstract class BaseViewModel : ViewModel(){
    override fun onCleared() {
        super.onCleared()
        "ViewModel onCleared :${this::class.java.simpleName}".logD()
    }

}