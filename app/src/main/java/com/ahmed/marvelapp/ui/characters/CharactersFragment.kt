package com.ahmed.marvelapp.ui.characters

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.ahmed.marvelapp.R
import com.ahmed.marvelapp.data.response.Character
import com.ahmed.marvelapp.databinding.FragmentCharactersBinding
import com.ahmed.marvelapp.ui.base.BaseFragment
import com.ahmed.marvelapp.domain.sealeds.RequestState
import com.ahmed.marvelapp.utilities.Utils
import com.ahmed.marvelapp.utilities.observeEvent

class CharactersFragment : BaseFragment<FragmentCharactersBinding>() {
    override val layoutIdFragment: Int = R.layout.fragment_characters
    override val viewModel: CharactersFragmentViewModel by viewModels()

    private val utils = Utils()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        handleEvents()
    }

    private fun handleEvents() {
        viewModel.requestState.observe(viewLifecycleOwner) {
            if (it is RequestState.Success) {
                val characters = it.data?.data?.results
                characters?.let { charactersList ->
                    binding.rv.adapter = CharactersFragmentAdapter(charactersList, viewModel)
                }
            }
        }
        viewModel.navigateUp.observeEvent(viewLifecycleOwner) { navigateUp ->
            if (navigateUp) {
                utils.navigateUp(requireView())
            }
        }

        viewModel.navigateToCharacterDetails.observeEvent(viewLifecycleOwner) { character ->
            navigateToCharacterDetailsFragment(character)
        }
    }

    private fun navigateToCharacterDetailsFragment(character: Character) {
        requireView().findNavController().navigate(CharactersFragmentDirections.actionCharactersFragmentToCharacterDetailsFragment(character))
    }
}