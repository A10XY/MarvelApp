package com.ahmed.marvelapp.ui.characters

import com.ahmed.marvelapp.R
import com.ahmed.marvelapp.data.response.Character
import com.ahmed.marvelapp.ui.base.BaseAdapter

class CharactersFragmentAdapter(
    items: List<Character>,
    private val listener: CharactersClicksListener,
) : BaseAdapter<Character>(items, listener) {
    override val layoutId: Int = R.layout.item_character
}