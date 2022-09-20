package com.example.phrases.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.phrases.core.SealedClass1
import com.example.phrases.data.model.PhraseEntity
import com.example.phrases.data.model.Phrase
import com.example.phrases.repository.RepositoryInterface
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ViewModel1 @Inject constructor(
    private val repository: RepositoryInterface
) : ViewModel() {

    private var _allPhrasesVarVm = MutableLiveData<SealedClass1>()
    val allPhrasesVarVm: LiveData<SealedClass1>
        get() = _allPhrasesVarVm

    fun showAllPhrasesVm() {
        viewModelScope.launch {
            _allPhrasesVarVm.postValue(SealedClass1.Loading)

            try {
                val repo = repository.getPhrases()
                _allPhrasesVarVm.postValue(SealedClass1.Content(repo))
            }catch (e: Exception){
                _allPhrasesVarVm.postValue(SealedClass1.Error)
            }

        }
    }

    //------------------------------------------------------

    private val _itemDetails = MutableLiveData<Phrase>()
    val itemDetails: LiveData<Phrase>
        get() = _itemDetails

    //send phrase to FragmentDetails :
    fun sendDetails(phrase: Phrase) {
        _itemDetails.value = phrase
    }

    //Room------------------------------------------------------

    //used in Fragment Details to send phrase to Fragments Favorites:

    fun insertPhraseToFavorites(phrase: PhraseEntity) {
        viewModelScope.launch {
            repository.insertPhraseToFavorites(phrase)
        }
    }

    //used in Fragment Details Favorites Details to delete phrase from Fragment Favorites:

    fun deletePhraseFromFavorites(phrase: PhraseEntity){
        viewModelScope.launch {
            repository.deletePhraseFromFavorites(phrase)
        }
    }

    //see all the favorite phrases in FragmentFavorites:

    private var _favList = MutableLiveData<List<PhraseEntity>>()
    val favList: LiveData<List<PhraseEntity>>
        get() = _favList


    fun getFavoritesPhrasesVM(): LiveData<List<PhraseEntity>> {

        viewModelScope.launch {
            _favList.value = repository.getFavoritesPhrases()
        }
        return _favList


    }

}