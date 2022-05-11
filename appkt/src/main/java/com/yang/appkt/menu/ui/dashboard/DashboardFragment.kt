package com.yang.appkt.menu.ui.dashboard

import OkHttpApi
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.yang.appkt.databinding.FragmentDashboardBinding
import com.yang.base.HttpApi
import com.yang.base.IHttpCallback
import kotlin.math.log

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
            var httpApi:HttpApi=OkHttpApi()
            httpApi.get(map,"api.php",object :IHttpCallback{
                override fun onSuccess(data: Any) {
                    Log.d("=success",data.toString())
                }

                override fun onFail(error: Any) {
                    Log.d("=fail",error.toString())
                }

            })
        }


        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}