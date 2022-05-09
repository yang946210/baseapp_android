package com.yang.appkt.menu

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import com.yang.appkt.R
import kotlinx.coroutines.*
import kotlin.system.measureTimeMillis

class CoroutinesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coroutines)
        //findViewById<TextView>(R.id.tv_launchStart).setOnClickListener { launchStart() }
        //findViewById<TextView>(R.id.tv_asyncStart).setOnClickListener { asyncStart() }
        //findViewById<TextView>(R.id.tv_runBlockingStart).setOnClickListener { runBlockingStart() }

    }


    /**
     * launch异步启动，
     * 不阻塞线程
     */
    private fun launchStart() {
        var jobA1=GlobalScope.launch(context=Dispatchers.Main) {
            Thread.sleep(1000)
            Log.d("=====coroutines", "当前线程11${Thread.currentThread()}")
        }
        var job2=GlobalScope.launch {
            Thread.sleep(1000)
            Log.d("=====coroutines", "当前线程12${Thread.currentThread()}")
        }

        Log.d("=====coroutines", "当前线程13${Thread.currentThread()}")
    }

    /**
     * launch异步启动,与launch返回值不通
     * 不阻塞线程,支持并发
     */
    private fun asyncStart() {
        GlobalScope.launch {
            val deferred1 =GlobalScope.async {
                getResult1()
            }
            val deferred2 = GlobalScope.async {
                getResult2()
            }
            val result=deferred1.await()+deferred2.await()
            Log.d("============async",result)
        }

    }

    /**
     * runBlocking启动，阻塞线程
     * 返回泛型，最后一行时返回
     */
     private fun runBlockingStart()=runBlocking {
        val time = measureTimeMillis {
            Thread.sleep(1000)
            var jobA1 = launch { Log.d("=====coroutines", "当前线程31${Thread.currentThread()}") }
            Thread.sleep(1000)
            var jobA2 = launch { Log.d("=====coroutines", "当前线程32${Thread.currentThread()}") }
            Thread.sleep(1000)
            var jobA3 = launch { Log.d("=====coroutines", "当前线程33${Thread.currentThread()}") }
        }
        launch { Log.d("=====coroutines", "当前线程34${Thread.currentThread()}") }
    }

    private fun getResult1():String{
        Thread.sleep(2000)
        return "result 1"
    }


    private fun getResult2():String{
        Thread.sleep(1000)
        return "result 2"
    }


}


