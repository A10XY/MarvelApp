package com.ahmed.marvelapp.ui.characters

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ahmed.marvelapp.data.repository.RepositoryImp
import com.ahmed.marvelapp.data.response.Character
import com.ahmed.marvelapp.data.response.MarvelApiResponse
import com.ahmed.marvelapp.data.service.WebRequest
import com.ahmed.marvelapp.domain.sealeds.RequestState
import com.ahmed.marvelapp.utilities.Event
import com.ahmed.marvelapp.utilities.observeOnMainThread
import com.ahmed.marvelapp.utilities.postEvent

class CharactersFragmentViewModel : ViewModel(), CharactersClicksListener {
    private val webRequest = WebRequest()
    private val repository = RepositoryImp(webRequest.marvelApiService)

    private val _requestState = MutableLiveData<RequestState<MarvelApiResponse<Character>>>(RequestState.Loading)
    val requestState: LiveData<RequestState<MarvelApiResponse<Character>>> get() = _requestState

    private val _navigateUp = MutableLiveData(Event(false))
    val navigateUp: LiveData<Event<Boolean>> get() = _navigateUp

    private val _navigateToCharacterDetails = MutableLiveData<Event<Character>>()
    val navigateToCharacterDetails: LiveData<Event<Character>> get() = _navigateToCharacterDetails

    init {
        getCharacters()
    }

    private fun getCharacters() {
        repository.getCharacters(50).run {
            observeOnMainThread()
            subscribe(::onSuccess, ::onFail)
        }
    }

    private fun onSuccess(state: RequestState<MarvelApiResponse<Character>>) {
        if (state is RequestState.Success) {
            _requestState.postValue(state)
            Log.d("MALT", "Success: ${state.data?.data?.results?.get(0)?.name}")
        } else if (state is RequestState.Error) {
            _requestState.postValue(state)
            Log.d("MALT", state.message)
        }
    }

    private fun onFail(throwable: Throwable) {
        _requestState.postValue(RequestState.Error(throwable.message.toString()))
        Log.d("MALT", "Request Failed: ${throwable.message.toString()}")
    }

    fun onNavigateUpClickListener() {
        _navigateUp.postEvent(true)
    }

    fun tryLoadDataAgain() {
        _requestState.postValue(RequestState.Loading)
        getCharacters()
    }

    override fun onCharacterClick(character: Character) {
        _navigateToCharacterDetails.postEvent(character)
    }
}