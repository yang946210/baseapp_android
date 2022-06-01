package com.yang.appkt.menu

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import coil.load
import com.yang.appkt.databinding.ActivityCoroutinesBinding
import com.yang.ktbase.ext.logD
import kotlinx.coroutines.*

class CoroutinesActivity : AppCompatActivity(),CoroutineScope by MainScope() {

    private lateinit var binding: ActivityCoroutinesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCoroutinesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
    }


    private fun init() {
        binding.tvRunBlockingStart.setOnClickListener { start() }
        binding.tvLaunchStart.setOnClickListener { launchStart() }
        binding.tvAsyncStart.setOnClickListener { asyncStart() }
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


        var s3=lifecycleScope.launch {
            "end 3".logD()
            val a1=
                withContext(Dispatchers.Default) { getResult1() }
            val a2=
                withContext(Dispatchers.Default) { getResult1() }

            "end 3$a1======$a2".logD()
        }
    }

    /**
     * launch异步启动，
     * 不阻塞线程
     */
    private fun launchStart() {
    }

    /**
     * async,与launch返回值不通
     * 不阻塞线程,支持并发
     */
    private fun asyncStart() {
        binding.ivTitle.load("https://t7.baidu.com/it/u=2168645659,3174029352&fm=193&f=GIF")
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


