package com.ahmed.marvelapp.ui.base

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.ahmed.marvelapp.BR

abstract class BaseAdapter<T>(
    var items: List<T>,
    private var listener: BaseInteractionListener
) : RecyclerView.Adapter<BaseAdapter.BaseViewHolder>() {
    abstract class BaseViewHolder(binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root)
    abstract val layoutId: Int

    class ItemViewHolder(val binding: ViewDataBinding) : BaseViewHolder(binding)

    override fun getItemCount(): Int = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return ItemViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context), layoutId , parent,false) )
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        val currentItem = items[position]
        when(holder) {
            is ItemViewHolder -> {
                holder.binding.setVariable(BR.item, currentItem)
                holder.binding.setVariable(BR.listener, listener)
            }
        }
    }
}