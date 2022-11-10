package com.yang.appkt

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import com.yang.appkt.databinding.ActivityMainBinding
import com.yang.appkt.menu.*
import com.yang.ktbase.base.BaseBindActivity
import kotlinx.coroutines.*


class MainActivity : BaseBindActivity<ActivityMainBinding>(), CoroutineScope by MainScope() {

    var textView:TextView?=null
    override fun initView(savedInstanceState: Bundle?) {

        binding.apply {
            tvJavaBase.setOnClickListener {
                startActivity(Intent(this@MainActivity, JavaMemoryActivity::class.java))
            }
            tvActivity.setOnClickListener {
                startActivity(Intent(this@MainActivity, ActivityActivity::class.java))
            }
            tvLaunch.setOnClickListener {
                startActivity(Intent(this@MainActivity, LaunchActivity::class.java))
            }
            tvHandler.setOnClickListener {
                startActivity(Intent(this@MainActivity, HandlerActivity::class.java))
            }
            tvWebView.setOnClickListener {
                startActivity(Intent(this@MainActivity, WebViewActivity::class.java))
            }  
            tvRoom.setOnClickListener {
                startActivity(Intent(this@MainActivity, RoomActivity::class.java))
            }
            tvLifecycle.setOnClickListener {
                startActivity(Intent(this@MainActivity, LifecycleActivity::class.java))
            }
            tvLiveData.setOnClickListener {
                startActivity(Intent(this@MainActivity, VmActivity::class.java))
            }
            tvFrame.setOnClickListener {
                startActivity(Intent(this@MainActivity, RecyclerActivity::class.java))
            }
            tvCoroutines.setOnClickListener {
                startActivity(Intent(this@MainActivity, CoroutinesActivity::class.java))
            }
            tvBluetooth.setOnClickListener {
                startActivity(Intent(this@MainActivity, BluetoothActivity::class.java))
            }
            tvBluetooth.text=android.os.Build.MODEL
            tvNdk.setOnClickListener {
                //startActivity(Intent(this@MainActivity, NdkActivity::class.java))
                textView.notNull {
                    textView= TextView(this@MainActivity)
                    textView!!.text="11"
                }
                textView.apply {
                    Toast.makeText(this@MainActivity,textView?.text,Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        cancel()
    }
}

/**
 * 判断是否为空 并传入相关操作
 */
inline fun <reified T> T.notNull(nullAction: () -> Unit = {}): T {
    if (this == null) {
        nullAction.invoke()
    }
    return this
}