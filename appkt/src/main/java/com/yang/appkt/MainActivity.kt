package com.yang.appkt

import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.yang.appkt.databinding.ActivityVmBinding
import com.yang.ktbase.activity.BaseActivity


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

    /**
     * viewModel构造创建测试
     */
    private fun singleLiveDataTest() {
//        val viewModel1 by lazy { ViewModelProvider(this)[VmViewModel::class.java] }
//        val viewModel2 by viewModels<VmViewModel>()
//        val viewModel3: VmViewModel by viewModels()
//        val viewModel4: LifecycleViewModel by viewModels()
//
//        viewModel.s = "=======s"
//        viewModel1.s = "======s1"
//        viewModel2.s = "======s2"
//        viewModel3.s = "======s3"
//        viewModel4.s = "======s4"
//        ("=====\n" +
//                "viewModel0=${viewModel.s}\n" +
//                "viewModel1=${viewModel1.s}\n" +
//                "viewModel2=${viewModel2.s}\n" +
//                "viewModel3=${viewModel3.s}\n" +
//                "viewModel4=${viewModel4.s}").logD()

        //结果打印
        //    viewModel0=======s3
        //    viewModel1=======s3
        //    viewModel2=======s3
        //    viewModel3=======s3
        //    viewModel4=======s4
    }

}