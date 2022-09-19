package com.ahmed.marvelapp.data.response

import com.google.gson.annotations.SerializedName

data class MarvelApiThumbnail(
    @SerializedName("path")
    val path: String?,
    @SerializedName("extension")
    val extension: String?,
)
