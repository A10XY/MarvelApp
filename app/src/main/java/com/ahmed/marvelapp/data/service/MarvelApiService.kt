package com.ahmed.marvelapp.data.service

import com.ahmed.marvelapp.data.response.Character
import com.ahmed.marvelapp.data.response.Comic
import com.ahmed.marvelapp.data.response.MarvelApiResponse
import com.ahmed.marvelapp.utilities.Constants
import com.ahmed.marvelapp.utilities.Utils
import io.reactivex.rxjava3.core.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MarvelApiService {

    @GET("comics")
    fun getComics(
        @Query("apikey") apiKey: String = Constants.PUBLIC_API_KEY,
        @Query("ts") ts: String = Utils.currentTimeStamp,
        @Query("hash") hash: String = Utils.hash(),
        @Query("limit") limit: String = Constants.DEFAULT_API_RESULTS_LIMIT,
    ): Single<Response<MarvelApiResponse<Comic>>>

    @GET("characters")
    fun getCharacters(
        @Query("apikey") apiKey: String = Constants.PUBLIC_API_KEY,
        @Query("ts") ts: String = Utils.currentTimeStamp,
        @Query("hash") hash: String = Utils.hash(),
        @Query("limit") limit: String = Constants.DEFAULT_API_RESULTS_LIMIT,
    ): Single<Response<MarvelApiResponse<Character>>>
}