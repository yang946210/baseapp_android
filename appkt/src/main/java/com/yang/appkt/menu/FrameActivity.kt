package com.yang.appkt.menu

import android.os.Bundle
import androidx.activity.viewModels
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import com.yang.appkt.databinding.ActivityFrameBinding
import com.yang.appkt.viewmodel.FrameViewModel

class FrameActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFrameBinding

    private val viewModel: FrameViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityFrameBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        binding.contentFrame.textViewGetLog.setOnClickListener {
            viewModel.getData()
        }


        binding.fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG).show()
        }
    }

}