package com.yang.appkt.menu

import android.os.Bundle
import com.yang.appkt.databinding.FragmentDataBinding
import com.yang.appkt.ext.logLifeCycle
import com.yang.ktbase.base.BaseBindFragment

class LiveDataFragment : BaseBindFragment<FragmentDataBinding>() {

    override fun initView(savedInstanceState: Bundle?) {
        logLifeCycle()
    }
}