package com.giftech.myquran.data.source.local

import androidx.room.withTransaction
import com.giftech.myquran.data.source.local.entity.LastReadAyatEntity
import com.giftech.myquran.data.source.local.entity.SurahEntity
import com.giftech.myquran.data.source.local.preferences.Preferences
import com.giftech.myquran.data.source.local.room.SurahDao
import com.giftech.myquran.data.source.local.room.SurahDatabase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalDataSource @Inject constructor(
    private val surahDao: SurahDao,
    private val preferences: Preferences,
    private val db: SurahDatabase
) {

    fun getListSurah() = surahDao.getListSurah()

    suspend fun insertNewSurah(listSurah: List<SurahEntity>) {
        db.withTransaction {
            surahDao.deleteAllSurah()
            surahDao.insertSurah(listSurah)
        }
    }

    fun getSurahByName(keyword: String): Flow<List<SurahEntity>> = surahDao.getSurahByName(keyword)

    fun setLastAyat(ayat: LastReadAyatEntity) {
        preferences.setAyat(ayat)
    }

    fun getLastAyat(): LastReadAyatEntity = preferences.getAyat()
}