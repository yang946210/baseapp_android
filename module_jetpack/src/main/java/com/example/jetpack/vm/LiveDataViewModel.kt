package com.example.jetpack.vm

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import com.yang.ktbase.vm.BaseViewModel


class LiveDataViewModel : BaseViewModel() {


    /**
     * 一般liveDara
     */
     val mlLiveData1 =MutableLiveData<String>()

    /**
     * 一般liveDara
     */
     val mlLiveData2= MutableLiveData<Int>()

    /**
     * 可以合并接收多个livedata的livedata
     */
     val mdLiveData= MediatorLiveData<String>()


}