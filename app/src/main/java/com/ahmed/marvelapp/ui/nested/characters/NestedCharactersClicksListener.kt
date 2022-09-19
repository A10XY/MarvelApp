package com.ahmed.marvelapp.ui.nested.characters

import com.ahmed.marvelapp.data.response.Character
import com.ahmed.marvelapp.ui.base.BaseInteractionListener

interface NestedCharactersClicksListener : BaseInteractionListener {
    fun onShowMoreCharactersClick()
    fun onNestedCharacterClick(character: Character)
}