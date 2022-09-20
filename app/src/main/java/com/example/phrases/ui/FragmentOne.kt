package com.example.phrases.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.phrases.*
import com.example.phrases.core.SealedClass1
import com.example.phrases.databinding.FragmentOneBinding
import com.example.phrases.data.model.Phrase
import com.example.phrases.presentation.ViewModel1
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FragmentOne : Fragment(R.layout.fragment_one), AdapterPhrase.OnPhraseClickListener {

    private val adapterPhrase = AdapterPhrase(this)

    private lateinit var binding: FragmentOneBinding

    private val viewModel: ViewModel1 by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentOneBinding.bind(view)

        binding.rvAlbum.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.rvAlbum.adapter = adapterPhrase

        viewModel.allPhrasesVarVm.observe(requireActivity()) {
            updatingSealedClass(it)
        }
        viewModel.showAllPhrasesVm()

        binding.btnGoToFavorites.setOnClickListener {
            findNavController().navigate(R.id.action_fragmentOne_to_fragmentFavorites)
        }
    }

    private fun updatingSealedClass(sealedClass1: SealedClass1) {
        when (sealedClass1) {
            SealedClass1.Loading -> {
                binding.rvAlbum.isVisible = false
                binding.imageView.isVisible = false
                binding.progressBarPhrases.isVisible = true
            }
            SealedClass1.Error -> {
                binding.imageView.isVisible = true
                binding.rvAlbum.isVisible = false
                binding.progressBarPhrases.isVisible = false
            }
            is SealedClass1.Content -> {
                binding.rvAlbum.isVisible = true
                binding.imageView.isVisible = false
                binding.progressBarPhrases.isVisible = false
                adapterPhrase.getData(sealedClass1.sealedList)
            }
        }
    }

    override fun onPhraseClick(phrase: Phrase, position: Int) {
        viewModel.sendDetails(phrase)
        findNavController().navigate(R.id.action_fragmentOne_to_fragmentDetails)
    }


}