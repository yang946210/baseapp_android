package com.yang.appkt.menu

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.yang.appkt.BaseInfo
import com.yang.appkt.R

class InfoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info)
        dataClass()
    }


    /**
     * data函数
     */
    private fun dataClass() {
        BaseInfo.dataClassTest()
        BaseInfo.objectTest()
    }


}

