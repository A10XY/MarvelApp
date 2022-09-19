package com.ahmed.marvelapp.data.response

import com.google.gson.annotations.SerializedName

data class MarvelResourceList<T>(
    @SerializedName("available")
    val available: Int?,
    @SerializedName("items")
    val items: List<T>?
)