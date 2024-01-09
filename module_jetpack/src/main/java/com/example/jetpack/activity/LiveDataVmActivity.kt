package com.example.jetpack.activity

import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.blankj.utilcode.util.TimeUtils
import com.example.jetpack.vm.LiveDataViewModel

import com.example.lib_jetpack.databinding.ActivityLiveDataBinding
import com.yang.ktbase.activity.BaseVmActivity
import com.yang.ktbase.util.logD
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.Date

class LiveDataVmActivity : BaseVmActivity<LiveDataViewModel,ActivityLiveDataBinding>(){

    private var index=1

    override fun initView(savedInstanceState: Bundle?) {

        mViewModel.apply {
            mlLiveData1.observe(this@LiveDataVmActivity){
                "md1 get $it".logD()
            }
            mlLiveData2.observe(this@LiveDataVmActivity){
                "md2 get $it".logD()
            }
            mdLiveData.observe(this@LiveDataVmActivity){
                "md10086 get $it".logD()
            }


            //合并子livedata一起回调
            mdLiveData.addSource(mlLiveData1){
                mdLiveData.value=it
            }
            mdLiveData.addSource(mlLiveData2){
                mdLiveData.value="$it"
            }
        }



        mBinding.apply {
            btnPost.setOnClickListener {
                lifecycleScope.launch (Dispatchers.IO){
                    //post可以在任何线程
                    mViewModel.mlLiveData1.postValue(TimeUtils.date2String(Date()))
                }
            }
            btnSet.setOnClickListener {
                lifecycleScope.launch (Dispatchers.Main){
                    //set只能在主线程
                    mViewModel.mlLiveData2.value=+index
                }
            }
        }

    }
}