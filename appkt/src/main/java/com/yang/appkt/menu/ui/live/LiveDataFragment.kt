package com.yang.appkt.menu.ui.live

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.yang.appkt.databinding.FragmentDataBinding
import com.yang.appkt.ext.logLifeCycle
import com.yang.ktbase.base.BaseBindFragment

class LiveDataFragment : BaseBindFragment<FragmentDataBinding>() {


    private val dashboardViewModel: LiveDataViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun initView(savedInstanceState: Bundle?) {
        logLifeCycle()
    }
}