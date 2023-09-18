package com.yang.appkt.menu

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.v2ray.ang.databinding.ActivityRecyclerBinding
import com.yang.appkt.adapter.BannerJsonAdapter
import com.yang.appkt.viewmodel.FrameViewModel
import com.yang.ktbase.base.BaseActivity


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
    }

}