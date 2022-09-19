package com.ahmed.marvelapp.ui.comics

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.ahmed.marvelapp.R
import com.ahmed.marvelapp.data.response.Comic
import com.ahmed.marvelapp.databinding.FragmentComicsBinding
import com.ahmed.marvelapp.domain.sealeds.RequestState
import com.ahmed.marvelapp.ui.base.BaseFragment
import com.ahmed.marvelapp.utilities.Utils
import com.ahmed.marvelapp.utilities.observeEvent

class ComicsFragment : BaseFragment<FragmentComicsBinding>() {
    override val layoutIdFragment: Int = R.layout.fragment_comics
    override val viewModel: ComicsFragmentViewModel by viewModels()

    private val utils = Utils()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        handleEvents()
    }

    private fun handleEvents() {
        viewModel.requestState.observe(viewLifecycleOwner) {
            if (it is RequestState.Success) {
                val characters = it.data?.data?.results
                characters?.let { comicsList ->
                    binding.comicsRecyclerView.adapter = ComicsFragmentAdapter(comicsList, viewModel)
                }
            }
        }
        viewModel.navigateUp.observeEvent(viewLifecycleOwner) { navigateUp ->
            if (navigateUp) {
                utils.navigateUp(requireView())
            }
        }

        viewModel.navigateToComicDetails.observeEvent(viewLifecycleOwner) { character ->
            navigateToCharacterDetailsFragment(character)
        }
    }

    private fun navigateToCharacterDetailsFragment(comic: Comic) {
        requireView().findNavController().navigate(ComicsFragmentDirections.actionComicsFragmentToComicDetailsFragment(comic))
    }
}