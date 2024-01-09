package com.yang.ktbase.vm

import androidx.lifecycle.ViewModel
import com.yang.ktbase.network.netutil.Response
import com.yang.ktbase.util.logD


/**
 * ViewModel基类
 */
abstract class BaseViewModel : ViewModel(),Response{


    override fun onCleared() {
        super.onCleared()
        "ViewModel onCleared :${this::class.java.simpleName}".logD()
    }


}