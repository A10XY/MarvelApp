package com.ahmed.marvelapp.data.response

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Character(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("thumbnail")
    val thumbnail: MarvelApiThumbnail?,
    @SerializedName("urls")
    val urls: List<MarvelUrl>?,
    @SerializedName("comics")
    val comics: MarvelResourceList<Comic>?,
) : Serializable
