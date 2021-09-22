package com.giftech.myquran.ui.surah

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.giftech.myquran.data.SurahRepository
import com.giftech.myquran.data.source.local.entity.AyatEntity

class SurahViewModel(private val surahRepository: SurahRepository): ViewModel() {

    fun getAyatByNomorSurah(nomorSurah:Int):LiveData<List<AyatEntity>> = surahRepository.getAyatByNomorSurah(nomorSurah)
}