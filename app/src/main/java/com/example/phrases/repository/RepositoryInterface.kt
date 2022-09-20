package com.example.phrases.repository

import com.example.phrases.data.model.PhraseEntity
import com.example.phrases.data.model.Phrase

interface RepositoryInterface {

    suspend fun getPhrases(): List<Phrase>

    //Room---------------------------------

    suspend fun insertPhraseToFavorites(phrase: PhraseEntity)

    suspend fun deletePhraseFromFavorites(phrase: PhraseEntity)

    suspend fun getFavoritesPhrases(): List<PhraseEntity>

}
