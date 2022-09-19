package com.ahmed.marvelapp.data.service

import com.ahmed.marvelapp.utilities.Constants
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class WebRequest {
    private val retrofit = Retrofit.Builder().apply {
        baseUrl(Constants.BASE_API_URL)
        addConverterFactory(GsonConverterFactory.create())
        addCallAdapterFactory(RxJava3CallAdapterFactory.create())
    }.build()

    val marvelApiService: MarvelApiService = retrofit.create(MarvelApiService::class.java)
}