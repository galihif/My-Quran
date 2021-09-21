package com.giftech.myquran.data

import androidx.lifecycle.LiveData
import com.giftech.myquran.data.source.local.entity.AyatEntity
import com.giftech.myquran.data.source.local.entity.SurahEntity

interface SurahDataSource {

    fun getAllSurah():LiveData<List<SurahEntity>>
    fun getAyatByNomorSurah(nomorSurah:Int):LiveData<List<AyatEntity>>

}