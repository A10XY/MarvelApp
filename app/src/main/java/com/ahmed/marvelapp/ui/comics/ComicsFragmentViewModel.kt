package com.ahmed.marvelapp.ui.comics

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ahmed.marvelapp.data.repository.RepositoryImp
import com.ahmed.marvelapp.data.response.Comic
import com.ahmed.marvelapp.data.response.MarvelApiResponse
import com.ahmed.marvelapp.data.service.WebRequest
import com.ahmed.marvelapp.domain.sealeds.RequestState
import com.ahmed.marvelapp.utilities.Event
import com.ahmed.marvelapp.utilities.observeOnMainThread
import com.ahmed.marvelapp.utilities.postEvent

class ComicsFragmentViewModel : ViewModel(), ComicsClicksListener {
    private val webRequest = WebRequest()
    private val repository = RepositoryImp(webRequest.marvelApiService)

    private val _requestState = MutableLiveData<RequestState<MarvelApiResponse<Comic>>>(RequestState.Loading)
    val requestState: LiveData<RequestState<MarvelApiResponse<Comic>>> get() = _requestState

    private val _navigateUp = MutableLiveData(Event(false))
    val navigateUp: LiveData<Event<Boolean>> get() = _navigateUp

    private val _navigateToComicDetails = MutableLiveData<Event<Comic>>()
    val navigateToComicDetails: LiveData<Event<Comic>> get() = _navigateToComicDetails

    init {
        getComics()
    }

    private fun getComics() {
        repository.getComics(50).run {
            observeOnMainThread()
            subscribe(::onSuccess, ::onFail)
        }
    }

    private fun onSuccess(state: RequestState<MarvelApiResponse<Comic>>) {
        if (state is RequestState.Success) {
            _requestState.postValue(state)
        } else if (state is RequestState.Error) {
            _requestState.postValue(state)
        }
    }

    private fun onFail(throwable: Throwable) {
        _requestState.postValue(RequestState.Error(throwable.message.toString()))
    }

    fun onNavigateUpClickListener() {
        _navigateUp.postEvent(true)
    }

    fun tryLoadDataAgain() {
        _requestState.postValue(RequestState.Loading)
        getComics()
    }

    override fun onComicClick(comic: Comic) {
        _navigateToComicDetails.postEvent(comic)
    }
}