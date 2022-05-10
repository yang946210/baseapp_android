package com.yang.appkt.menu.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import com.yang.appkt.databinding.FragmentHomeBinding
import java.lang.Exception
import java.nio.channels.Selector
import java.nio.channels.spi.SelectorProvider

/**
 * 一些liveData+viewModel的例子
 */
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    private val binding get() = _binding!! //!!为null就抛异常

    private lateinit var homeViewModel:HomeViewModel

    /**
     * 一个计数器，点击按钮就++
     */
    private var intDex=0

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
//        activity?.run {
//            var modle=ViewModelProvider(this).get(ActivityViewModle::class.java)
//        }?:throw  Exception("activity invalid")

    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root


        /**
         * 获取实例，并绑定生命周期
         */
        homeViewModel=ViewModelProvider(this).get(HomeViewModel::class.java)

        /**
         * 建立订阅关系
         */
        homeViewModel.text.observe(this.viewLifecycleOwner){
            binding.textHome.text=it
        }

        /**
         * 跟新数据
         */
        binding.btAdd.setOnClickListener { homeViewModel.text.value=intDex++.toString() }
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}