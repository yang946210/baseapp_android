package com.yang.appkt.menu

import android.os.Bundle
import androidx.lifecycle.LiveDataScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.JsonObject
import com.yang.appkt.adapter.BannerJsonAdapter
import com.yang.appkt.databinding.ActivityRecyclerBinding
import com.yang.appkt.viewmodel.FrameViewModel
import com.yang.ktbase.LiveDataBus
import com.yang.ktbase.base.BaseActivity
import com.yang.ktbase.ext.logD
import java.util.concurrent.ConcurrentHashMap


class RecyclerActivity : BaseActivity<FrameViewModel, ActivityRecyclerBinding>() {

    private val bannerAdapter:BannerJsonAdapter by lazy {
        BannerJsonAdapter()
    }

    override fun initView(savedInstanceState: Bundle?) {

        binding.rvBanner.run {
            adapter=bannerAdapter
            layoutManager=LinearLayoutManager(context)
        }

        viewModel.loginData.observe(this){
            if (it != null) {
                bannerAdapter.data=it
            }
        }

        viewModel.getData()
        LiveDataBus.with<String>("test").observe(this) {
            it.logD()
        }
    }

}