package com.example.phrases.data.local

import androidx.room.*
import com.example.phrases.data.model.PhraseEntity


@Dao
interface PhraseDao {

    @Query("SELECT * FROM PhraseEntity ORDER BY id ")
    suspend fun getAll(): List<PhraseEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPhraseFavorites(phraseEntity: PhraseEntity)

    @Delete
    suspend fun deletePhraseFromFavorites(phraseEntity: PhraseEntity)
}