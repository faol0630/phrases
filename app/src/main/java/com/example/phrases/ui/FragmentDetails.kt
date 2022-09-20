package com.example.phrases.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import com.example.phrases.R
import com.example.phrases.databinding.FragmentDetailsBinding
import com.example.phrases.data.model.PhraseEntity
import com.example.phrases.data.model.Phrase
import com.example.phrases.presentation.ViewModel1
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FragmentDetails : Fragment(R.layout.fragment_details) {

    private lateinit var binding : FragmentDetailsBinding

    private val viewModel: ViewModel1 by activityViewModels()

    private lateinit var phrase: Phrase

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentDetailsBinding.bind(view)

        viewModel.itemDetails.observe(requireActivity()){
            binding.tvDetailsId.text = it.id.toString()
            binding.tvDetailsTitle.text = it.title

        }

        binding.btnSaveInFavorites.setOnClickListener {

            val id = binding.tvDetailsId.text.toString().toInt()
            val title = binding.tvDetailsTitle.text.toString()

            phrase = Phrase(id, title)

            viewModel.insertPhraseToFavorites(
                PhraseEntity(phrase.id, phrase.title)
            )
            Toast.makeText(requireContext(), "Phrase added to favorites", Toast.LENGTH_LONG).show()
        }

    }
}