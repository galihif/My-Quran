package com.giftech.myquran.ui.surah

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.giftech.myquran.data.SurahRepository
import com.giftech.myquran.data.source.local.entity.AyatEntity
import com.giftech.myquran.data.source.local.entity.LastReadAyatEntity

class SurahViewModel(private val surahRepository: SurahRepository): ViewModel() {

    fun getAyatByNomorSurah(nomorSurah:Int):LiveData<List<AyatEntity>> = surahRepository.getAyatByNomorSurah(nomorSurah)

    fun getLastRead():LiveData<LastReadAyatEntity> = surahRepository.getLastRead()

    fun setLastRead(ayatEntity: LastReadAyatEntity) = surahRepository.setLastRead(ayatEntity)
}