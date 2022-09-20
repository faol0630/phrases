package com.example.phrases.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import com.example.phrases.R
import com.example.phrases.databinding.FragmentFavoritesDetailsBinding
import com.example.phrases.data.model.PhraseEntity
import com.example.phrases.data.model.Phrase
import com.example.phrases.presentation.ViewModel1
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FragmentFavoritesDetails : Fragment(R.layout.fragment_favorites_details) {

    private lateinit var binding : FragmentFavoritesDetailsBinding

    private val viewModel: ViewModel1 by activityViewModels()

    private lateinit var phrase: Phrase

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentFavoritesDetailsBinding.bind(view)

        viewModel.itemDetails.observe(requireActivity()){
            binding.tvFavDetailsId.text = it.id.toString()
            binding.tvFavDetailsTitle.text = it.title

        }

        binding.btnDeleteFromFavorites.setOnClickListener{

            val id = binding.tvFavDetailsId.text.toString().toInt()
            val title = binding.tvFavDetailsTitle.text.toString()

            phrase = Phrase(id, title)

            viewModel.deletePhraseFromFavorites(
                PhraseEntity(phrase.id, phrase.title)
            )
            Toast.makeText(requireContext(), "Phrase deleted from favorites", Toast.LENGTH_LONG).show()
        }
    }


}