package com.ahmed.marvelapp.ui.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.ahmed.marvelapp.R
import com.ahmed.marvelapp.data.response.Character
import com.ahmed.marvelapp.data.response.Comic
import com.ahmed.marvelapp.databinding.FragmentHomeBinding
import com.ahmed.marvelapp.domain.sealeds.RecyclerViewItem
import com.ahmed.marvelapp.domain.sealeds.RequestState
import com.ahmed.marvelapp.ui.base.BaseFragment
import com.ahmed.marvelapp.ui.nested.characters.NestedCharactersClicksListener
import com.ahmed.marvelapp.ui.nested.comics.NestedComicsClicksListener
import com.ahmed.marvelapp.utilities.observeEvent

class HomeFragment : BaseFragment<FragmentHomeBinding>() {
    override val layoutIdFragment: Int = R.layout.fragment_home
    override val viewModel: HomeFragmentViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        handleEvents()
    }

    private fun handleEvents() {
        viewModel.run {
            requestState.observe(viewLifecycleOwner) {
                if (it is RequestState.Success) {
                    it.data?.data?.results?.let { listOfCharacters ->
                        val randomCharacter = listOfCharacters.random()
                        val randomCharacterImageUrl = "${randomCharacter.thumbnail?.path}.${randomCharacter.thumbnail?.extension}"
                        val randomComic = viewModel.comicsList.random()
                        val randomComicImageUrl = "${randomComic.thumbnail?.path}.${randomComic.thumbnail?.extension}"
                        val viewItemsList = mutableListOf(
                            RecyclerViewItem.InfoCard(
                                title = randomCharacter.name!!,
                                subtitle = "Today's Character",
                                url = randomCharacterImageUrl
                            ),
                            RecyclerViewItem.Characters(listOfCharacters),
                            RecyclerViewItem.InfoCard(
                                title = randomComic.title!!,
                                subtitle = "Today's Comic",
                                url = randomComicImageUrl
                            ),
                            RecyclerViewItem.Comics(viewModel.comicsList),
                        )
                        binding.homeRecyclerView.adapter = HomeFragmentAdapter(viewItemsList, viewModel as NestedComicsClicksListener, viewModel as NestedCharactersClicksListener)
                    }
                }
            }
            nestedCharacterClicked.observeEvent(viewLifecycleOwner) { character ->
                navigateToCharacterDetailsFragment(character)
            }
            showMoreCharactersClicked.observeEvent(viewLifecycleOwner) { clicked ->
                if (clicked) {
                    navigateToCharactersFragment()
                }
            }
            nestedComicClicked.observeEvent(viewLifecycleOwner) { comic ->
                navigateToComicDetailsFragment(comic)
            }
            showMoreComicsClicked.observeEvent(viewLifecycleOwner) { clicked ->
                if (clicked) {
                    navigateToComicsFragment()
                }
            }
        }
    }

    private fun navigateToCharactersFragment() {
        requireView().findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToCharactersFragment())
    }

    private fun navigateToCharacterDetailsFragment(character: Character) {
        requireView().findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToCharacterDetailsFragment(character))
    }

    private fun navigateToComicsFragment() {
        requireView().findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToComicsFragment())
    }

    private fun navigateToComicDetailsFragment(comic: Comic) {
        requireView().findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToComicDetailsFragment(comic))
    }
}