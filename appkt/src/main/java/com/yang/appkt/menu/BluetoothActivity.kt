package com.yang.appkt.menu

import android.Manifest
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothManager
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.blankj.utilcode.util.ToastUtils
import com.yang.appkt.databinding.ActivityBluetoothBinding
import com.yang.ktbase.base.BaseBindActivity


class BluetoothActivity : BaseBindActivity<ActivityBluetoothBinding>() {

    private val REQUEST_ENABLE_BT:Int=100001

    private lateinit var mBluetoothAdapter:BluetoothAdapter

    override fun initView(savedInstanceState: Bundle?) {

        binding.btInit.setOnClickListener {
            //废弃
            //var mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

            val bluetoothManager = getSystemService(Context.BLUETOOTH_SERVICE) as BluetoothManager
            mBluetoothAdapter  = bluetoothManager.adapter.also {
                if (it==null){
                    ToastUtils.showLong("不支持蓝牙")
                }
            }
        }

        /**
         * 方式一：暴力开启bluetooth
         */
        binding.btStart.setOnClickListener {
            mBluetoothAdapter.enable();
        }

        /**
         * 方式二：有弹框进行提示，隐式启动Intent
         */
        binding.btStart2.setOnClickListener {
            if (!mBluetoothAdapter.isEnabled){
                val enableBtIntent = Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)
                startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT)
            }
        }

        /**
         * 扫描bluetooth
         */
        binding.btSearch.setOnClickListener {
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private fun initPermission() {
        if (ContextCompat.checkSelfPermission(this@BluetoothActivity, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            //判断是否需要向用户解释为何要此权限
            if (!ActivityCompat.shouldShowRequestPermissionRationale(this@BluetoothActivity, Manifest.permission.READ_CONTACTS)) {

                showMessageOKCancel("223"
                ) { _, _ -> ActivityCompat.requestPermissions(this@BluetoothActivity
                    , arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION),1111)
                }
                return;
            }
            //请求权限
            requestPermissions( arrayOf(Manifest.permission.WRITE_CONTACTS), 1111);
        }
    }

    private fun showMessageOKCancel(message:String, okListener:  DialogInterface.OnClickListener) {
             AlertDialog.Builder(this@BluetoothActivity)
            .setMessage(message)
            .setPositiveButton("OK", okListener)
            .setNegativeButton("Cancel", okListener)
            .create()
            .show()
    }


    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            //用户允许改权限，0表示允许，-1表示拒绝 PERMISSION_GRANTED = 0， PERMISSION_DENIED = -1
            //这里进行授权被允许的处理
            //可以弹个Toast，感谢用户爸爸允许了。
            ToastUtils.showLong( "谢谢爸爸")
        } else {
            //这里进行权限被拒绝的处理，就跳转到本应用的程序管理器
            ToastUtils.showLong( "垃圾")
        }
    }




    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode==REQUEST_ENABLE_BT){
            var data=data
        }
    }
}


