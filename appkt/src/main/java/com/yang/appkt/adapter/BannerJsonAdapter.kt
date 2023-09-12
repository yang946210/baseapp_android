package com.yang.appkt.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.google.gson.JsonObject
import com.v2ray.ang.databinding.ItemBannerBinding
import com.yang.ktbase.base.BaseRecyclerViewJsonAdapter
import com.yang.ktbase.base.BaseViewHolder

class BannerJsonAdapter : BaseRecyclerViewJsonAdapter<ItemBannerBinding>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder<ItemBannerBinding> {
        var inflater=ItemBannerBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return BaseViewHolder(inflater)
    }

    override fun onBindViewHolder(
        holder: BaseViewHolder<ItemBannerBinding>,
        position: Int,
        binding: ItemBannerBinding,
        jsonObject: JsonObject
    ) {
        binding.tvTitle.text= jsonObject.get("title").toString();
    }
}