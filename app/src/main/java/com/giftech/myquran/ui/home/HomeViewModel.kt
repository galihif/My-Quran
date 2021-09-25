package com.giftech.myquran.ui.home

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.giftech.myquran.data.SurahRepository
import com.giftech.myquran.data.source.local.entity.LastReadAyatEntity
import com.giftech.myquran.data.source.local.entity.SurahEntity

class HomeViewModel(private val surahRepository: SurahRepository, val context: Context):ViewModel() {

    fun getAllSurah():LiveData<List<SurahEntity>> = surahRepository.getAllSurah()

    fun getLastRead():LiveData<LastReadAyatEntity> = surahRepository.getLastRead(context)

    fun setLastRead(ayatEntity: LastReadAyatEntity) = surahRepository.setLastRead(context, ayatEntity)

}