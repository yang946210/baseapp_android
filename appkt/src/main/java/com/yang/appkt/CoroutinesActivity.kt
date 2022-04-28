package com.yang.appkt

import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class CoroutinesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coroutines)
        findViewById<TextView>(R.id.tv_start).setOnClickListener { start() }
        var pm:PackageManager=packageManager
    }


    private fun start(){
        var runBlockingJob= runBlocking {
            Log.d("runBlocking", "启动一个协程")
        }
        Log.d("runBlockingJob", "$runBlockingJob")


        val launchJob = GlobalScope.launch{
            Log.d("launch", "启动一个协程")
        }
        Log.d("launchJob", "$launchJob")

        val asyncJob = GlobalScope.async{
            Log.d("async", "启动一个协程")
            "我是返回值"
        }
        Log.d("asyncJob", "$asyncJob")

    }
}