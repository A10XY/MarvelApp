package com.ahmed.marvelapp.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ahmed.marvelapp.data.repository.RepositoryImp
import com.ahmed.marvelapp.data.response.Character
import com.ahmed.marvelapp.data.response.Comic
import com.ahmed.marvelapp.data.response.MarvelApiResponse
import com.ahmed.marvelapp.data.service.WebRequest
import com.ahmed.marvelapp.domain.sealeds.RequestState
import com.ahmed.marvelapp.ui.nested.characters.NestedCharactersClicksListener
import com.ahmed.marvelapp.ui.nested.comics.NestedComicsClicksListener
import com.ahmed.marvelapp.utilities.Event
import com.ahmed.marvelapp.utilities.observeOnMainThread
import com.ahmed.marvelapp.utilities.postEvent

class HomeFragmentViewModel : ViewModel(), NestedComicsClicksListener, NestedCharactersClicksListener {
    private val webRequest = WebRequest()
    private val repository = RepositoryImp(webRequest.marvelApiService)

    private val _requestState = MutableLiveData<RequestState<MarvelApiResponse<Character>>>(RequestState.Loading)
    val requestState: LiveData<RequestState<MarvelApiResponse<Character>>> get() = _requestState

    private val _showMoreCharactersClicked = MutableLiveData(Event(false))
    val showMoreCharactersClicked: LiveData<Event<Boolean>> get() = _showMoreCharactersClicked

    private val _nestedCharacterClicked = MutableLiveData<Event<Character>>()
    val nestedCharacterClicked: LiveData<Event<Character>> get() = _nestedCharacterClicked

    private val _showMoreComicsClicked = MutableLiveData(Event(false))
    val showMoreComicsClicked: LiveData<Event<Boolean>> get() = _showMoreComicsClicked

    private val _nestedComicClicked = MutableLiveData<Event<Comic>>()
    val nestedComicClicked: LiveData<Event<Comic>> get() = _nestedComicClicked

    val comicsList = mutableListOf<Comic>()

    init {
        getHomeData()
    }

    private fun getHomeData() {
        repository.getComics(10).run {
            observeOnMainThread()
            flatMap { requestState ->
                if (requestState is RequestState.Success) {
                    requestState.data?.data?.results?.let { listOfComics -> listOfComics.forEach { comicsList.add(it) } }
                }
                repository.getCharacters(10)
            }.observeOnMainThread().subscribe(::onSuccess, ::onFail)
        }
    }

    private fun onSuccess(state: RequestState<MarvelApiResponse<Character>>) {
        if (state is RequestState.Success) {
            _requestState.postValue(state)
        }
    }

    private fun onFail(throwable: Throwable) {
        _requestState.postValue(RequestState.Error(throwable.message.toString()))
    }

    fun tryLoadDataAgain() {
        _requestState.postValue(RequestState.Loading)
        getHomeData()
    }

    override fun onShowMoreCharactersClick() {
        _showMoreCharactersClicked.postEvent(true)
    }

    override fun onNestedCharacterClick(character: Character) {
        _nestedCharacterClicked.postEvent(character)
    }

    override fun onShowMoreComicsClick() {
        _showMoreComicsClicked.postEvent(true)
    }

    override fun onNestedComicClick(comic: Comic) {
        _nestedComicClicked.postEvent(comic)
    }
}