package com.example.phrases.data.remote

import com.example.phrases.BASE_ULR
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class ApiPhrases {

    companion object {

        fun getPhrases(): PhrasesService {
            return Retrofit.Builder()
                .baseUrl(BASE_ULR)
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(PhrasesService::class.java)
        }
    }

}