package com.giftech.myquran.ui.splash

import androidx.lifecycle.ViewModel
import com.giftech.myquran.data.SurahRepository
import com.giftech.myquran.data.source.local.entity.LastReadAyatEntity

class SplashViewModel(private val surahRepository: SurahRepository):ViewModel() {

    private val lastReadAyatEntity = LastReadAyatEntity(1,1,"Al-Fatihah")

    fun setLastReadFirst(){
        surahRepository.setLastRead(lastReadAyatEntity)
    }

    fun isFirstLaunch():Boolean = surahRepository.getIsFirstLaunch()

    fun setFirstLaunch(){
        surahRepository.setFirstLaunch()
    }

}