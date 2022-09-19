package com.ahmed.marvelapp.ui.comics

import com.ahmed.marvelapp.data.response.Comic
import com.ahmed.marvelapp.ui.base.BaseInteractionListener

interface ComicsClicksListener : BaseInteractionListener {
    fun onComicClick(comic: Comic)
}