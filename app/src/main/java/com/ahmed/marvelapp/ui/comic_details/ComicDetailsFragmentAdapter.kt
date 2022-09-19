package com.ahmed.marvelapp.ui.comic_details

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.ahmed.marvelapp.R
import com.ahmed.marvelapp.domain.enums.RecyclerViewItemViewType
import com.ahmed.marvelapp.domain.sealeds.RecyclerViewHolder
import com.ahmed.marvelapp.domain.sealeds.RecyclerViewItem
import com.ahmed.marvelapp.ui.nested.characters.NestedCharactersClicksListener

class ComicDetailsFragmentAdapter(
    private val items: List<RecyclerViewItem>,
    private val listener: ComicDetailsClicksListener,
    private val characterListener: NestedCharactersClicksListener
) : RecyclerView.Adapter<RecyclerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        return when (viewType){
            RecyclerViewItemViewType.COMICS_DETAILS.ordinal -> {
                RecyclerViewHolder.ComicsDetailsViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.layout_nested_comic_details, parent, false))
            }
            RecyclerViewItemViewType.CHARACTERS.ordinal -> {
                RecyclerViewHolder.CharactersViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.layout_nested_characters, parent, false))
            }
            else -> throw IllegalArgumentException("UNKNOWN VIEW TYPE")
        }
    }

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        when (holder) {
            is RecyclerViewHolder.CharactersViewHolder -> {
                holder.bind(items[position] as RecyclerViewItem.Characters, characterListener)
            }
            is RecyclerViewHolder.ComicsDetailsViewHolder -> {
                holder.bind(items[position] as RecyclerViewItem.ComicDetails, listener)
            }
            else -> {}
        }
    }

    override fun getItemCount(): Int = items.size

    override fun getItemViewType(position: Int): Int {
        return when(items[position]) {
            is RecyclerViewItem.ComicDetails -> {
                RecyclerViewItemViewType.COMICS_DETAILS.ordinal
            }
            is RecyclerViewItem.Characters -> {
                RecyclerViewItemViewType.CHARACTERS.ordinal
            }
            else -> -1
        }
    }
}