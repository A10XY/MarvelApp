package com.ahmed.marvelapp.ui.comic_details

import com.ahmed.marvelapp.ui.base.BaseInteractionListener

interface ComicDetailsClicksListener : BaseInteractionListener {
    fun onVisitComicClickListener(url: String)
}