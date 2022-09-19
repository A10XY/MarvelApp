package com.ahmed.marvelapp.ui.character_details

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.ahmed.marvelapp.R
import com.ahmed.marvelapp.databinding.FragmentCharacterDetailsBinding
import com.ahmed.marvelapp.domain.sealeds.RecyclerViewItem
import com.ahmed.marvelapp.ui.base.BaseFragment
import com.ahmed.marvelapp.ui.nested.comics.NestedComicsClicksListener
import com.ahmed.marvelapp.utilities.Utils
import com.ahmed.marvelapp.utilities.observeEvent

class CharacterDetailsFragment : BaseFragment<FragmentCharacterDetailsBinding>() {
    override val layoutIdFragment: Int = R.layout.fragment_character_details
    override val viewModel: CharacterDetailsFragmentViewModel by viewModels()

    private val args: CharacterDetailsFragmentArgs by navArgs()
    private val utils = Utils()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.setCharacter(args.character)
        handleEvents()
    }

    private fun handleEvents() {
        viewModel.gotCharacter.observe(viewLifecycleOwner) { character ->
            val list = listOf(
                RecyclerViewItem.CharacterDetails(
                    character.name!!,
                    character.description!!,
                    character.urls?.get(0)?.url!!,
                )
            )
            binding.characterDetailsRecyclerView.adapter = CharacterDetailsFragmentAdapter(list, viewModel as CharacterDetailsClicksListener, viewModel as NestedComicsClicksListener)
        }
        viewModel.navigateUp.observeEvent(viewLifecycleOwner) { navigateUp ->
            if (navigateUp) {
                utils.navigateUp(requireView())
            }
        }
        viewModel.shareCharacter.observeEvent(viewLifecycleOwner) { url ->
            shareCharacter(url)
        }
        viewModel.visitCharacter.observeEvent(viewLifecycleOwner) { url ->
            visitCharacter(url)
        }
    }

    private fun shareCharacter(url: String) {
        utils.share(requireActivity(), url)
    }

    private fun visitCharacter(url: String) {
        utils.openUrl(requireActivity(), url)
    }
}