package com.example.phrases.repository

import com.example.phrases.data.local.AppDatabase
import com.example.phrases.data.model.PhraseEntity
import com.example.phrases.data.model.Phrase
import com.example.phrases.data.remote.PhrasesService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class PhraseRepositoryAPI @Inject constructor(
    private val apiPhrases: PhrasesService,
    private val appDatabase: AppDatabase
) : RepositoryInterface {

    override suspend fun getPhrases(): List<Phrase>{
        return withContext(Dispatchers.IO){
            apiPhrases.getAllAlbums().map {
                Phrase(
                    it.id,
                    it.title
                )
            }
        }
    }

    //Room -----------------------------------------------------

    override suspend fun insertPhraseToFavorites(phrase: PhraseEntity) {
        appDatabase.phraseDao().insertPhraseFavorites(phrase)
    }

    override suspend fun deletePhraseFromFavorites(phrase: PhraseEntity) {
        appDatabase.phraseDao().deletePhraseFromFavorites(phrase)
    }

    override suspend fun getFavoritesPhrases(): List<PhraseEntity> {
        return appDatabase.phraseDao().getAll()
    }

}