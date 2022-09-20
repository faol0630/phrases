package com.example.phrases.data.remote

import com.example.phrases.ALBUMS
import com.example.phrases.data.model.PhraseEntity
import retrofit2.http.GET

interface PhrasesService {

    @GET(ALBUMS)
    suspend fun getAllAlbums(): List<PhraseEntity>

}