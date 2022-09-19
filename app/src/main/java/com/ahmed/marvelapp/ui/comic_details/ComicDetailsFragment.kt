package com.ahmed.marvelapp.ui.comic_details

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.ahmed.marvelapp.R
import com.ahmed.marvelapp.databinding.FragmentComicDetailsBinding
import com.ahmed.marvelapp.domain.sealeds.RecyclerViewItem
import com.ahmed.marvelapp.ui.base.BaseFragment
import com.ahmed.marvelapp.ui.nested.characters.NestedCharactersClicksListener
import com.ahmed.marvelapp.utilities.Utils
import com.ahmed.marvelapp.utilities.observeEvent

class ComicDetailsFragment : BaseFragment<FragmentComicDetailsBinding>() {
    override val layoutIdFragment: Int = R.layout.fragment_comic_details
    override val viewModel: ComicDetailsFragmentViewModel by viewModels()

    private val args: ComicDetailsFragmentArgs by navArgs()
    private val utils = Utils()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.setComic(args.comic)
        handleEvents()
    }

    private fun handleEvents() {
        viewModel.gotComic.observe(viewLifecycleOwner) { comic ->
            val list = listOf(
                RecyclerViewItem.ComicDetails(
                    comic.title!!,
                    comic.description!!,
                    comic.urls?.get(0)?.url!!,
                )
            )
            binding.comicDetailsRecyclerView.adapter = ComicDetailsFragmentAdapter(list, viewModel as ComicDetailsClicksListener, viewModel as NestedCharactersClicksListener)
        }
        viewModel.navigateUp.observeEvent(viewLifecycleOwner) { navigateUp ->
            if (navigateUp) {
                utils.navigateUp(requireView())
            }
        }
        viewModel.shareComic.observeEvent(viewLifecycleOwner) { url ->
            shareComic(url)
        }
        viewModel.visitComic.observeEvent(viewLifecycleOwner) { url ->
            visitComic(url)
        }
    }

    private fun shareComic(url: String) {
        utils.share(requireActivity(), url)
    }

    private fun visitComic(url: String) {
        utils.openUrl(requireActivity(), url)
    }
}