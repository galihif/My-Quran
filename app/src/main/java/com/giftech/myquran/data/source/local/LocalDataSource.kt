package com.giftech.myquran.data.source.local

import androidx.lifecycle.LiveData
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
    private val db:SurahDatabase
){
    companion object {
        private var instance: LocalDataSource? = null

        fun getInstance(surahDao: SurahDao, preferences: Preferences, db:SurahDatabase): LocalDataSource =
            instance ?: synchronized(this) {
                instance ?: LocalDataSource(surahDao,preferences,db)
            }
    }

    fun getAllSurah():LiveData<List<SurahEntity>> = surahDao.getAllSurah()

    fun getListSurah() = surahDao.getListSurah()

    suspend fun insertNewSurah(listSurah: List<SurahEntity>){
        db.withTransaction {
            surahDao.deleteAllSurah()
            surahDao.insertSurah(listSurah)
        }
    }

    fun getSurahByName(keyword:String): Flow<List<SurahEntity>> = surahDao.getSurahByName(keyword)

    fun setLastSurah(surah:SurahEntity){
        preferences.setSurah(surah)
    }

    fun getLastSurah():SurahEntity = preferences.getSurah()

    fun setLastAyat(ayat:LastReadAyatEntity){
        preferences.setAyat(ayat)
    }

    fun getLastAyat():LastReadAyatEntity = preferences.getAyat()

    fun setFirstLaunch(){
        preferences.setFirstLaunch()
    }

    fun getIsFirstLaunch(): Boolean = preferences.getIfFirstLaunch()

}