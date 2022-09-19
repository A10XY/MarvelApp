package com.ahmed.marvelapp.ui.characters

import com.ahmed.marvelapp.data.response.Character
import com.ahmed.marvelapp.ui.base.BaseInteractionListener

interface CharactersClicksListener : BaseInteractionListener {
    fun onCharacterClick(character: Character)
}