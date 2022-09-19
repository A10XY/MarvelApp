package com.ahmed.marvelapp.data.response

import com.google.gson.annotations.SerializedName

data class MarvelApiResponseData<T>(
    @SerializedName("limit")
    val limit: Int?,
    @SerializedName("results")
    val results: List<T>?
)