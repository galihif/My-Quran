package com.giftech.myquran.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.giftech.myquran.data.SurahRepository
import com.giftech.myquran.data.source.local.entity.LastReadAyatEntity

class SearchViewModel(private val surahRepository: SurahRepository):ViewModel() {

//    fun getSurahByName(keyword:String) = surahRepository.getSurahByName(keyword)

    fun getLastRead(): LiveData<LastReadAyatEntity> = surahRepository.getLastRead()

}