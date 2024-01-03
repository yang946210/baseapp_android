package com.example.jetpack.viewmodel

import com.yang.ktbase.base.BaseViewModel
import com.yang.ktbase.extorutil.logD
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map

class FlowViewModel : BaseViewModel() {

    //flow构建方式
    var flow1 = flowOf(1, 2, 3, 4, 5, "6")
    var flow2 = listOf("a", "b", "c", "d").asFlow()
    var flow3 = flow { emit(1) }
    var flow4 = channelFlow { send(1) }

    //flow冷流，channelFlow热流
    suspend fun emit1() {
        "emit1 thread==>${Thread.currentThread().name}==>".logD()
        flow {
            emit(2)
        }.map {
            it * 2
        }.flowOn(Dispatchers.IO).collect {
            "collect thread==>${Thread.currentThread().name},value==>${it}".logD()
        }
    }
}