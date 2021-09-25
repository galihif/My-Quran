package com.giftech.myquran.data

import android.content.Context
import androidx.lifecycle.LiveData
import com.giftech.myquran.data.source.local.entity.AyatEntity
import com.giftech.myquran.data.source.local.entity.LastReadAyatEntity
import com.giftech.myquran.data.source.local.entity.SurahEntity

interface SurahDataSource {

    fun getAllSurah():LiveData<List<SurahEntity>>
    fun getAyatByNomorSurah(nomorSurah:Int):LiveData<List<AyatEntity>>
    fun setLastRead(context: Context,ayat: LastReadAyatEntity)
    fun getLastRead(context:Context):LiveData<LastReadAyatEntity>

}