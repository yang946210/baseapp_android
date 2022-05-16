package com.yang.appkt.menu.ui.dashboard

import IHttpCallback
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.yang.appkt.databinding.FragmentDashboardBinding

class DashboardFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private lateinit var dashboardViewModel: DashboardViewModel


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDashboardBinding.inflate(inflater, container, false)

        val root: View = binding.root

        dashboardViewModel=ViewModelProvider(this).get(DashboardViewModel::class.java)

        dashboardViewModel.login.observe(viewLifecycleOwner) { binding.textDashboard.text = it.name }

        binding.dashViewModel=dashboardViewModel

        binding.btChange.setOnClickListener {
            var map:Map<String,String> = mapOf("key" to "free","appid" to "0","msg" to "hello")
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}