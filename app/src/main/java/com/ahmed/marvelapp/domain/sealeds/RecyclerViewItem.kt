package com.ahmed.marvelapp.domain.sealeds

import com.ahmed.marvelapp.data.response.Character
import com.ahmed.marvelapp.data.response.Comic

sealed class RecyclerViewItem {

    data class Characters(
        val charactersList: List<Character>,
    ) : RecyclerViewItem()

    data class Comics(
        val comicsList: List<Comic>,
    ) : RecyclerViewItem()

    data class CharacterDetails(
        val name: String,
        val description: String,
        val url: String,
    ) : RecyclerViewItem()

    data class ComicDetails(
        val title: String,
        val description: String,
        val url: String,
    ) : RecyclerViewItem()

    data class InfoCard(
        val title: String,
        val subtitle: String,
        val url: String,
    ) : RecyclerViewItem()
}