package com.yang.appkt.menu.ui.vm

import android.os.Bundle
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.yang.appkt.databinding.FragmentModelBinding
import com.yang.appkt.ext.logLifeCycle
import com.yang.appkt.viewmodel.VmViewModel
import com.yang.ktbase.base.BaseFragment

class ViewModelFragment : BaseFragment<ViewModelViewModel, FragmentModelBinding>() {

    /**
     * 获取父activityViewModel 实现数据共享
     */
    val vmViewModel:VmViewModel by activityViewModels()

    val vmViewModel1:ViewModelViewModel by viewModels()

    override fun initView(savedInstanceState: Bundle?) {
        logLifeCycle()
    }
}