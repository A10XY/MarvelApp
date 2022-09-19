package com.ahmed.marvelapp.domain.sealeds

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.ahmed.marvelapp.databinding.*
import com.ahmed.marvelapp.ui.character_details.CharacterDetailsClicksListener
import com.ahmed.marvelapp.ui.comic_details.ComicDetailsClicksListener
import com.ahmed.marvelapp.ui.nested.characters.NestedCharactersAdapter
import com.ahmed.marvelapp.ui.nested.characters.NestedCharactersClicksListener
import com.ahmed.marvelapp.ui.nested.comics.NestedComicsAdapter
import com.ahmed.marvelapp.ui.nested.comics.NestedComicsClicksListener
import com.bumptech.glide.Glide

sealed class RecyclerViewHolder(
    binding: ViewDataBinding
) : RecyclerView.ViewHolder(binding.root) {

    class CharactersViewHolder(
        private val binding: LayoutNestedCharactersBinding,
    ) : RecyclerViewHolder(binding) {
        fun bind(characters: RecyclerViewItem.Characters, listener: NestedCharactersClicksListener) {
            binding.run {
                charactersRecyclerView.adapter = NestedCharactersAdapter(characters.charactersList, listener)
                showMoreCharactersTextView.setOnClickListener {
                    listener.onShowMoreCharactersClick()
                }
            }
        }
    }

    class ComicsViewHolder(
        private val binding: LayoutNestedComicsBinding,
    ) : RecyclerViewHolder(binding) {
        fun bind(comics: RecyclerViewItem.Comics, listener: NestedComicsClicksListener) {
            binding.run {
                comicsRecyclerView.adapter = NestedComicsAdapter(comics.comicsList, listener)
                showMoreComicsTextView.setOnClickListener {
                    listener.onShowMoreComicsClick()
                }
            }
        }
    }

    class CharacterDetailsViewHolder(
        private val binding: LayoutNestedCharacterDetailsBinding,
    ) : RecyclerViewHolder(binding) {
        fun bind(characterDetails: RecyclerViewItem.CharacterDetails, listener: CharacterDetailsClicksListener) {
            binding.run {
                characterNameTextView.text = characterDetails.name
                characterDescriptionTextView.text = characterDetails.description
                visitLinearLayout.setOnClickListener {
                    listener.onVisitCharacterClickListener(characterDetails.url)
                }
            }
        }
    }

    class ComicsDetailsViewHolder(
        private val binding: LayoutNestedComicDetailsBinding,
    ) : RecyclerViewHolder(binding) {
        fun bind(comicDetails: RecyclerViewItem.ComicDetails, listener: ComicDetailsClicksListener) {
            binding.run {
                comicTitleTextView.text = comicDetails.title
                comicDescriptionTextView.text = comicDetails.description
                visitLinearLayout.setOnClickListener {
                    listener.onVisitComicClickListener(comicDetails.url)
                }
            }
        }
    }

    class InfoCardViewHolder(
        private val binding: LayoutNestedInfoCardBinding
    ) : RecyclerViewHolder(binding) {
        fun bind(infoCard: RecyclerViewItem.InfoCard) {
            binding.run {
                holderTitleTextView.text = infoCard.title
                holderDescriptionTextView.text = infoCard.subtitle
                Glide.with(root).load(infoCard.url).into(holderImageView)
            }
        }
    }

}