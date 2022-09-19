package com.ahmed.marvelapp.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.ahmed.marvelapp.R
import com.ahmed.marvelapp.domain.enums.RecyclerViewItemViewType
import com.ahmed.marvelapp.domain.sealeds.*
import com.ahmed.marvelapp.ui.nested.characters.NestedCharactersClicksListener
import com.ahmed.marvelapp.ui.nested.comics.NestedComicsClicksListener

class HomeFragmentAdapter(
    private val items: List<RecyclerViewItem>,
    private val nestedComicsListener: NestedComicsClicksListener,
    private val nestedCharactersListener: NestedCharactersClicksListener,
) : RecyclerView.Adapter<RecyclerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        return when (viewType){
            RecyclerViewItemViewType.CHARACTERS.ordinal -> {
                RecyclerViewHolder.CharactersViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.layout_nested_characters, parent, false))
            }
            RecyclerViewItemViewType.COMICS.ordinal -> {
                RecyclerViewHolder.ComicsViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.layout_nested_comics, parent, false))
            }
            RecyclerViewItemViewType.INFO_CARD.ordinal -> {
                RecyclerViewHolder.InfoCardViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.layout_nested_info_card, parent, false))
            }
            else -> throw IllegalArgumentException("UNKNOWN VIEW TYPE")
        }
    }

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        when (holder) {
            is RecyclerViewHolder.CharactersViewHolder -> {
                holder.bind(items[position] as RecyclerViewItem.Characters, nestedCharactersListener)
            }
            is RecyclerViewHolder.ComicsViewHolder -> {
                holder.bind(items[position] as RecyclerViewItem.Comics, nestedComicsListener)
            }
            is RecyclerViewHolder.InfoCardViewHolder -> {
                holder.bind(items[position] as RecyclerViewItem.InfoCard)
            }
            else -> {}
        }
    }

    override fun getItemCount(): Int = items.size

    override fun getItemViewType(position: Int): Int {
        return when(items[position]) {
            is RecyclerViewItem.Characters -> {
                RecyclerViewItemViewType.CHARACTERS.ordinal
            }
            is  RecyclerViewItem.Comics -> {
                RecyclerViewItemViewType.COMICS.ordinal
            }
            is RecyclerViewItem.InfoCard -> {
                RecyclerViewItemViewType.INFO_CARD.ordinal
            }
            else -> -1
        }
    }

}