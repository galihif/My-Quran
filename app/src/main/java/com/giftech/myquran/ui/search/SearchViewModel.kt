package com.giftech.myquran.ui.search

import androidx.lifecycle.ViewModel
import com.giftech.myquran.data.SurahRepository

class SearchViewModel(private val surahRepository: SurahRepository):ViewModel() {

    fun getSurahByName(keyword:String) = surahRepository.getSurahByName(keyword)

}