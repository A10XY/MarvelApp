package com.ahmed.marvelapp.data.response

import com.google.gson.annotations.SerializedName

data class MarvelUrl(
    @SerializedName("type")
    val type: String?,
    @SerializedName("url")
    val url: String?,
)
