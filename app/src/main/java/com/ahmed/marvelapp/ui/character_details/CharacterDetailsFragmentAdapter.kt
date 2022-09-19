package com.ahmed.marvelapp.ui.character_details

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.ahmed.marvelapp.R
import com.ahmed.marvelapp.domain.enums.RecyclerViewItemViewType
import com.ahmed.marvelapp.domain.sealeds.RecyclerViewHolder
import com.ahmed.marvelapp.domain.sealeds.RecyclerViewItem
import com.ahmed.marvelapp.ui.nested.comics.NestedComicsClicksListener

class CharacterDetailsFragmentAdapter(
    private val items: List<RecyclerViewItem>,
    private val listener: CharacterDetailsClicksListener,
    private val comicsListener: NestedComicsClicksListener
) : RecyclerView.Adapter<RecyclerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        return when (viewType){
            RecyclerViewItemViewType.CHARACTER_DETAILS.ordinal -> {
                RecyclerViewHolder.CharacterDetailsViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.layout_nested_character_details, parent, false))
            }
            RecyclerViewItemViewType.COMICS.ordinal -> {
                RecyclerViewHolder.ComicsViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.layout_nested_comics, parent, false))
            }
            else -> throw IllegalArgumentException("UNKNOWN VIEW TYPE")
        }
    }

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        when (holder) {
            is RecyclerViewHolder.CharacterDetailsViewHolder -> {
                holder.bind(items[position] as RecyclerViewItem.CharacterDetails, listener)
            }
            is RecyclerViewHolder.ComicsViewHolder -> {
                holder.bind(items[position] as RecyclerViewItem.Comics, comicsListener)
            }
            else -> {}
        }
    }

    override fun getItemCount(): Int = items.size

    override fun getItemViewType(position: Int): Int {
        return when(items[position]) {
            is RecyclerViewItem.CharacterDetails -> {
                RecyclerViewItemViewType.CHARACTER_DETAILS.ordinal
            }
            is RecyclerViewItem.Comics -> {
                RecyclerViewItemViewType.COMICS.ordinal
            }
            else -> -1
        }
    }
}