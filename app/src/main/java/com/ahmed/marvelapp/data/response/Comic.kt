package com.ahmed.marvelapp.data.response

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Comic(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("thumbnail")
    val thumbnail: MarvelApiThumbnail?,
    @SerializedName("pageCount")
    val pageCount: Int?,
    @SerializedName("urls")
    val urls: List<MarvelUrl>?,
    @SerializedName("characters")
    val characters: MarvelResourceList<Character>?,
) : Serializable
