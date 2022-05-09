package com.yang.appkt.menu

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.yang.appkt.BaseInfo
import com.yang.appkt.R

class InfoActivity : AppCompatActivity() {

    private lateinit var tvTextView:TextView

    private val simpleLiveData= MutableLiveData<String>()

    private val mediatorLiveData= MediatorLiveData<String>()



    private var int=1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info)
        findViewById<Button>(R.id.bt_add).setOnClickListener {
            simpleLiveData.value=int++.toString()
        }
        dataClass()
        liveData()

    }


    /**
     * data函数
     */
    private fun dataClass() {
        BaseInfo.infoTest()
        BaseInfo.funTest()
        BaseInfo.dataClassTest()
        BaseInfo.objectTest()
    }

    private fun liveData(){

        tvTextView=findViewById(R.id.tv_textView)
        val observer=Observer<String>{
            tvTextView.text=it
        }


        simpleLiveData.observe(this,observer)
        mediatorLiveData.observe(this,observer)//可见听多个数据源
    }


}


