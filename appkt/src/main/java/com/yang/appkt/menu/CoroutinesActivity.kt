package com.yang.appkt.menu

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import coil.load
import com.yang.appkt.databinding.ActivityCoroutinesBinding
import com.yang.ktbase.LiveDataBus
import com.yang.ktbase.ext.logD
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.flow

class CoroutinesActivity : AppCompatActivity(), CoroutineScope by MainScope() {

    private lateinit var binding: ActivityCoroutinesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCoroutinesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
    }


    private fun init() {
        launch { }//CoroutineScope by MainScope() 将此activity声明为一个作用域
        binding.tvRunBlockingStart.setOnClickListener { start() }
        binding.tvLaunchStart.setOnClickListener { launchStart() }
        binding.tvAsyncStart.setOnClickListener { flow() }

        LiveDataBus.with<String>("test1").observe(this){
            it.logD()
        }
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
        var s=flow<String> {
            emit("send")
        }

        lifecycleScope.launch{
            simple().collect{
                it.logD()
            }

            s.collect{
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




}


