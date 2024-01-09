package com.example.jetpack.activity

import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.example.lib_jetpack.databinding.ActivityFlowBinding
import com.yang.ktbase.activity.BaseActivity
import com.yang.ktbase.util.logD
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class FlowActivity : BaseActivity<ActivityFlowBinding>() {
    var flow1:Flow<Int>?=null
    var flow2:Flow<Int>?=null

    override fun initView(savedInstanceState: Bundle?) {
        createFlow()

        mBinding.apply {
            btnEmit1.setOnClickListener {
                lifecycleScope.launch {
                    "${Thread.currentThread().name}==>btnEmit1".logD()
                    flow1?.collect{
                        "===btnEmit11===>"+it.toString().logD()
                    }
                    Thread.sleep(2000)
                    flow1?.collect{
                        "===btnEmit12===>"+it.toString().logD()
                    }
                }
            }
            btnEmit2.setOnClickListener {
                lifecycleScope.launch {
                    "${Thread.currentThread().name}==>btnEmit2".logD()
                    flow2?.collect{
                        "===btnEmit21===>"+it.toString().logD()
                    }
                    Thread.sleep(2000)
                    flow2?.collect{
                        "===btnEmit22===>"+it.toString().logD()
                    }
                }
            }
        }
    }


    //flow构建方式
    private fun createFlow(){
        flow1 = flow { emit(7) }
        flow1 = flowOf(1, 2, 3)
        flow1 = listOf(4, 5, 6).asFlow()


        //channelFlow创建热流
        flow2 = channelFlow {
            for (i in 11..14){
                send(i)
            }
        }

        var stateFlow= MutableStateFlow("初始值")

        var sharedFlow= MutableSharedFlow<Int>(
            replay = 2, //粘性数，订阅后能收到之前的几个消息
            extraBufferCapacity = 2,
            onBufferOverflow = BufferOverflow.SUSPEND) //背压策略
    }

    //flow冷流，channelFlow热流
    suspend fun emitCold() {
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