package com.yang.appkt

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatDialog

class MainActivity : AppCompatActivity() {

    private lateinit var tvCoroutines:TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setView()

    }

    private fun setView(){
        tvCoroutines=findViewById(R.id.tv_Coroutines)
        tvCoroutines.setOnClickListener { coroutinesButton() }
    }



    private fun coroutinesButton(){
        startActivity(Intent(this@MainActivity, CoroutinesActivity::class.java))
    }


}