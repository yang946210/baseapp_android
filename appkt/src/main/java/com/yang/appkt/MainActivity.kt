package com.yang.appkt

import android.annotation.SuppressLint
import android.content.Context
import android.content.Context.LOCATION_SERVICE
import android.content.Intent
import android.location.Location
import android.location.LocationManager
import android.net.Uri
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
                val intent=Intent(Intent.ACTION_VIEW, Uri.parse("com-cntaiping-life-tpsl-pre://?outerDataSource=\"GFSecurities\"&authCode=\"EBE6324E19BA36C5EF3F2A60C33D8542\"&busiNums=\"010015348506001\"&recordStatus=\"W\""));
                startActivity(intent)
            }

            tvNdk.text= "${isLocationProviderEnabled(applicationContext)}+${getLocationByNetWork(applicationContext)}"
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


fun isLocationProviderEnabled(context: Context): Boolean {
    var result = false
    val locationManager = context
        .getSystemService(LOCATION_SERVICE) as LocationManager ?: return false
    if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager
            .isProviderEnabled(LocationManager.NETWORK_PROVIDER)
    ) {
        result = true
    }
    return result
}

@SuppressLint("MissingPermission")
private fun getLocationByNetWork(context: Context): Location? {
    var location: Location? = null
    val locationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager

    try {
        if (locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
            location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER)
        }
    } catch (ex: SecurityException) {
        ex.printStackTrace()
    }
    return location
}




