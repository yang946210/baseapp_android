package com.yang.appkt

import android.content.Intent
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.jetpack.activity.WebViewVmActivity
import com.google.zxing.integration.android.IntentIntegrator
import com.yang.appkt.databinding.ActivityVmBinding
import com.yang.ktbase.base.BaseActivity


class MainActivity : BaseActivity<ActivityVmBinding>() {

    override fun initView(savedInstanceState: Bundle?) {
        initNavigation()
    }

    /**
     * 初始化导航
     */
    private fun initNavigation() {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment_activity_vm) as NavHostFragment
        val navController = navHostFragment.navController
        mBinding.navView.apply {
            itemIconTintList=null
            setupWithNavController(navController)
        }
    }

    // 接收扫码结果
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        val result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
        if (result != null) {
            val intent=Intent(this,WebViewVmActivity::class.java);
            intent.putExtra("url",result.contents)
            startActivity(intent)

        }
    }

}