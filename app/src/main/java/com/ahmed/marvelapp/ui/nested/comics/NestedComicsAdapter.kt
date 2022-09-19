package com.ahmed.marvelapp.ui.nested.comics

import com.ahmed.marvelapp.R
import com.ahmed.marvelapp.data.response.Comic
import com.ahmed.marvelapp.ui.base.BaseAdapter

class NestedComicsAdapter(
    items: List<Comic>,
    listener: NestedComicsClicksListener,
) : BaseAdapter<Comic>(items, listener) {
    override val layoutId: Int = R.layout.item_nested_comic
}