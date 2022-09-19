package com.ahmed.marvelapp.ui.comic_details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ahmed.marvelapp.data.response.Character
import com.ahmed.marvelapp.data.response.Comic
import com.ahmed.marvelapp.ui.nested.characters.NestedCharactersClicksListener
import com.ahmed.marvelapp.utilities.Event
import com.ahmed.marvelapp.utilities.postEvent

class ComicDetailsFragmentViewModel : ViewModel(), ComicDetailsClicksListener, NestedCharactersClicksListener {
    private val _navigateUp = MutableLiveData(Event(false))
    val navigateUp: LiveData<Event<Boolean>> get() = _navigateUp

    private val _shareComic = MutableLiveData<Event<String>>()
    val shareComic: LiveData<Event<String>> get() = _shareComic

    private val _visitComic = MutableLiveData<Event<String>>()
    val visitComic: LiveData<Event<String>> get() = _visitComic

    private val _gotComic = MutableLiveData<Comic>()
    val gotComic: LiveData<Comic> get() = _gotComic

    private val _showMoreCharactersClicked = MutableLiveData(Event(false))
    val showMoreCharactersClicked: LiveData<Event<Boolean>> get() = _showMoreCharactersClicked

    private val _nestedCharacterClicked = MutableLiveData<Event<Character>>()
    val nestedCharacterClicked: LiveData<Event<Character>> get() = _nestedCharacterClicked

    fun setComic(comic: Comic) {
        _gotComic.postValue(comic)
    }

    fun onShareComicClickListener() {
        _gotComic.value?.urls?.get(0)?.url?.let { url ->
            _shareComic.postEvent(url)
        }
    }

    fun onNavigateUpClickListener() {
        _navigateUp.postEvent(true)
    }

    override fun onVisitComicClickListener(url: String) {
        _visitComic.postEvent(url)
    }

    override fun onShowMoreCharactersClick() {
        _showMoreCharactersClicked.postEvent(true)
    }

    override fun onNestedCharacterClick(character: Character) {
        _nestedCharacterClicked.postEvent(character)
    }
}