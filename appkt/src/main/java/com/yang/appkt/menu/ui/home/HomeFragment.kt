package com.yang.appkt.menu.ui.home

import android.os.Bundle
import android.widget.TextView
import com.yang.appkt.databinding.FragmentHomeBinding
import com.yang.ktbase.base.BaseFragment

class HomeFragment : BaseFragment<HomeViewModel,FragmentHomeBinding>() {

    override fun initView(savedInstanceState: Bundle?) {
        val textView: TextView = binding.textHome
        viewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
    }
}