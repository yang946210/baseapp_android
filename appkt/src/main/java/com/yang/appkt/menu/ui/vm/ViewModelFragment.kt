package com.yang.appkt.menu.ui.vm

import android.os.Bundle
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.yang.appkt.databinding.FragmentNotificationsBinding
import com.yang.appkt.viewmodel.VmViewModel
import com.yang.ktbase.base.BaseFragment

class ViewModelFragment : BaseFragment<ViewModelViewModel, FragmentNotificationsBinding>() {

    /**
     * 获取父activityViewModel 实现数据共享
     */
    val vmViewModel:VmViewModel by activityViewModels()

    val vmViewModel1:ViewModelViewModel by viewModels()

    override fun initView(savedInstanceState: Bundle?) {
        val textView: TextView = binding.textNotifications
        viewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
    }
}