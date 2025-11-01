package com.yang.appkt

import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.yang.appkt.databinding.ActivityVmBinding
import com.yang.ktbase.base.BaseActivity


class MainActivity : BaseActivity<ActivityVmBinding>() {

    override fun bindView(savedInstanceState: Bundle?) {
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




}