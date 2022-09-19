package com.ahmed.marvelapp.data.repository

import com.ahmed.marvelapp.data.response.Character
import com.ahmed.marvelapp.data.response.Comic
import com.ahmed.marvelapp.data.response.MarvelApiResponse
import com.ahmed.marvelapp.data.service.MarvelApiService
import com.ahmed.marvelapp.domain.sealeds.RequestState
import io.reactivex.rxjava3.core.Single
import retrofit2.Response

class RepositoryImp(private val marvelApiService: MarvelApiService) : Repository {
    override fun getCharacters(count: Int): Single<RequestState<MarvelApiResponse<Character>>> {
        return wrapResponse(marvelApiService.getCharacters(limit = count.toString()))
    }

    override fun getComics(count: Int): Single<RequestState<MarvelApiResponse<Comic>>> {
        return wrapResponse(marvelApiService.getComics(limit = count.toString()))
    }

    private fun <T> wrapResponse(response: Single<Response<T>>): Single<RequestState<T>> {
        return response.map {
            if (it.isSuccessful) {
                RequestState.Success(it.body())
            } else {
                RequestState.Error(it.message())
            }
        }
    }
}