package com.ahmed.marvelapp.ui.character_details

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ahmed.marvelapp.data.response.Character
import com.ahmed.marvelapp.data.response.Comic
import com.ahmed.marvelapp.ui.nested.comics.NestedComicsClicksListener
import com.ahmed.marvelapp.utilities.Event
import com.ahmed.marvelapp.utilities.postEvent

class CharacterDetailsFragmentViewModel : ViewModel(), CharacterDetailsClicksListener, NestedComicsClicksListener {

    private val _navigateUp = MutableLiveData(Event(false))
    val navigateUp: LiveData<Event<Boolean>> get() = _navigateUp

    private val _shareCharacter = MutableLiveData<Event<String>>()
    val shareCharacter: LiveData<Event<String>> get() = _shareCharacter

    private val _visitCharacter = MutableLiveData<Event<String>>()
    val visitCharacter: LiveData<Event<String>> get() = _visitCharacter

    private val _gotCharacter = MutableLiveData<Character>()
    val gotCharacter: LiveData<Character> get() = _gotCharacter

    private val _showMoreComicsClicked = MutableLiveData(Event(false))
    val showMoreComicsClicked: LiveData<Event<Boolean>> get() = _showMoreComicsClicked

    private val _nestedComicClicked = MutableLiveData<Event<Comic>>()
    val nestedComicClicked: LiveData<Event<Comic>> get() = _nestedComicClicked

    fun setCharacter(character: Character) {
        _gotCharacter.postValue(character)
    }

    fun onShareCharacterClickListener() {
        _gotCharacter.value?.urls?.get(0)?.url?.let { url ->
            _shareCharacter.postEvent(url)
        }
    }

    fun onNavigateUpClickListener() {
        _navigateUp.postEvent(true)
    }

    override fun onVisitCharacterClickListener(url: String) {
        _visitCharacter.postEvent(url)
    }

    override fun onShowMoreComicsClick() {
        _showMoreComicsClicked.postEvent(true)
    }

    override fun onNestedComicClick(comic: Comic) {
        _nestedComicClicked.postEvent(comic)
    }
}