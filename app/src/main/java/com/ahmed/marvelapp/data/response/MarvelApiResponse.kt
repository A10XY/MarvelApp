package com.ahmed.marvelapp.data.response

import com.google.gson.annotations.SerializedName

data class MarvelApiResponse<T>(
    @SerializedName("data")
    val data: MarvelApiResponseData<T>?,
)