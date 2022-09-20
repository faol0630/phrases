package com.example.phrases.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.phrases.data.model.PhraseEntity

@Database(entities = [PhraseEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase(){

    abstract fun phraseDao() : PhraseDao

}
