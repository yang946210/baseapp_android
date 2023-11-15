package com.yang.appkt.menu.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yang.ktbase.ext.logD
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    private val stateFlow= MutableStateFlow(0)

    fun add (){
        stateFlow.value++
    }

    fun  reduce(){
        stateFlow.value--
    }

    fun leakTest(){
        viewModelScope.launch {
            while (true){
                Thread.currentThread().toString().logD("=========homeViewModel")
                delay(1000)
                stateFlow.value++
            }
        }
        Thread.currentThread().toString().logD("=========homeViewModel1111")
    }

}