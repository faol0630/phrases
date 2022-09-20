package com.example.phrases.application.di

import android.content.Context
import androidx.room.Room
import com.example.phrases.data.local.AppDatabase
import com.example.phrases.data.local.PhraseDao
import com.example.phrases.data.remote.ApiPhrases
import com.example.phrases.data.remote.PhrasesService
import com.example.phrases.repository.PhraseRepositoryAPI
import com.example.phrases.repository.RepositoryInterface
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    fun providesPhrasesService(): PhrasesService = ApiPhrases.getPhrases()

    @Provides
    fun providesPhraseRepositoryApi(
        phrase: PhrasesService,
        appDatabase: AppDatabase
    ): PhraseRepositoryAPI = PhraseRepositoryAPI(phrase, appDatabase)

    @Provides
    fun providesRepositoryInterface(
        phraseRepositoryAPI: PhraseRepositoryAPI
    ) : RepositoryInterface = phraseRepositoryAPI

    //Room----------------------------------------

    @Provides
    fun providesAppDataBase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, "phrases_database")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    fun providesPhrasesDao(appDatabase: AppDatabase) : PhraseDao = appDatabase.phraseDao()
}