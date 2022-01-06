package com.giftech.myquran.data.source.local

import androidx.lifecycle.LiveData
import com.giftech.myquran.data.source.local.entity.LastReadAyatEntity
import com.giftech.myquran.data.source.local.entity.SurahEntity
import com.giftech.myquran.data.source.local.preferences.Preferences
import com.giftech.myquran.data.source.local.room.SurahDao

class LocalDataSource private constructor(
    private val surahDao: SurahDao,
    private val preferences: Preferences
){
    companion object {
        private var instance: LocalDataSource? = null

        fun getInstance(surahDao: SurahDao, preferences: Preferences): LocalDataSource =
            instance ?: synchronized(this) {
                instance ?: LocalDataSource(surahDao,preferences)
            }
    }

    fun getAllSurah():LiveData<List<SurahEntity>> = surahDao.getAllSurah()

    fun insertSurah(listSurah: List<SurahEntity>) = surahDao.insertSurah(listSurah)

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