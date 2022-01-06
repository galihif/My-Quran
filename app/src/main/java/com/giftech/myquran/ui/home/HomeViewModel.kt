package com.giftech.myquran.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.giftech.myquran.data.Resource
import com.giftech.myquran.data.SurahRepository
import com.giftech.myquran.data.source.local.entity.LastReadAyatEntity
import com.giftech.myquran.data.source.local.entity.SurahEntity

class HomeViewModel(private val surahRepository: SurahRepository):ViewModel() {

    fun getAllSurah(): LiveData<Resource<List<SurahEntity>>> = surahRepository.getAllSurah()

    fun getLastRead():LiveData<LastReadAyatEntity> = surahRepository.getLastRead()

    fun getLastSurah():LiveData<SurahEntity> = surahRepository.getLastSurah()

}