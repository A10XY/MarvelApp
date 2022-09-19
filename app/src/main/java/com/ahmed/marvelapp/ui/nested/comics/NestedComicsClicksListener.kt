package com.ahmed.marvelapp.ui.nested.comics

import com.ahmed.marvelapp.data.response.Comic
import com.ahmed.marvelapp.ui.base.BaseInteractionListener

interface NestedComicsClicksListener : BaseInteractionListener {
    fun onShowMoreComicsClick()
    fun onNestedComicClick(comic: Comic)
}