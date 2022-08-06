package com.giftech.myquran.data.source.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.giftech.myquran.data.source.local.entity.SurahEntity

@Database(entities = [SurahEntity::class], version = 1, exportSchema = false)
abstract class SurahDatabase: RoomDatabase() {

    abstract fun surahDao():SurahDao

    companion object {
        @Volatile
        private var INSTANCE: SurahDatabase? = null

        fun getInstance(context: Context): SurahDatabase =
            INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    SurahDatabase::class.java,
                    "Surah.db"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance

            }
    }
}