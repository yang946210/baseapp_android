package com.yang.ktbase.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

abstract class BaseRecyclerViewAdapter<E : Any, V : ViewBinding> : RecyclerView.Adapter<BaseRecyclerViewAdapter.BaseViewHolder<V>>() {

    open var data: MutableList<E> = mutableListOf()
        set(value) {
            field = value
            notifyItemRangeChanged(0, value.size)
        }

    //不想用反射，所以只能暴露出去多写一部
    abstract override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<V>

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: BaseViewHolder<V>, position: Int) {
        onBindViewHolder(
            holder,
            holder.absoluteAdapterPosition,
            holder.binding,
            data[holder.absoluteAdapterPosition]
        )
    }

    abstract fun onBindViewHolder(holder: BaseViewHolder<V>, position: Int, binding: V, bean: E)

    open class BaseViewHolder<V : ViewBinding>(val binding: V) : RecyclerView.ViewHolder(binding.root)

}

