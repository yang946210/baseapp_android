package com.yang.appkt.menu.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import com.yang.appkt.databinding.FragmentDashboardBinding
import com.yang.appkt.menu.bean.LoginData

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
        val textView: TextView = binding.textDashboard

        dashboardViewModel=ViewModelProvider(this).get(DashboardViewModel::class.java)
        dashboardViewModel.login.observe(viewLifecycleOwner) { textView.text = it.name }

        binding.btChange.setOnClickListener {
            dashboardViewModel.login.postValue( LoginData("li",23,"girl"))
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}