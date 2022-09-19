package com.ahmed.marvelapp.ui.nested.characters

import com.ahmed.marvelapp.R
import com.ahmed.marvelapp.data.response.Character
import com.ahmed.marvelapp.ui.base.BaseAdapter

class NestedCharactersAdapter(
    items: List<Character>,
    listener: NestedCharactersClicksListener,
) : BaseAdapter<Character>(items, listener) {
    override val layoutId: Int = R.layout.item_nested_character
}