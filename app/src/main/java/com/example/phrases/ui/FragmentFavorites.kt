package com.example.phrases.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.phrases.R
import com.example.phrases.databinding.FragmentFavoritesBinding
import com.example.phrases.data.model.Phrase
import com.example.phrases.presentation.ViewModel1
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FragmentFavorites : Fragment(R.layout.fragment_favorites),
    AdapterPhrase.OnPhraseClickListener {

    private val adapterPhrase = AdapterPhrase(this)

    private lateinit var binding: FragmentFavoritesBinding

    private val viewModel: ViewModel1 by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentFavoritesBinding.bind(view)

        binding.rvFavorites.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        favoritesObserver()

        viewModel.getFavoritesPhrasesVM()


    }

    private fun favoritesObserver() {
        viewModel.favList.observe(requireActivity()) {

            binding.rvFavorites.adapter = adapterPhrase

            val list: List<Phrase> = it.map { phraseEntity ->
                Phrase(phraseEntity.id, phraseEntity.title)
            }
            adapterPhrase.getData(list)

        }

    }


    override fun onPhraseClick(phrase: Phrase, position: Int) {

        viewModel.sendDetails(phrase)
        findNavController().navigate(R.id.action_fragmentFavorites_to_fragmentFavoritesDetails)
    }


}