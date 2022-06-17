package com.yang.ktbase.base

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.google.gson.JsonArray
import com.google.gson.JsonObject

abstract class BaseRecyclerViewJsonAdapter<V : ViewBinding> :
    RecyclerView.Adapter<BaseViewHolder<V>>() {

    open var data: JsonArray = JsonArray()
        set(value) {
            field = value
            notifyItemRangeChanged(0, value.size())
        }

    //不想用反射，所以只能暴露出去多写一部
    abstract override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<V>

    override fun getItemCount(): Int = data.size()

    override fun onBindViewHolder(holder: BaseViewHolder<V>, position: Int) {
        onBindViewHolder(
            holder, holder.absoluteAdapterPosition, holder.binding,
            data.get(holder.absoluteAdapterPosition) as JsonObject
        )
    }

    abstract fun onBindViewHolder(
        holder: BaseViewHolder<V>,
        position: Int,
        binding: V,
        jsonObject: JsonObject
    )

}

