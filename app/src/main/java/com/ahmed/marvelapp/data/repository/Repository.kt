package com.ahmed.marvelapp.data.repository

import com.ahmed.marvelapp.data.response.Character
import com.ahmed.marvelapp.data.response.Comic
import com.ahmed.marvelapp.data.response.MarvelApiResponse
import com.ahmed.marvelapp.domain.sealeds.RequestState
import io.reactivex.rxjava3.core.Single

interface Repository {
    fun getCharacters(count: Int): Single<RequestState<MarvelApiResponse<Character>>>
    fun getComics(count: Int): Single<RequestState<MarvelApiResponse<Comic>>>
}