package com.ahmed.marvelapp.ui.character_details

import com.ahmed.marvelapp.ui.base.BaseInteractionListener

interface CharacterDetailsClicksListener : BaseInteractionListener {
    fun onVisitCharacterClickListener(url: String)
}