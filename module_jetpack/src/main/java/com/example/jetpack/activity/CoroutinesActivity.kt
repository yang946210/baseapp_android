package com.example.jetpack.activity

import android.os.Bundle
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import coil.load
import com.example.lib_jetpack.databinding.ActivityCoroutinesBinding
import com.yang.appkt.viewmodel.CoroutineModel
import com.yang.ktbase.LiveDataBus
import com.yang.ktbase.base.BaseActivity
import com.yang.ktbase.ext.logD
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.flow

class CoroutinesActivity : BaseActivity<CoroutineModel, ActivityCoroutinesBinding>(),
    CoroutineScope by MainScope() {

    var tv:TextView?=null


    override fun initView(savedInstanceState: Bundle?) {




        GlobalScope.launch {
            for (index in 1..10000) {
                delay(1000)
                "000GlobalScope ===${Thread.currentThread()}=== ${System.currentTimeMillis()}".logD()
            }
        }
        launch {
            for (index in 1..10000) {
                delay(1000)
                launch(Dispatchers.Default){
                    "111main ===${Thread.currentThread()}=== ${System.currentTimeMillis()}".logD()
                }
                "111main ===${Thread.currentThread()}=== ${System.currentTimeMillis()}".logD()
            }
        }

        lifecycleScope.launch {
            for (index in 1..10000) {
                delay(1000)
                "222viewModelScope===${Thread.currentThread()}=== ${System.currentTimeMillis()}".logD()
            }
        }

        viewModel.viewModelScope.launch {
            for (index in 1..10000) {
                delay(1000)
                "333viewModelScope===${Thread.currentThread()}=== ${System.currentTimeMillis()}".logD()
            }
        }
        binding.tvRunBlockingStart.setOnClickListener { start() }
        binding.tvLaunchStart.setOnClickListener { launchStart() }
        binding.tvAsyncStart.setOnClickListener { flow() }
    }


    object dataClass{
        var name="name"
    }


    /**
     * 启动协程
     */
    private fun start() {



        val s1 = runBlocking {
            getResult1().logD()
            getResult2().logD()
            "end 1 ====${Thread.currentThread()}".logD()
        }
        "$s1".logD()

        //=====================

        val s2 = lifecycleScope.launch(Dispatchers.IO) {
            for (index in 1..10) {
                launch {
                    "end 2====$index $this".logD()
                }
            }
        }
        "end 21==== $s2".logD()


        var s3 = lifecycleScope.launch {
            "end 3".logD()
            val a1 =
                withContext(Dispatchers.Default) { getResult1() }
            val a2 =
                withContext(Dispatchers.Default) { getResult1() }

            "end 3$a1======$a2".logD()
        }
    }

    /**
     * launch异步启动，
     * 不阻塞线程
     */
    private fun launchStart() {
        binding.ivTitle.load("https://t7.baidu.com/it/u=2168645659,3174029352&fm=193&f=GIF")
        LiveDataBus.with<String>("test").postData("2222")
    }

    /**
     * flow
     */
    private fun flow() {
        val s = flow<String> { emit("send") }

        lifecycleScope.launch {
            simple().collect {
                it.logD()
            }

            s.collect {
                it.logD()
            }
        }

        (1..4).asFlow()


    }

    private fun simple() = flow<String> {
        for (i in 1..20) {
            emit("flow emit send${i}")
        }
    }

    private suspend fun getResult1(): String {
        delay(2000)
        return "result 1${Thread.currentThread()}"
    }

    private suspend fun getResult2(): String {
        delay(1000)
        return "result 2 ${Thread.currentThread()}"
    }

    private suspend fun getImage(imageUrl: String) = withContext(Dispatchers.IO) {

    }

    override fun onDestroy() {
        super.onDestroy()
    }
}


