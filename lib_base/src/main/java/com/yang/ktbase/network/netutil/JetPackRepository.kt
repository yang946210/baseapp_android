package com.yang.ktbase.network.netutil

import com.aisier.network.entity.ApiResponse
import com.google.gson.JsonArray
import com.yang.ktbase.network.RetrofitApi

class JetPackRepository : BaseRepository() {


    suspend fun getBanner1(): ApiResponse<JsonArray> {
        return executeHttp {
            RetrofitApi.getApi.getBanner()
        }
    }



}