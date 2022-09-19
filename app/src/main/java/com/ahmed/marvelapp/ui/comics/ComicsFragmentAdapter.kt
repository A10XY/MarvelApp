package com.ahmed.marvelapp.ui.comics

import com.ahmed.marvelapp.R
import com.ahmed.marvelapp.data.response.Comic
import com.ahmed.marvelapp.ui.base.BaseAdapter

class ComicsFragmentAdapter(
    items: List<Comic>,
    private val listener: ComicsClicksListener
) : BaseAdapter<Comic>(items, listener) {
    override val layoutId: Int = R.layout.item_comic
}