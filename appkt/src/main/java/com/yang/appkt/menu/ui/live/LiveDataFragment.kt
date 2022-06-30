package com.yang.appkt.menu.ui.live

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.yang.appkt.databinding.FragmentDashboardBinding
import com.yang.ktbase.base.BaseBindFragment

class LiveDataFragment : BaseBindFragment<FragmentDashboardBinding>() {

    private val dashboardViewModel:LiveDataViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }


    override fun initView(savedInstanceState: Bundle?) {
        val textView: TextView = binding.textDashboard

        val dashboardViewModel = ViewModelProvider(this).get(LiveDataViewModel::class.java)

        dashboardViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
    }
}