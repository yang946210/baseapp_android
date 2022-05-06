package com.yang.appkt

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.yang.appkt.menu.CoroutinesActivity
import com.yang.appkt.menu.InfoActivity

class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setView()
    }

    private fun setView(){
        findViewById<TextView>(R.id.tv_info).setOnClickListener { infoButton() }
        findViewById<TextView>(R.id.tv_Coroutines).setOnClickListener { coroutinesButton() }
    }



    /**
     * 协程
     */
    private fun infoButton(){
        startActivity(Intent(this@MainActivity, InfoActivity::class.java))
    }

    /**
     * 协程
     */
    private fun coroutinesButton(){
        startActivity(Intent(this@MainActivity, CoroutinesActivity::class.java))
    }




}