package com.example.jetpack.vm

import com.aisier.network.entity.ApiResponse
import com.google.gson.JsonArray
import com.yang.ktbase.network.netutil.JetPackRepository
import com.yang.ktbase.vm.BaseViewModel

/**
 * 示例代码
 */
class NetExpViewModel: BaseViewModel() {

    private val repository by lazy { JetPackRepository() }

    suspend fun getBanner():ApiResponse<JsonArray>{
        return  repository.getBanner1()
    };
}