package com.giftech.myquran.ui.splash

import androidx.lifecycle.ViewModel
import com.giftech.myquran.data.SurahRepository
import com.giftech.myquran.data.source.local.entity.LastReadAyatEntity
import com.giftech.myquran.data.source.local.entity.SurahEntity

class SplashViewModel(private val surahRepository: SurahRepository):ViewModel() {

    private val lastReadAyatEntity = LastReadAyatEntity(1,1,"Al-Fatihah")
    private val surah = SurahEntity(
        "Al Fatihah",
        "الفاتحة",
        "Pembukaan",
        7,
        1,
        "http://ia802609.us.archive.org/13/items/quraninindonesia/001AlFaatihah.mp3",
        "mekah"
    )

    fun setSurahFirst(){
        surahRepository.setLastSurah(surah)
    }

    fun setLastReadFirst(){
        surahRepository.setLastRead(lastReadAyatEntity)
    }

    fun isFirstLaunch():Boolean = surahRepository.getIsFirstLaunch()

    fun setFirstLaunch(){
        surahRepository.setFirstLaunch()
    }

}